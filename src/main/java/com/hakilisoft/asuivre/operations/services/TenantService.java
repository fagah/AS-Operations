package com.hakilisoft.asuivre.operations.services;

import java.util.List;
import java.util.Map;

import com.hakilisoft.asuivre.operations.domain.BusinessKey;
import com.hakilisoft.asuivre.operations.domain.OATenant;

public interface TenantService {

	public long countOATenants();
	
	public List<OATenant> findAllOATenants();
	
    public OATenant findOATenant(Long id);
    
    public OATenant findOATenantsByBusinessKeyEquals(String businessKey) ;
        
    public List<OATenant> findOATenantEntries(Integer firstResult, Integer maxResults);
    
    public void persist(OATenant tenant);
    
	public OATenant merge(OATenant tenant);
	
	public void flush();
    
	public void loadTenantsPool();
	
	public void clearTenantFromPool(String businessKey);
	
	public Map<String, BusinessKey> getTenantPool();
}
