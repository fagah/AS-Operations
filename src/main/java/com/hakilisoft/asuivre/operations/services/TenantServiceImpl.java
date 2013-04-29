package com.hakilisoft.asuivre.operations.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.hakilisoft.asuivre.operations.domain.BusinessKey;
import com.hakilisoft.asuivre.operations.domain.OATenant;
import com.hakilisoft.asuivre.operations.frwk.OAUtils;

public class TenantServiceImpl implements TenantService {

	private static Logger logger = Logger.getLogger(TenantServiceImpl.class);
	
	@PersistenceContext(name=OAUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;
	
	private Map<String, BusinessKey> tenantPool = new HashMap<String, BusinessKey>();
	
	
	public TenantServiceImpl() {
	}
	
	@Override
	public void loadTenantsPool() {
		logger.info("init() - Loading available Business Keys = Tenant Datasource configurations...");
		addTenantToPool(OAUtils.DB_DATASOURCE_TENANT_ID_1, new BusinessKey(OAUtils.DB_DATASOURCE_TENANT_ID_1));
		addTenantToPool(OAUtils.DB_DATASOURCE_TENANT_ID_2, new BusinessKey(OAUtils.DB_DATASOURCE_TENANT_ID_2));
		addTenantToPool(OAUtils.DB_DATASOURCE_TENANT_ID_3, new BusinessKey(OAUtils.DB_DATASOURCE_TENANT_ID_3));
		addTenantToPool(OAUtils.DB_DATASOURCE_TENANT_ID_4, new BusinessKey(OAUtils.DB_DATASOURCE_TENANT_ID_4));
	}
	
	@Override
	public void clearTenantFromPool(String businessKey) {
		removeTenantFromPool(businessKey);
	}
	
	@Override
	public long countOATenants() {
        return entityManager.createQuery("SELECT COUNT(o) FROM OATenant o", Long.class).getSingleResult();
	}
	
	@Override
	public List<OATenant> findAllOATenants() {
        return entityManager.createQuery("SELECT o FROM OATenant o", OATenant.class).getResultList();
	}
	
	@Override
	public OATenant findOATenant(Long id) {
        if (id == null) return null;
        return entityManager.find(OATenant.class, id);
	}
    	
	@Override
	public List<OATenant> findOATenantEntries(Integer firstResult, Integer maxResults) {
        return entityManager.createQuery("SELECT o FROM OATenant o", OATenant.class)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
	}
	
	@Override
	public OATenant findOATenantsByBusinessKeyEquals(String businessKey) {
        if (businessKey == null || businessKey.length() == 0) 
        	throw new IllegalArgumentException("The 'businessKey' argument is required");
        
        return entityManager.createQuery("SELECT o FROM OATenant AS o WHERE o.businessKey = :businessKey", OATenant.class)
        		.setParameter("businessKey", businessKey)
        		.getSingleResult();
	}
	
	@Transactional
	@Override
	public OATenant merge(OATenant tenant) {
		OATenant merged = this.entityManager.merge(tenant);
        this.entityManager.flush();
        return merged;
	}
	
	@Transactional
	@Override
	public void persist(OATenant tenant) {
		this.entityManager.persist(tenant);
	}
	
	@Override
	public void flush() {
		this.entityManager.flush();
	}

    synchronized private void addTenantToPool(String key, BusinessKey value) {
    	List<String> businessKeys = new ArrayList<String>();
    	List<OATenant> tenants = findAllOATenants();
    	for (OATenant oaTenant : tenants) {
    		businessKeys.add(oaTenant.getBusinessKey());
		}
    	
    	if (!businessKeys.contains(key)) {
    		tenantPool.put(key, value);		
		}
    }
    
    synchronized private void removeTenantFromPool(String tenantBusinessKey) {
    	tenantPool.remove(tenantBusinessKey);
    }

	public synchronized Map<String, BusinessKey> getTenantPool() {
		return tenantPool;
	}
    
    
	
}
