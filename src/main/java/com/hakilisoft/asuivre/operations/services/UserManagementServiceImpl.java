package com.hakilisoft.asuivre.operations.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;

import com.hakilisoft.asuivre.operations.domain.OAGroup;
import com.hakilisoft.asuivre.operations.domain.OAUser;
import com.hakilisoft.asuivre.operations.domain.OAUserRoleInTenant;
import com.hakilisoft.asuivre.operations.frwk.OAUtils;

public class UserManagementServiceImpl implements UserManagementService {

	@PersistenceContext(name=OAUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;
	
	
	public UserManagementServiceImpl() {
	}

	@Override
	public long countAOUsers() {
        return entityManager.createQuery("SELECT COUNT(o) FROM OAUser o", Long.class).getSingleResult();
	}
	
	@Override
	public OAUser findOAUser(Long id) {
        if (id == null) return null;
        return entityManager.find(OAUser.class, id);
	}
	
	@Override
	public List<OAUser> findAllAOUsers() {
        return entityManager.createQuery("SELECT o FROM OAUser o", OAUser.class).getResultList();
	}
	
	@Override
	public List<OAUser> findAOUserEntries(Integer firstResult, Integer maxResults) {
        return entityManager.createQuery("SELECT o FROM OAUser o", OAUser.class)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
	}
	
	@Override
	public List<OAUser> findOAUsersByUserNameEquals(String userName) {
        if (StringUtils.isEmpty(userName)) 
        	throw new IllegalArgumentException("The 'userName' argument is required");
        
        return this.entityManager.createQuery("SELECT o FROM OAUser AS o WHERE o.userName = :userName", OAUser.class)
        	.setParameter("userName", userName)
        	.getResultList();
	}
	
	@Override
	public List<OAUserRoleInTenant> findOAUserRoleInTenantsByUserNameEquals(String userName) {
        if (StringUtils.isEmpty(userName)) 
        	throw new IllegalArgumentException("The userName argument is required");
        
        return entityManager.createQuery("SELECT o FROM OAUserRoleInTenant AS o WHERE o.userName = :userName", OAUserRoleInTenant.class)
        	.setParameter("userName", userName)
        	.getResultList();
	}
	
	@Override
	public List<OAUserRoleInTenant> findCombinationOAUserRoleInTenants(String tenantBusinessKey, String groupName) {
		List<OAUserRoleInTenant> userRoleInTenants = null;
		if (tenantBusinessKey == null && groupName == null) {
			userRoleInTenants=  entityManager.createQuery("SELECT o FROM OAUser o", OAUserRoleInTenant.class).getResultList();
			
		} else if (tenantBusinessKey == null && groupName != null) {
			userRoleInTenants = entityManager.createQuery("SELECT o FROM OAUserRoleInTenant AS o WHERE o.groupName = :groupName", OAUserRoleInTenant.class)
	        		.setParameter("groupName", groupName)
	        		.getResultList();
			
		} else if (tenantBusinessKey != null && groupName == null) {
			userRoleInTenants = entityManager.createQuery("SELECT o FROM OAUserRoleInTenant AS o WHERE o.tenantBusinessKey = :tenantBusinessKey", OAUserRoleInTenant.class)
	        		.setParameter("tenantBusinessKey", tenantBusinessKey)
	        		.getResultList();
		} else {
			userRoleInTenants = entityManager.createQuery("SELECT o FROM OAUserRoleInTenant AS o WHERE o.tenantBusinessKey = :tenantBusinessKey AND o.groupName = :groupName", OAUserRoleInTenant.class)
	            	.setParameter("tenantBusinessKey", tenantBusinessKey)
	            	.setParameter("groupName", groupName)
	            	.getResultList();
		}
		
		return userRoleInTenants;
	}
	

	@Override
	public OAUser merge(OAUser user) {
		OAUser merged = this.entityManager.merge(user);
        this.entityManager.flush();
        return merged;
	}
	
	@Override
	public void persist(OAUserRoleInTenant userRoleInTenant) {
		this.entityManager.persist(userRoleInTenant);
	}
	
	@Override
	public void persist(OAUser user) {
		this.entityManager.persist(user);
	}
	
	
	@Override
	public long countAOGroups() {
        return entityManager.createQuery("SELECT COUNT(o) FROM OAGroup o", Long.class).getSingleResult();
	}

	@Override
	public List<OAGroup> findAOGroupEntries(Integer firstResult, Integer maxResults) {
        return entityManager.createQuery("SELECT o FROM OAGroup o", OAGroup.class)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
	}
	
	@Override
	public List<OAGroup> findAllAOGroups() {
        return entityManager.createQuery("SELECT o FROM OAGroup o", OAGroup.class).getResultList();
	}
	
	@Override
	public OAGroup findOAGroup(Long id) {
        if (id == null) return null;
        return entityManager.find(OAGroup.class, id);
	}
	
	
	@Override
	public OAGroup findOAGroupsByNameEquals(String name) {
        if (name == null || name.length() == 0) 
        	throw new IllegalArgumentException("The 'name' argument is required");
        
        return this.entityManager.createQuery("SELECT o FROM OAGroup AS o WHERE o.name = :name", OAGroup.class)
        	.setParameter("name", name)
        	.getSingleResult();
	}
	
	
	@Override
	public OAGroup merge(OAGroup group) {
		OAGroup merged = this.entityManager.merge(group);
        this.entityManager.flush();
        return merged;
	}
	
	@Override
	public void persist(OAGroup group) {
		this.entityManager.persist(group);
	}
	
	
}
