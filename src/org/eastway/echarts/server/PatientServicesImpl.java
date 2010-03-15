package org.eastway.echarts.server;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Vector;

import javax.naming.NamingException;

import org.eastway.echarts.client.PatientServices;
import org.eastway.echarts.shared.DbException;
import org.eastway.echarts.shared.Message;
import org.eastway.echarts.shared.Messages;
import org.eastway.echarts.shared.Patient;
import org.eastway.echarts.shared.ServiceCode;
import org.eastway.echarts.shared.ServiceCodes;
import org.eastway.echarts.shared.SessionExpiredException;
import org.eastway.echarts.shared.UserData;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class PatientServicesImpl extends RemoteServiceServlet implements
		PatientServices, DbConstants {

	@Override
	public Patient getPatient(String patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		String sql = "SELECT * FROM VDisplayDemographics WHERE PATID = "
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
				Patient p = new Patient(srs.getString("Alias"),
					srs.getString("Allergies"),
					srs.getString("CaseStatus"),
					srs.getDate("DOB"),
					srs.getString("EducationLevel"),
					srs.getString("EducationType"),
					srs.getString("Employment"),
					srs.getString("FirstName"),
					srs.getString("Gender"),
					srs.getString("IncomeSource1"),
					srs.getString("IncomeSource2"),
					srs.getString("IncomeSource3"),
					srs.getDate("LastEdit"),
					srs.getString("LastEditBy"),
					srs.getString("LastName"),
					srs.getString("LivingArrangement"),
					srs.getString("MaritalStatus"),
					srs.getString("Name"),
					srs.getString("PATID"),
					srs.getString("Race"),
					srs.getBoolean("SP_AlcoholDrug"),
					srs.getBoolean("SP_Blind"),
					srs.getBoolean("SP_ChildAlcDrug"),
					srs.getBoolean("SP_DD"),
					srs.getBoolean("SP_Deaf"),
					srs.getBoolean("SP_DomesticViolence"),
					srs.getBoolean("SP_DUIDWI"),
					srs.getBoolean("SP_Forensic"),
					srs.getBoolean("SP_GeneralPopulation"),
					srs.getBoolean("SP_HearingImpaired"),
					srs.getBoolean("SP_HIVAIDS"),
					srs.getBoolean("SP_MIMR"),
					srs.getBoolean("SP_PhyDisabled"),
					srs.getBoolean("SP_PhysicalAbuse"),
					srs.getBoolean("SP_ProbationParole"),
					srs.getBoolean("SP_SchoolDropout"),
					srs.getBoolean("SP_SexualAbuse"),
					srs.getBoolean("SP_SMD"),
					srs.getBoolean("SP_SpeechImpaired"),
					srs.getBoolean("SP_Suicidal"),
					srs.getBoolean("SP_VisuallyImpaired"),
					srs.getString("SSN"),
					srs.getString("Suffix"),
					srs.getBoolean("Veteran"));
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
	public Vector<String> getPatientList(String sessionId) throws SessionExpiredException, DbException {
		Connection con = null;
		Vector<String> pl = new Vector<String>();
		checkSessionExpire(sessionId);
		String sql = "SELECT PATID + ' - ' + Name AS 'SearchString' FROM Demographics ORDER BY Name";
		Statement stmt = null;
		ResultSet srs = null;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next())
				pl.add(srs.getString(1));
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
			sql = "SELECT VTicklerList.PATID, Demographics.Name, VTicklerList.ItemName, VTicklerList.[Date] FROM VTicklerList INNER JOIN Demographics ON VTicklerList.PATID = Demographics.PATID WHERE [Date] IS NOT NULL AND VTicklerList.PATID IN (SELECT PATID FROM Assignments WHERE StaffId = '"
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

		String sql = "SELECT [Messages].ID, PATID, [Descriptor] as 'MessageType', CAST(MessageDate As DateTime) As MessageDate, [Message] as 'Content', [ParentID], StaffName FROM [Messages] INNER JOIN VMessageType ON [Messages].MessageType = VMessageType.MessageType INNER JOIN [User] ON [Messages].LastEditBy = [User].StaffId WHERE PATID ="
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
				m.add(srs.getInt(1), srs.getString(2), srs.getString(3), srs
						.getTimestamp(4), srs.getString(5), srs.getInt(6), srs
						.getString(7));
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

		String sql = "SELECT * FROM form.Form_list";
		LinkedHashSet<String[]> formsList = new LinkedHashSet<String[]>();

		Connection con;
		Statement stmt;
		ResultSet rs;

		try {
			con = DbConnection.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String[] s = new String[2];
				s[0] = rs.getString(1);
				s[1] = rs.getString(2);
				formsList.add(s);
			}
			return formsList;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		}
	}
}
