package com.hakilisoft.asuivre.operations.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.hakilisoft.asuivre.operations.domain.OAActivityStream;
import com.hakilisoft.asuivre.operations.domain.OAServerAccess;
import com.hakilisoft.asuivre.operations.frwk.OAUtils;

public class ActivityServiceImpl implements ActivityService {

	@PersistenceContext(name=OAUtils.DB_PERSISTENCE_UNIT)
	private EntityManager entityManager;
	
	public ActivityServiceImpl() {
	}
	
	@Override
	public long countOAActivityStream() {
        return entityManager.createQuery("SELECT COUNT(o) FROM OAActivityStream o", Long.class).getSingleResult();
	}
	
	@Override
	public long countOAServerAccess() {
        return entityManager.createQuery("SELECT COUNT(o) FROM OAServerAccess o", Long.class).getSingleResult();
	}
	
	@Override
	public List<OAActivityStream> findAllOAActivityStream() {
        return entityManager.createQuery("SELECT o FROM OAActivityStream o", OAActivityStream.class).getResultList();
	}
	
	@Override
	public OAActivityStream findOAActivityStream(String id) {
        if (id == null) return null;
        return entityManager.find(OAActivityStream.class, id);
	}
	
	@Override
	public OAServerAccess findOAServerAccess(String id) {
        if (id == null) return null;
        return entityManager.find(OAServerAccess.class, id);
	}
	
	@Override
	public List<OAActivityStream> findAllOAServerAccess() {
        return entityManager.createQuery("SELECT o FROM OAServerAccess o", OAActivityStream.class).getResultList();
	}
	
	@Override
	public List<OAActivityStream> findOAActivityStreamEntries(Integer firstResult, Integer maxResults) {
        return entityManager.createQuery("SELECT o FROM OAActivityStream o", OAActivityStream.class)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
	}
	
	@Override
	public List<OAServerAccess> findOAServerAccessEntries(Integer firstResult, Integer maxResults) {
        return entityManager.createQuery("SELECT o FROM OAServerAccess o", OAServerAccess.class)
        		.setFirstResult(firstResult)
        		.setMaxResults(maxResults)
        		.getResultList();
	}
	
	@Transactional
	@Override
	public void persist(OAActivityStream activityStream) {
		this.entityManager.persist(activityStream);
	}
	
	@Transactional
	@Override
	public void persist(OAServerAccess serverAccess) {
		this.entityManager.persist(serverAccess);
	}
	
	@Transactional
	@Override
	public void logActivityStream(String event, String tenantBusinessKey, String createdBy, Date date) {
		OAActivityStream activityStream = new OAActivityStream();
		activityStream.setId(UUID.randomUUID().toString());
		activityStream.setEvent(event);
		activityStream.setTenantBusinessKey(tenantBusinessKey);
		activityStream.setCreationDate(date);
		activityStream.setCreatedBy(createdBy);
		persist(activityStream);
	}
	
	@Transactional
	@Override
	public void logServerAccess(String event, String tenantBusinessKey, String userName, Date loginDate, Date logoutDate) {
		throw new UnsupportedOperationException("Not yet suppoerted. To Be Done !!!");
	}
	

}
