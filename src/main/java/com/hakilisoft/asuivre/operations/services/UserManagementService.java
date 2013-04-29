package com.hakilisoft.asuivre.operations.services;

import java.util.List;

import com.hakilisoft.asuivre.operations.domain.OAGroup;
import com.hakilisoft.asuivre.operations.domain.OAUser;
import com.hakilisoft.asuivre.operations.domain.OAUserRoleInTenant;

public interface UserManagementService {

	// USERS
	public long countAOUsers();
	
	public List<OAUser> findAOUserEntries(Integer firstResult, Integer maxResults);
	
	public List<OAUser> findAllAOUsers();
	
	public OAUser findOAUser(Long id);
	
    public List<OAUser> findOAUsersByUserNameEquals(String userName);
    
    public List<OAUserRoleInTenant> findOAUserRoleInTenantsByUserNameEquals(String userName);
    
    public List<OAUserRoleInTenant> findCombinationOAUserRoleInTenants(String tenantBusinessKey, String groupName);
	
	public void persist(OAUser user);
	
	public void persist(OAUserRoleInTenant userRoleInTenant);
	
	public OAUser merge(OAUser user);
	
	
	// GROUPS
	public long countAOGroups();
	
	public List<OAGroup> findAOGroupEntries(Integer firstResult, Integer maxResults);
	
	public List<OAGroup> findAllAOGroups();
	
	public OAGroup findOAGroup(Long id);
	
    public OAGroup findOAGroupsByNameEquals(String name);
    
	public void persist(OAGroup group);
	
	public OAGroup merge(OAGroup group);
	
	
	
}
