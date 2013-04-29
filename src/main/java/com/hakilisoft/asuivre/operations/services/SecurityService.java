package com.hakilisoft.asuivre.operations.services;

public interface SecurityService {
	
	public String encodePassword(String rawPassword);
	
	public String currentUser();
}
