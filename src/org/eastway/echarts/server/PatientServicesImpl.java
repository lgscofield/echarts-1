package org.eastway.echarts.server;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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

import com.google.gwt.user.server.Base64Utils;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class PatientServicesImpl extends RemoteServiceServlet implements
		PatientServices, DbConstants {

	@Override
	public String validateUser(String username, String password) throws DbException {
		String sql = "SELECT * FROM [User] WHERE username = '" + username
				+ "' and password = HASHBYTES('SHA1','" + password + "')";

		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;
		String sessionId = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			if (srs.first()) {
				sessionId = createSessionId();
				storeSessionId(sessionId, username);
			} else {
				sessionId = null;
			}
			return sessionId;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}
	}

	private void storeSessionId(String sessionId, String username) throws DbException {
		Date t = new Date();
		long expire = t.getTime() + 3600000; // 60 minutes
		String sql = "UPDATE [User] SET SessionId='" + sessionId + "', SessionIdExpire=" + expire + " WHERE Username='" + username + "'";
		Connection con = null;
		Statement stmt = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} catch (SQLException e) {
			throw new DbException();
		}
	}

	private String createSessionId() {
		InputStream in;
		byte[] seed = new byte[16];;
		String sessionId = null;

		try {
			// We seed Random() with /dev/random on linux servers;
			// for Windows, we seed with the current timestamp
			in = new FileInputStream("/dev/random");
			DataInputStream dataIn = new DataInputStream(in);
			for (int i = 0; i < 16; i++)
				seed[i] = dataIn.readByte();
		} catch (FileNotFoundException e) {
			Random random = new Random();
			random.nextBytes(seed);
		} catch (IOException e) {
			Random random = new Random();
			random.nextBytes(seed);
		}

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(seed);
			sessionId = Base64Utils.toBase64(md.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
		return sessionId;
	}

	@Override
	public Patient getPatient(String patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		String sql = "SELECT * FROM VDisplayDemographics WHERE PATID = "
			+ patientId;
		Patient p = new Patient();
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			if (srs.next()) {
				p.setAlias(srs.getString("Alias"));
				p.setAllergies(srs.getString("Allergies"));
				p.setCaseStatus(srs.getString("CaseStatus"));
				p.setDob(srs.getDate("DOB"));
				p.setEducationLevel(srs.getString("EducationLevel"));
				p.setEducationType(srs.getString("EducationType"));
				p.setEmployment(srs.getString("Employment"));
				p.setFirstName(srs.getString("FirstName"));
				p.setGender(srs.getString("Gender"));
				p.setIncomeSource1(srs.getString("IncomeSource1"));
				p.setIncomeSource2(srs.getString("IncomeSource2"));
				p.setIncomeSource3(srs.getString("IncomeSource3"));
				p.setLastEdit(srs.getDate("LastEdit"));
				p.setLastEditBy(srs.getString("LastEditBy"));
				p.setLastName(srs.getString("LastName"));
				p.setLivingArrangement(srs.getString("LivingArrangement"));
				p.setMaritalStatus(srs.getString("MaritalStatus"));
				p.setName(srs.getString("Name"));
				p.setPatientId(srs.getString("PATID"));
				p.setRace(srs.getString("Race"));
				p.setSp_alcoholdrug(srs.getBoolean("SP_AlcoholDrug"));
				p.setSp_blind(srs.getBoolean("SP_Blind"));
				p.setSp_childalcdrug(srs.getBoolean("SP_ChildAlcDrug"));
				p.setSp_dd(srs.getBoolean("SP_DD"));
				p.setSp_deaf(srs.getBoolean("SP_Deaf"));
				p.setSp_domesticviolence(srs.getBoolean("SP_DomesticViolence"));
				p.setSp_duidwi(srs.getBoolean("SP_DUIDWI"));
				p.setSp_forensic(srs.getBoolean("SP_Forensic"));
				p.setSp_generalpopulation(srs.getBoolean("SP_GeneralPopulation"));
				p.setSp_hearingimpaired(srs.getBoolean("SP_HearingImpaired"));
				p.setSp_hivaids(srs.getBoolean("SP_HIVAIDS"));
				p.setSp_mimr(srs.getBoolean("SP_MIMR"));
				p.setSp_phydisabled(srs.getBoolean("SP_PhyDisabled"));
				p.setSp_physicalabuse(srs.getBoolean("SP_PhysicalAbuse"));
				p.setSp_probationparole(srs.getBoolean("SP_ProbationParole"));
				p.setSp_schooldropout(srs.getBoolean("SP_SchoolDropout"));
				p.setSp_sexualabuse(srs.getBoolean("SP_SexualAbuse"));
				p.setSp_smd(srs.getBoolean("SP_SMD"));
				p.setSp_speechimpaired(srs.getBoolean("SP_SpeechImpaired"));
				p.setSp_suicidal(srs.getBoolean("SP_Suicidal"));
				p.setSp_visuallyimpaired(srs.getBoolean("SP_VisuallyImpaired"));
				p.setSsn(srs.getString("SSN"));
				p.setSuffix(srs.getString("Suffix"));
				p.setVeteran(srs.getBoolean("Veteran"));
			}
			return p;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
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
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
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
			sql = "SELECT VTicklerList.PATID, Demographics.Name, VTicklerList.ItemName, VTicklerList.[Date] FROM VTicklerList INNER JOIN Demographics ON VTicklerList.PATID = Demographics.PATID WHERE [Date] IS NOT NULL AND VTicklerList.PATID IN (SELECT PATID FROM Assignments WHERE Staff = '"
				+ staffId + "' AND Disposition = 1) ORDER BY 4 ASC";
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}
	}

	@Override
	public Messages getMessages(String patientId, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		Messages msgs = new Messages();

		String sql = "SELECT ID, PATID, [Descriptor] as 'MessageType', CAST(MessageDate As DateTime) As MessageDate, [Message] as 'Content', [ParentID], StaffName FROM [Messages] INNER JOIN VMessageType ON [Messages].MessageType = VMessageType.MessageType INNER JOIN [User] ON [Messages].LastEditBy = [User].Staff WHERE PATID ="
				+ patientId;
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
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
			throw new DbException();
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
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

			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DbException("Error adding message", e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}
	}

	private String getStaffId(String sessionId) throws SQLException, NamingException {
		String sql = "SELECT Staff FROM [User] WHERE SessionId = '"
					+ sessionId + "'";
		String staffId = null;

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			while(rs.next())
				staffId = rs.getString("Staff");
			return staffId;
		} catch (SQLException e) {
			throw e;
		} catch (NamingException e) {
			throw e;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw e;
			}
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
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}
	}

	private int getMessageType(String messageType) throws SQLException, DbException {
		String sql = "SELECT * FROM VMessageType WHERE Descriptor ='"
				+ messageType + "'";
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw e;
			}
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
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
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
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}

	}

	@Override
	public String getTicket(Integer ticketNumber, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		String sql = "SELECT TemplateString FROM ServiceCodes INNER JOIN TicketTemplate ON ServiceCodes.TemplateID = TicketTemplate.TemplateID WHERE [Service] = '390'";
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;
		String ticketString = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next()) {
				ticketString = srs.getString(1);
			}
			return ticketString;
		} catch (SQLException e) {
			throw new DbException(e);
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}
	}

	@Override
	public String getProgressNoteBody(String service, String sessionId)
			throws SessionExpiredException, DbException {
		checkSessionExpire(sessionId);
		String sql = "SELECT TemplateString FROM TicketTemplate WHERE TemplateID = "
				+ service;
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;
		String progressNoteBody = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			while (srs.next()) {
				progressNoteBody = srs.getString("TemplateString");
			}
			return progressNoteBody;
		} catch (SQLException e) {
			throw new DbException();
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}
	}

	private boolean checkSessionExpire(String sessionId) throws SessionExpiredException, DbException {
		String sql = "SELECT SessionIdExpire FROM [User] WHERE SessionId = '" + sessionId + "'";
		Date t = new Date();
		long expire = 0;
		Connection con = null;
		Statement stmt = null;
		ResultSet srs = null;

		try {
			DbConnection dbc = new DbConnection(jndiRes);
			con = dbc.getConnection();
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			srs = stmt.executeQuery(sql);
			if (srs.first()) {
				expire = srs.getLong("SessionIdExpire");
			}
		} catch (SQLException e) {
			throw new DbException();
		} catch (NamingException e) {
			throw new DbException("Naming exception");
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException();
			}
		}

		if (t.getTime() > expire) {
			throw new SessionExpiredException();
		} else {
			return true;
		}
	}
}
