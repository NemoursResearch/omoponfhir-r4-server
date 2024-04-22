package edu.gatech.chai.omoponfhir.smart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sqlite.SQLiteConfig;

public class BaseSmartOnFhir {
	final static Logger logger = LoggerFactory.getLogger(BaseSmartOnFhir.class);
	
	public Connection connect() {
		String url = System.getenv("SMARTONFHIR_DB_URI");
		if (url == null || url.isEmpty()) {
			url = "jdbc:sqlite::resource:smartonfhir.db";
		}
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		}

		return conn;
	}
}
