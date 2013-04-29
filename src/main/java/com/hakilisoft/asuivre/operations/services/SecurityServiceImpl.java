package com.hakilisoft.asuivre.operations.services;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityServiceImpl implements SecurityService {

	public SecurityServiceImpl() {
	}

	@Override
	public String encodePassword(String rawPassword) {
		PasswordEncoder encoder = new ShaPasswordEncoder(256);// Sha-256 encoding type
		return encoder.encodePassword(rawPassword, null);
	}
	
	@Override
	public String currentUser() {
		String userName = null;
		SecurityContext context = SecurityContextHolder.getContext();
		if (context != null) {
			Object principal = context.getAuthentication().getPrincipal();
			if (principal != null && principal instanceof org.springframework.security.core.userdetails.User) {
				userName = ((org.springframework.security.core.userdetails.User) principal).getUsername();
			}
		}
		return userName;		
	}

}
