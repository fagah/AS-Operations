package com.hakilisoft.asuivre.operations.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "OA_GROUP", finders = { "findOAGroupsByNameEquals" })
public class OAGroup {

    @NotNull
    private String name = "ROLE_ADMIN";

    private String description = "Admin access in an organization";

    @Column(name = "TENANT_BUSINESS_KEY")
    private String tenantBusinessKey;

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationDate;

    @Column(name = "MODIFICATION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date modificationDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @ManyToMany(cascade = CascadeType.MERGE)
    private List<OAUser> users;
}
