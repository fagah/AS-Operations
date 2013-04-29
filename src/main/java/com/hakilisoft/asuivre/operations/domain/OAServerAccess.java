package com.hakilisoft.asuivre.operations.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="OA_SERVER_ACCESS")
public class OAServerAccess implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Version
	private Integer version;
	
	@NotNull
	@Column(name="USER_NAME")
	private String userName;
	
	@NotNull
    @Column(name = "LOGIN_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
	private Date loginTime;
	
    @Column(name = "LOGOUT_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
	private Date logoutTime;
	
	
	public OAServerAccess() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public Integer getVersion() {
		return version;
	}


	public void setVersion(Integer version) {
		this.version = version;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Date getLoginTime() {
		return loginTime;
	}


	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}


	public Date getLogoutTime() {
		return logoutTime;
	}


	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}


	@Override
	public String toString() {
		return "AOServerAccess [userName=" + userName + ", loginTime="
				+ loginTime + ", logoutTime=" + logoutTime + "]";
	}

	
}
