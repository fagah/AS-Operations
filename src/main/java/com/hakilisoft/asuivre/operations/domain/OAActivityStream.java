package com.hakilisoft.asuivre.operations.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="OA_ACTIVITY_STREAM")
public class OAActivityStream implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Version
	private Integer version;
	  
    @NotNull
    private String event;

    @NotNull
    private String createdBy;

    @NotNull
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationDate;
    
    @Column(name = "TENANT_BUSINESS_KEY")
    private String tenantBusinessKey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getTenantBusinessKey() {
		return tenantBusinessKey;
	}

	public void setTenantBusinessKey(String tenantBusinessKey) {
		this.tenantBusinessKey = tenantBusinessKey;
	}    
    

	@Override
	public String toString() {
		return "AOActivityStream [event=" + event + ", createdBy=" + createdBy
				+ ", creationDate=" + creationDate + ", tenantBusinessKey="
				+ tenantBusinessKey + "]";
	}
    
}
