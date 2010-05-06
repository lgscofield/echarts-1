/*
 * Copyright 2010 Ian Hilt
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.eastway.echarts.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eastway.echarts.client.RpcServices;
import org.eastway.echarts.domain.Alert;
import org.eastway.echarts.domain.AlertService;
import org.eastway.echarts.domain.Demographics;
import org.eastway.echarts.domain.DemographicsService;
import org.eastway.echarts.domain.EHR;
import org.eastway.echarts.domain.EHRService;
import org.eastway.echarts.domain.Patient;
import org.eastway.echarts.domain.PatientService;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.DemographicsDTO;
import org.eastway.echarts.shared.EHRDTO;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.PatientDTO;
import org.eastway.echarts.shared.ServiceCode;
import org.eastway.echarts.shared.ServiceCodes;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServicesImpl extends RemoteServiceServlet implements
		RpcServices, DbConstants {

	@Override
	public EHRDTO getEhr(long ehrId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		EHRService ehrService = new EHRService(em);
		EHR ehr = ehrService.find(ehrId);
		EHRDTO ehrDto = new EHRDTO();
		ehrDto.setEhrId(ehr.getId());
		ArrayList<Long> patientIds = new ArrayList<Long>();
		patientIds.add(ehr.getSubject().getId());
		ehrDto.setPatientIds(patientIds);
		em.close();
		emf.close();
		return ehrDto;
	}

	@Override
	public PatientDTO getPatient(long patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		PatientService patientService = new PatientService(em);
		Patient patient = patientService.find(patientId);
		PatientDTO patientDto = new PatientDTO();

		patientDto.setAlias(patient.getAlias());
		patientDto.setCaseNumber(patient.getCaseNumber());
		patientDto.setCaseStatus(patient.getCaseStatus());
		patientDto.setDemographics(getDemographics(patientId, sessionId));
		patientDto.setFirstName(patient.getFirstName());
		patientDto.setLastEdit(patient.getLastEdit());
		patientDto.setLastEditBy(patient.getLastEditBy());
		patientDto.setLastName(patient.getLastName());
		patientDto.setMiddleInitial(patient.getMiddleInitial());
		patientDto.setPatientId(patient.getId());
		patientDto.setSsn(patient.getSsn());
		patientDto.setSuffix(patient.getSuffix());
		em.close();
		emf.close();
		return patientDto;
	}

	@Override
	public PatientDTO editEhr(PatientDTO patient, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		Connection con = null;

		try {
			con = DbConnection.getConnection();

			con.setAutoCommit(false);

			PreparedStatement patientInsert = con.prepareStatement(
					"INSERT INTO Patient("
						+ "CaseNumber, FirstName, MiddleInitial, LastName, Name, ehr_id, SSN, CaseStatus, LastEditBy, LastEdit)"
						+ " VALUES(?,?,?,?,?,(SELECT ehr_id FROM Ehr WHERE subject_id = ?),?,?,?,GETUTCDATE())",
					PreparedStatement.RETURN_GENERATED_KEYS);
			patientInsert.setString(1, patient.getCaseNumber());
			patientInsert.setString(2, patient.getFirstName());
			patientInsert.setString(3, patient.getMiddleInitial());
			patientInsert.setString(4, patient.getLastName());
			patientInsert.setString(5, patient.getName());
			patientInsert.setLong(6, patient.getPatientId());
			patientInsert.setString(7, patient.getSsn());
			patientInsert.setString(8, patient.getCaseStatus());
			patientInsert.setString(9, getStaffId(sessionId));
			patientInsert.executeUpdate();

			ResultSet lastInsertIds = patientInsert.getGeneratedKeys();

			PreparedStatement demographicsInsert = con.prepareStatement(
					"INSERT INTO Demographics("
					+ "Patient_Id,DOB,Insurance,Gender,"
					+ "Race,Veteran,Religion,MaritalStatus,"
					+ "EducationLevel,EducationType,LivingArrangement,Employment,"
					+ "IncomeSource1,IncomeSource2,IncomeSource3,Allergies,"
					+ "SP_SMD,SP_AlcoholDrug,SP_Forensic,SP_DD,"
					+ "SP_MIMR,SP_DUIDWI,SP_Deaf,SP_HearingImpaired,"
					+ "SP_Blind,SP_VisuallyImpaired,SP_PhyDisabled,SP_SpeechImpaired,"
					+ "SP_PhysicalAbuse,SP_SexualAbuse,SP_DomesticViolence,SP_ChildAlcDrug,"
					+ "SP_HIVAIDS,SP_Suicidal,SP_SchoolDropout,SP_ProbationParole,"
					+ "SP_GeneralPopulation,LastEditBy,LastEdit"
					+ ") VALUES("
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,?,?,"
					+ "?,?,GETUTCDATE()"
					+ ")");

			int patientID = -1;

			if (lastInsertIds.next())
				patientID = lastInsertIds.getInt(1);

			demographicsInsert.setInt(1, patientID);
			demographicsInsert.setDate(2, new java.sql.Date(patient.getDemographics().getDob().getTime()));
			demographicsInsert.setString(3, patient.getDemographics().getInsuranceType());
			demographicsInsert.setString(4, patient.getDemographics().getGender());
			demographicsInsert.setString(5, patient.getDemographics().getRace());
			demographicsInsert.setBoolean(6, patient.getDemographics().isVeteran());
			demographicsInsert.setString(7, patient.getDemographics().getReligion());
			demographicsInsert.setString(8, patient.getDemographics().getMaritalStatus());
			demographicsInsert.setString(9, patient.getDemographics().getEducationLevel());
			demographicsInsert.setString(10, patient.getDemographics().getEducationType());
			demographicsInsert.setString(11, patient.getDemographics().getLivingArrangement());
			demographicsInsert.setString(12, patient.getDemographics().getEmployment());
			demographicsInsert.setString(13, patient.getDemographics().getIncomeSources()[0]);
			demographicsInsert.setString(14, patient.getDemographics().getIncomeSources()[1]);
			demographicsInsert.setString(15, patient.getDemographics().getIncomeSources()[2]);
			demographicsInsert.setString(16, patient.getDemographics().getAllergies()[0]);
			demographicsInsert.setBoolean(17, patient.getDemographics().isSmd());
			demographicsInsert.setBoolean(18, patient.getDemographics().isAlcoholDrug());
			demographicsInsert.setBoolean(19, patient.getDemographics().isForensic());
			demographicsInsert.setBoolean(20, patient.getDemographics().isDd());
			demographicsInsert.setBoolean(21, patient.getDemographics().isMimr());
			demographicsInsert.setBoolean(22, patient.getDemographics().isDuidwi());
			demographicsInsert.setBoolean(23, patient.getDemographics().isDeaf());
			demographicsInsert.setBoolean(24, patient.getDemographics().isHearingImpaired());
			demographicsInsert.setBoolean(25, patient.getDemographics().isBlind());
			demographicsInsert.setBoolean(26, patient.getDemographics().isVisuallyImpaired());
			demographicsInsert.setBoolean(27, patient.getDemographics().isPhyDisabled());
			demographicsInsert.setBoolean(28, patient.getDemographics().isSpeechImpaired());
			demographicsInsert.setBoolean(29, patient.getDemographics().isPhysicalAbuse());
			demographicsInsert.setBoolean(30, patient.getDemographics().isSexualAbuse());
			demographicsInsert.setBoolean(31, patient.getDemographics().isDomesticViolence());
			demographicsInsert.setBoolean(32, patient.getDemographics().isChildAlcDrug());
			demographicsInsert.setBoolean(33, patient.getDemographics().isHivAids());
			demographicsInsert.setBoolean(34, patient.getDemographics().isSuicidal());
			demographicsInsert.setBoolean(35, patient.getDemographics().isSchoolDropout());
			demographicsInsert.setBoolean(36, patient.getDemographics().isProbationParole());
			demographicsInsert.setBoolean(37, patient.getDemographics().isGeneralPopulation());
			demographicsInsert.setString(38, getStaffId(sessionId));
			
			demographicsInsert.executeUpdate();

			PreparedStatement ehrInsert = con.prepareStatement("UPDATE Ehr SET subject_id = ? WHERE subject_id = ?");
			ehrInsert.setLong(1, patientID);
			ehrInsert.setLong(2, patient.getPatientId());
			ehrInsert.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
		return patient;
	}

	@Override
	public void addEhr(PatientDTO patientDto, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		PatientService patientService = new PatientService(em);

		em.getTransaction().begin();
		EHR ehr = new EHR();
		em.persist(ehr);

		Patient patient = patientService.create(patientDto.getAlias(),
				patientDto.getCaseNumber(),
				patientDto.getCaseStatus(),
				ehr.getId(),
				patientDto.getFirstName(),
				patientDto.getLastEdit(),
				patientDto.getLastEditBy(),
				patientDto.getLastName(),
				patientDto.getMiddleInitial(),
				patientDto.getSsn(),
				patientDto.getSuffix());
		ehr.setSubject(patient);
		ehr.setTimeCreated(new Date(System.currentTimeMillis()));
		em.persist(ehr);

		DemographicsService demographicsService = new DemographicsService(em);
		DemographicsDTO demographicsDto = patientDto.getDemographics();
		demographicsService.create(demographicsDto.getGender(),
				demographicsDto.getRace(),
				demographicsDto.getMaritalStatus(),
				demographicsDto.getLivingArrangement(),
				demographicsDto.getEmployment(),
				demographicsDto.getIncomeSources(),
				demographicsDto.getEducationLevel(),
				demographicsDto.getEducationType(),
				demographicsDto.getAllergies(),
				demographicsDto.getInsuranceType(),
				demographicsDto.getPreferredLanguage(),
				demographicsDto.getEthnicity(),
				demographicsDto.getReligion(),
				ehr.getId(),
				demographicsDto.isVeteran(),
				demographicsDto.isSmd(),
				demographicsDto.isAlcoholDrug(),
				demographicsDto.isForensic(),
				demographicsDto.isDd(),
				demographicsDto.isMimr(),
				demographicsDto.isDuidwi(),
				demographicsDto.isDeaf(),
				demographicsDto.isHearingImpaired(),
				demographicsDto.isBlind(),
				demographicsDto.isVisuallyImpaired(),
				demographicsDto.isPhyDisabled(),
				demographicsDto.isSpeechImpaired(),
				demographicsDto.isPhysicalAbuse(),
				demographicsDto.isSexualAbuse(),
				demographicsDto.isDomesticViolence(),
				demographicsDto.isChildAlcDrug(),
				demographicsDto.isHivAids(),
				demographicsDto.isSuicidal(),
				demographicsDto.isSchoolDropout(),
				demographicsDto.isProbationParole(),
				demographicsDto.isGeneralPopulation(),
				demographicsDto.getDob(),
				new Date(System.currentTimeMillis()),
				demographicsDto.getLastEditBy());
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

	@Override
	public LinkedHashMap<String, Long> getPatientList(String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		LinkedHashMap<String, Long> pl = new LinkedHashMap<String, Long>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		EHRService service = new EHRService(em);
		List<EHR> ehrList = service.findAll();
		for (EHR ehr : ehrList)
			pl.put(ehr.getSubject().getCaseNumber() + " - " + ehr.getSubject().getName(), ehr.getId());
		
		//String sql = "SELECT CaseNumber + ' - ' + LastName + ', ' + FirstName AS SearchString, Ehr.ehr_id FROM Patient INNER JOIN Ehr ON Ehr.subject_id = Patient.Patient_ID ORDER BY LastName";
		em.close();
		emf.close();
		return pl;
	}

	@Override
	public Vector<String> getAlerts(String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		AlertService service = new AlertService(em);
		List<Alert> alerts = service.findAll();
		Vector<String> alertsDto = new Vector<String>();
		for (Alert a : alerts)
			alertsDto.add(a.getPatientId() + " " + a.getItemName() + " " + a.getDate().toString());
		em.close();
		emf.close();
		return alertsDto;
	}

	@Override
	public Messages getMessages(String patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		Messages msgs = new Messages();

		String sql = "SELECT [Messages].ID, PATID, [Descriptor] as 'MessageType', CreationTimestamp, Message, [ParentID], StaffName FROM [Messages] INNER JOIN VMessageType ON [Messages].MessageType = VMessageType.MessageType INNER JOIN [User] ON [Messages].LastEditBy = [User].StaffId WHERE PATID ="
				+ patientId + " ORDER BY ID";
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next()) {
				Message m = new Message();
				m.setId(srs.getInt(1));
				m.setPatId(srs.getString(2));
				m.setMessageType(srs.getString(3));
				m.setCreationDate(srs.getTimestamp(4).getTime());
				m.setMessage(srs.getString(5));
				m.setParentId(srs.getInt(6));
				m.setLastModifiedBy(srs.getString(7));
				msgs.add(m);
			}
			return msgs;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public void addMessage(Message msg, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		String staffId = null;
		int messageType;
		Connection con = null;
		PreparedStatement stmt = null;
		String sql = null;

		try {
			staffId = getStaffId(sessionId);
			messageType = getMessageType(msg.getMessageType());
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			sql = "INSERT INTO Messages(PATID, CreationTimestamp, MessageType, Message, LastEdit, LastEditBy)"
				+ " Values (?,?,?,?,?,?)";

			con = DbConnection.getConnection();

			stmt = con.prepareStatement(sql);

			stmt.setString(1, msg.getPatId());
			stmt.setTimestamp(2, ts);
			stmt.setInt(3, messageType);
			stmt.setString(4, msg.getMessage());
			stmt.setTimestamp(5, ts);
			stmt.setString(6, staffId);

			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	private String getStaffId(String sessionId) throws NamingException, DbException {
		String sql = "{call getStaffId(?, ?)}";
		Connection con = null;
		CallableStatement stmt = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.prepareCall(sql);
			stmt.setString("sessionid", sessionId);
			stmt.registerOutParameter("staffid", java.sql.Types.VARCHAR);
			stmt.execute();
			return stmt.getString("staffid");
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public ArrayList<String> getMessageTypes(String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		ArrayList<String> messageType = new ArrayList<String>();
		String sql = "SELECT * FROM VMessageType";
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next()) {
				messageType.add(srs.getString("Descriptor"));
			}
			return messageType;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exceptions");
		}
	}

	private int getMessageType(String messageType) throws SQLException, DbException {
		String sql = "SELECT * FROM VMessageType WHERE Descriptor ='"
				+ messageType + "'";
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next()) {
				return srs.getInt("MessageType");
			}
			throw new DbException("Message type not found");
		} catch (SQLException e) {
			throw e;
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public ServiceCodes getServiceCodes(String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		String sql = "SELECT * FROM ServiceCodes";
		ServiceCodes serviceCodes = new ServiceCodes();
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next()) {
				ServiceCode serviceCode = new ServiceCode();
				serviceCode.add(srs.getInt("Service"), srs
						.getString("Description"), srs.getString("TemplateID"));
				serviceCodes.add(serviceCode);
			}
			return serviceCodes;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}

	}

	private void checkSessionExpire(String sessionId) throws SessionExpiredException, DbException {
		String sql = "{call isSessionExpired(?, ?)}";
		Connection con = null;
		CallableStatement stmt = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.prepareCall(sql);
			stmt.setString("sessionid", sessionId);
			stmt.registerOutParameter("status", java.sql.Types.BIT);
			stmt.execute();
			if (stmt.getBoolean("status")) {
				throw new SessionExpiredException();
			}
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} catch (SessionExpiredException e) {
			throw e;
		}
	}

	@Override
	public UserData getUserData(String username, String sessionId) throws DbException, SessionExpiredException {
		checkSessionExpire(sessionId);
		String sql = "SELECT [StaffId]" +
						",[Username]" +
						",[JobClassID]" +
						",[extperm]" +
						",[StaffName]" +
						",[Program]" +
						",[HireDate]" +
						",[Status]" +
						",[TermDate]" +
						",[Office]" +
						",[OfficePhone]" +
						",[OfficeExt]" +
						",[StaffDescription]" +
						",[StaffNPI]" + " " +
					"FROM [EW-EHR].[dbo].[User]" + " " +
					"WHERE Username = '" + username + "'";

		try {
			Connection con = DbConnection.getConnection();
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet srs = stmt.executeQuery(sql);
			if (srs.next()) {
				UserData userData = new UserData(srs.getString("StaffId"),
						srs.getString("Username"), srs.getString("StaffName"),
						srs.getString("Program"), srs.getString("Status"),
						srs.getString("Office"), srs.getString("OfficePhone"),
						srs.getString("OfficeExt"), srs.getString("StaffDescription"),
						srs.getString("StaffNPI"), srs.getInt("JobClassId"), srs.getInt("extperm"),
						srs.getDate("HireDate"), srs.getDate("TermDate"));
				return userData;
			}
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
		return null;
	}

	@Override
	public LinkedHashSet<String[]> getFormsList(String sessionId, String patientId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		String sql = "SELECT * FROM form.Form_list ORDER BY FormOrder ASC";
		LinkedHashSet<String[]> formsList = new LinkedHashSet<String[]>();

		Connection con;
		Statement stmt;
		ResultSet rs;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String[] s = new String[3];
				s[0] = rs.getString(1);
				s[1] = rs.getString(2);
				s[2] = rs.getString(3);
				formsList.add(s);
			}
			return formsList;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public DemographicsDTO getDemographics(String patientid, String sessionId)
			throws SessionExpiredException, DbException {
		// TODO Auto-generated method stub
		return null;
	}

	public DemographicsDTO getDemographics(long id, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("EchartsPersistence");
		EntityManager em = emf.createEntityManager();
		DemographicsService demographicsService = new DemographicsService(em);
		Demographics demographics = demographicsService.find(id);
		DemographicsDTO demographicsDto = new DemographicsDTO();

		demographicsDto.setAlcoholDrug(demographics.isAlcoholDrug());
		demographicsDto.setAllergies(demographics.getAllergies());
		demographicsDto.setBlind(demographics.isBlind());
		demographicsDto.setChildAlcDrug(demographics.isChildAlcDrug());
		demographicsDto.setDd(demographics.isDd());
		demographicsDto.setDeaf(demographics.isDeaf());
		demographicsDto.setDob(demographics.getDob());
		demographicsDto.setDomesticViolence(demographics.isDomesticViolence());
		demographicsDto.setDuidwi(demographics.isDuidwi());
		demographicsDto.setEducationLevel(demographics.getEducationLevel());
		demographicsDto.setEducationType(demographics.getEducationType());
		demographicsDto.setEmployment(demographics.getEmployment());
		demographicsDto.setEthnicity(demographics.getEthnicity());
		demographicsDto.setForensic(demographics.isForensic());
		demographicsDto.setGender(demographics.getGender());
		demographicsDto.setGeneralPopulation(demographics.isGeneralPopulation());
		demographicsDto.setHearingImpaired(demographics.isHearingImpaired());
		demographicsDto.setHivAids(demographics.isHivAids());
		demographicsDto.setIncomeSources(demographics.getIncomeSources());
		demographicsDto.setInsuranceType(demographics.getInsuranceType());
		demographicsDto.setLastEdit(demographics.getLastEdit());
		demographicsDto.setLastEditBy(demographics.getLastEditBy());
		demographicsDto.setLivingArrangement(demographics.getLivingArrangement());
		demographicsDto.setMaritalStatus(demographics.getMaritalStatus());
		demographicsDto.setMimr(demographics.isMimr());
		demographicsDto.setPhyDisabled(demographics.isPhyDisabled());
		demographicsDto.setPhysicalAbuse(demographics.isPhysicalAbuse());
		demographicsDto.setPreferredLanguage(demographics.getPreferredLanguage());
		demographicsDto.setProbationParole(demographics.isProbationParole());
		demographicsDto.setRace(demographics.getRace());
		demographicsDto.setSchoolDropout(demographics.isSchoolDropout());
		demographicsDto.setSexualAbuse(demographics.isSexualAbuse());
		demographicsDto.setSmd(demographics.isSmd());
		demographicsDto.setSpeechImpaired(demographics.isSpeechImpaired());
		demographicsDto.setSuicidal(demographics.isSuicidal());
		demographicsDto.setVeteran(demographics.isVeteran());
		demographicsDto.setVisuallyImpaired(demographics.isVisuallyImpaired());
		em.close();
		emf.close();
		return demographicsDto;
	}
}
