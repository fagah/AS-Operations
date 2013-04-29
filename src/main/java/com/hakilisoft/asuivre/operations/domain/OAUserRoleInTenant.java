package com.hakilisoft.asuivre.operations.domain;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "OA_USER_ROLE_IN_TENANT", finders = { "findOAUserRoleInTenantsByUserNameEquals" })
public class OAUserRoleInTenant {

    @NotNull
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "TENANT_BUSINESS_KEY")
    private String tenantBusinessKey;

    @Column(name = "GROUP_NAME")
    private String groupName;
}
