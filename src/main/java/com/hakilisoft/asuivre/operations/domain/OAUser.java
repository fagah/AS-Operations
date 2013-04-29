package com.hakilisoft.asuivre.operations.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "OA_USER", finders = { "findOAUsersByUserNameEquals" })
public class OAUser {

    @NotNull
    @Column(name = "USER_NAME", unique=true)
    private String userName = "dino";

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName = "Dino";

    @NotNull
    @Column(name = "LAST_NAME")
    private String lastName = "Kelly";

    @NotNull
    private String password = "dino";

    @Transient
    @NotNull
    private String confirmPassword = "dino";

    @NotNull
    private String email = "dino@home.com";

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "TENANT_NAME")
    private String tenantName;

    
    
    @Transient
    private String tenantGroupNames;
    
    
    
    
    
    @Column(name = "TENANT_BUSINESS_KEY")
    private String tenantBusinessKey;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    @Column(name = "MODIFICATION_DATE")
    private Date modificationDate;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    @ManyToMany
    private List<OATenant> tenants;

    @ManyToMany
    private List<OAGroup> groups;
}
