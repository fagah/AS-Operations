package com.hakilisoft.asuivre.operations.web.forms;

import java.io.Serializable;

public class UserAssignForm implements Serializable {
	
	private static final long serialVersionUID = -2128691313663308257L;
	
	private String userName;
	private String tenantBusinessKey;
	private String groupName;

	public UserAssignForm() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTenantBusinessKey() {
		return tenantBusinessKey;
	}

	public void setTenantBusinessKey(String tenantBusinessKey) {
		this.tenantBusinessKey = tenantBusinessKey;
	}


}
