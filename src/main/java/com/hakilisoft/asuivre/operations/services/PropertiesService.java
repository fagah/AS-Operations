package com.hakilisoft.asuivre.operations.services;

import java.io.Serializable;

public class PropertiesService implements Serializable {

	private static final long serialVersionUID = 7887529394983144263L;
	
	private String dbDriverClassName;
	private String dbURL;
	private String dbUserName;
	private String dbPassword;
	private String sqlScript;
	
	
	public PropertiesService() {
	}


	public String getDbDriverClassName() {
		return dbDriverClassName;
	}


	public void setDbDriverClassName(String dbDriverClassName) {
		this.dbDriverClassName = dbDriverClassName;
	}


	public String getDbURL() {
		return dbURL;
	}


	public void setDbURL(String dbURL) {
		this.dbURL = dbURL;
	}


	public String getDbUserName() {
		return dbUserName;
	}


	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}


	public String getDbPassword() {
		return dbPassword;
	}


	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}


	public String getSqlScript() {
		return sqlScript;
	}


	public void setSqlScript(String sqlScript) {
		this.sqlScript = sqlScript;
	}
	
	

}
