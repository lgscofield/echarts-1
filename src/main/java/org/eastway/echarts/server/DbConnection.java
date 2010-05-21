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
