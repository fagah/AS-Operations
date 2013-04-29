package com.hakilisoft.asuivre.operations.domain;

import java.io.Serializable;

public class BusinessKey implements Serializable {

	private static final long serialVersionUID = 35380376607045452L;
	
	private String name;
	
	public BusinessKey(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "BusinessKey [name=" + name + "]";
	}

}
