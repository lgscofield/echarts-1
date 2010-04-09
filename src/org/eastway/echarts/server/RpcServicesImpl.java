package org.eastway.echarts.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Vector;

import javax.naming.NamingException;

import org.eastway.echarts.client.RpcServices;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Demographics;
import org.eastway.echarts.shared.EHR;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.ServiceCode;
import org.eastway.echarts.shared.ServiceCodes;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class RpcServicesImpl extends RemoteServiceServlet implements
		RpcServices, DbConstants {

	@Override
	public EHR getEhr(long ehrId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		String sql = "SELECT * FROM Patient INNER JOIN Ehr on Ehr.subject_id = Patient.Patient_id WHERE EHR.ehr_id = " + ehrId + " ORDER BY Patient_Id";
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;
		List<Long> patientIds = new ArrayList<Long>();
		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);

			while (srs.next())
				patientIds.add(srs.getLong("Patient_Id"));
			EHR ehr = new EHR(ehrId, patientIds);
			return ehr;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public Patient getPatient(long patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		String sql = "SELECT * FROM Patient INNER JOIN Demographics ON Patient.Patient_Id = Demographics.Patient_Id WHERE Patient.Patient_Id = "
			+ patientId;
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;
		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			if (srs.next()) {
				String[] incomeSources = {
						srs.getString("IncomeSource1"),
						srs.getString("IncomeSource2"),
						srs.getString("IncomeSource3")
				};

				String[] allergies = {
						srs.getString("Allergies")
				};

				Demographics d = new Demographics(srs.getString("Gender"),
						srs.getString("Race"),
						srs.getString("MaritalStatus"),
						srs.getString("LivingArrangement"),
						srs.getString("Employment"),
						incomeSources,
						srs.getString("EducationLevel"),
						srs.getString("EducationType"),
						allergies,
						null,
						null,
						null,
						null,
						srs.getBoolean("Veteran"),
						srs.getBoolean("SP_SMD"),
						srs.getBoolean("SP_AlcoholDrug"),
						srs.getBoolean("SP_Forensic"),
						srs.getBoolean("SP_DD"),
						srs.getBoolean("SP_MIMR"),
						srs.getBoolean("SP_DUIDWI"),
						srs.getBoolean("SP_DEAF"),
						srs.getBoolean("SP_HearingImpaired"),
						srs.getBoolean("SP_Blind"),
						srs.getBoolean("SP_VisuallyImpaired"),
						srs.getBoolean("SP_PhyDisabled"),
						srs.getBoolean("SP_SpeechImpaired"),
						srs.getBoolean("SP_PhysicalAbuse"),
						srs.getBoolean("SP_SexualAbuse"),
						srs.getBoolean("SP_DomesticViolence"),
						srs.getBoolean("SP_ChildAlcDrug"),
						srs.getBoolean("SP_HIVAIDS"),
						srs.getBoolean("SP_Suicidal"),
						srs.getBoolean("SP_SchoolDropout"),
						srs.getBoolean("SP_ProbationParole"),
						srs.getBoolean("SP_GeneralPopulation"),
						srs.getDate("DOB"),
						srs.getDate("LastEdit"),
						srs.getString("LastEditBy"));
				Patient p = new Patient(srs.getString("Alias"),
					srs.getString("CaseNumber"),
					srs.getString("CaseStatus"),
					srs.getString("FirstName"),
					srs.getDate("LastEdit"),
					srs.getString("LastEditBy"),
					srs.getString("LastName"),
					srs.getString("MiddleInitial"),
					srs.getLong("Patient_Id"),
					srs.getString("SSN"),
					srs.getString("Suffix"),
					d);
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public Patient editEhr(Patient patient, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		Connection con = null;

		try {
			con = DbConnection.getConnection();

			con.setAutoCommit(false);

			PreparedStatement patientInsert = con.prepareStatement(
					"INSERT INTO Patient("
						+ "CaseNumber, FirstName, MiddleInitial, LastName, Name, ehr_id, SSN, CaseStatus, LastEditBy, LastEdit)"
						+ " VALUES(?,?,?,?,?,(SELECT ehr_id FROM Ehr WHERE subject_id = ?),?,?,?,GETDATE())",
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
					+ "?,?,GETDATE()"
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
	public void newEhr(Patient patient, String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		Connection con = null;

		try {
			con = DbConnection.getConnection();

			con.setAutoCommit(false);

			PreparedStatement patientInsert = con.prepareStatement(
					"INSERT INTO Patient("
						+ "CaseNumber, FirstName, MiddleInitial, LastName, Name, SSN, CaseStatus, LastEdit"
						+ ")"
						+ " VALUES(?,?,?,?,?,?,?,GETDATE())",
					PreparedStatement.RETURN_GENERATED_KEYS);
			patientInsert.setString(1, patient.getCaseNumber());
			patientInsert.setString(2, patient.getFirstName());
			patientInsert.setString(3, patient.getMiddleInitial());
			patientInsert.setString(4, patient.getLastName());
			patientInsert.setString(5, patient.getName());
			patientInsert.setString(6, patient.getSsn());
			patientInsert.setString(7, patient.getCaseStatus());
			patientInsert.executeUpdate();

			ResultSet lastPatientInsertIds = patientInsert.getGeneratedKeys();
			int patientID = -1;
			if (lastPatientInsertIds.next())
				patientID = lastPatientInsertIds.getInt(1);

			PreparedStatement ehrInsert = con.prepareStatement(
					"INSERT INTO Ehr("
						+ "subject_id,time_created"
						+ ")"
						+ " VALUES(?,GETDATE())", PreparedStatement.RETURN_GENERATED_KEYS);
			ehrInsert.setLong(1, patientID);
			ehrInsert.executeUpdate();

			ResultSet lastEhrInsertIds = ehrInsert.getGeneratedKeys();
			int ehrID = -1;
			if (lastEhrInsertIds.next())
				ehrID = lastEhrInsertIds.getInt(1);

			PreparedStatement patientUpdate = con.prepareStatement(
					"UPDATE Patient SET ehr_id = ? WHERE Patient_Id = ?");
			patientUpdate.setInt(1, ehrID);
			patientUpdate.setInt(2, patientID);
			patientUpdate.executeUpdate();

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
					+ "?,?,GETDATE()"
					+ ")");

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
//			demographicsInsert.setString(13, patient.getDemographics().getIncomeSources()[0]);
			demographicsInsert.setString(13, null);
//			demographicsInsert.setString(14, patient.getDemographics().getIncomeSources()[1]);
			demographicsInsert.setString(14, null);
//			demographicsInsert.setString(15, patient.getDemographics().getIncomeSources()[2]);
			demographicsInsert.setString(15, null);
//			demographicsInsert.setString(16, patient.getDemographics().getAllergies()[0]);
			demographicsInsert.setString(16, null);
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

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public LinkedHashMap<String, Long> getPatientList(String sessionId) throws SessionExpiredException, DbException {
		Connection con = null;
		LinkedHashMap<String, Long> pl = new LinkedHashMap<String, Long>();
		checkSessionExpire(sessionId);
		String sql = "SELECT CaseNumber + ' - ' + LastName + ', ' + FirstName AS SearchString, Ehr.ehr_id FROM Patient INNER JOIN Ehr ON Ehr.subject_id = Patient.Patient_ID ORDER BY LastName";
		Statement stmt = null;
		ResultSet srs = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next())
				pl.put(srs.getString(1), srs.getLong(2));
			return pl;
		} catch (SQLException e) {
			throw new DbException();
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}

	}

	@Override
	public Vector<String> getAlerts(String sessionId) throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);

		Vector<String> alerts = new Vector<String>();
		String sql = null;
		String staffId = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;
		Date today = new Date();
		Date dueDate = new Date();
		DateFormat df = new SimpleDateFormat("M/dd/yyyy");
		long diff;
		String warningMessage;

		try {
			staffId = getStaffId(sessionId);
			sql = "SELECT VTicklerList.PATID, Patient.Name, VTicklerList.ItemName, VTicklerList.[Date] FROM VTicklerList INNER JOIN Patient ON VTicklerList.PATID = Patient.CaseNumber WHERE [Date] IS NOT NULL AND VTicklerList.PATID IN (SELECT PATID FROM Assignments WHERE StaffId = '"
				+ staffId + "' AND Disposition = 1) ORDER BY 4 ASC";
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next()) {
				String a = new String();
				dueDate = srs.getDate(4);
				diff = dueDate.getTime() - today.getTime();
				if (diff <= 0) {
					warningMessage = " is overdue by <font color='red'><b>"
							+ (diff / (1000 * 60 * 60 * 24)) + "</b></font>";
				} else {
					warningMessage = " is due in <font color='green'><b>"
							+ (diff / (1000 * 60 * 60 * 24)) + "</b></font>";
				}
				a = srs.getString(1) + " - " + srs.getString(2) + ": "
						+ srs.getString(3) + warningMessage + " days ("
						+ df.format(dueDate) + ")";
				alerts.add(a);
			}
			return alerts;
		} catch (SQLException e) {
			throw new DbException();
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}

	@Override
	public Messages getMessages(String patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		Messages msgs = new Messages();

		String sql = "SELECT [Messages].ID, PATID, [Descriptor] as 'MessageType', CreationTimestamp, Message, [ParentID], StaffName FROM [Messages] INNER JOIN VMessageType ON [Messages].MessageType = VMessageType.MessageType INNER JOIN [User] ON [Messages].LastEditBy = [User].StaffId WHERE PATID ="
				+ patientId;
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
		Statement stmt = null;
		String sql = null;

		try {
			staffId = getStaffId(sessionId);
			messageType = getMessageType(msg.getMessageType());

			sql = "INSERT INTO Messages(PATID, MessageType, Message, LastEditBy)"
				+ "Values ('"
				+ msg.getPatId()
				+ "',"
				+ messageType
				+ ",'"
				+ msg.getMessage()
				+ "','"
				+ staffId + "')";

			con = DbConnection.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DbException("Error adding message", e);
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
	public Demographics getDemographics(String patientid, String sessionId)
			throws SessionExpiredException, DbException {
		// TODO Auto-generated method stub
		return null;
	}
}
