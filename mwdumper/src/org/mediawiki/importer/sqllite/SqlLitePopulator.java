package org.mediawiki.importer.sqllite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.mediawiki.parser.ParseResult;

public class SqlLitePopulator {

	Connection conn;
	PreparedStatement insertIntoDictionary;

	public SqlLitePopulator() {

		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:dictionary.db");
			
			Statement stat = conn.createStatement();
		    stat.executeUpdate("drop table if exists dictionary;");
		    stat.executeUpdate("create table dictionary (title, body);");
		    
		    insertIntoDictionary = conn.prepareStatement("insert into dictionary (title, body) values (?, ?);");
			
		} catch (SQLException e) {
			throw new ExceptionInInitializerError(e);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public void store(ParseResult result) throws SQLException {
		insertIntoDictionary.setString(1, result.getTitle());
		insertIntoDictionary.setString(2, result.getBody());
		insertIntoDictionary.execute();
	}

}
