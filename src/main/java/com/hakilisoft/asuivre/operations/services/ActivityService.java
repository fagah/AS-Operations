package com.hakilisoft.asuivre.operations.services;

import java.util.Date;
import java.util.List;

import com.hakilisoft.asuivre.operations.domain.OAActivityStream;
import com.hakilisoft.asuivre.operations.domain.OAServerAccess;

public interface ActivityService {
	
	public long countOAActivityStream();

    public List<OAActivityStream> findAllOAActivityStream();
    
    public List<OAActivityStream> findOAActivityStreamEntries(Integer firstResult, Integer maxResults);
    
    public OAActivityStream findOAActivityStream(String id);
    
	public void persist(OAActivityStream activityStream);
	
    public void logActivityStream(String event, String tenantBusinessKey, String createdBy, Date date);

	public long countOAServerAccess();

    public List<OAActivityStream> findAllOAServerAccess();
    
    public List<OAServerAccess> findOAServerAccessEntries(Integer firstResult, Integer maxResults);
    
    public OAServerAccess findOAServerAccess(String id);
    
	public void persist(OAServerAccess serverAccess);
	
	public void logServerAccess(String event, String tenantBusinessKey, String userName, Date loginDate, Date logoutDate);
    
}
