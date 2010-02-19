package org.eastway.echarts.server;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnection {
	private static Connection con;

	private DbConnection() throws NamingException, SQLException {
		Context initCtx = null;
		try {
			initCtx = new InitialContext();
			DataSource ds = (DataSource) initCtx
					.lookup(DbConstants.jndiRes);
			con = ds.getConnection();
		} catch (NamingException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
	}

	public static Connection getConnection() throws NamingException, SQLException {
		if (con == null) {
			new DbConnection();
		}
		return con;
	}
}
