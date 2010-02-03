package org.eastway.echarts.server;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnection {
	private String name;

	public DbConnection(String name) {
		this.name = name;
	}

	public Connection getConnection() throws SQLException, NamingException {
		Context initCtx = null;
		Connection con = null;
		try {
			initCtx = new InitialContext();
			DataSource ds = (DataSource) initCtx
					.lookup(name);
			con = ds.getConnection();
			return con;
		} catch (NamingException e) {
			throw e;
		} catch (SQLException e) {
			throw e;
		}
	}
}
