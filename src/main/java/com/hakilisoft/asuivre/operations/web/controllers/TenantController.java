package com.hakilisoft.asuivre.operations.web.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;

import com.hakilisoft.asuivre.operations.domain.OATenant;
import com.hakilisoft.asuivre.operations.frwk.OAUtils;
import com.hakilisoft.asuivre.operations.services.ActivityService;
import com.hakilisoft.asuivre.operations.services.SecurityService;
import com.hakilisoft.asuivre.operations.services.TenantService;

@RequestMapping("/operations/tenants")
@Controller
public class TenantController {

	
	private static final String CREATE_VIEW = "operations/tenants/create";
	private static final String SHOW_VIEW = "operations/tenants/show";
	private static final String LIST_VIEW = "operations/tenants/list";
	
	
	@Autowired
	private ActivityService activityService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private TenantService tenantService;

	
	@Transactional
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid OATenant tenant, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, tenant);
            return CREATE_VIEW;
        }
        uiModel.asMap().clear();
        
        tenant.setCreatedBy(securityService.currentUser());
        tenant.setCreationDate(new Date());
        
        tenantService.persist(tenant);
        
    	RequestContext requestContext = new RequestContext(httpServletRequest);
    	String event = requestContext.getMessage(OAUtils.OA_ACTIVITYSTREAM_TENANT_ORGANIZATION_ASSIGNMENT_MSG, new Object[]{tenant.getBusinessKey(), tenant.getName()});
		activityService.logActivityStream(event, OAUtils.OPERATIONS_DEFAULT_BK, securityService.currentUser(), new Date());
        
		tenantService.clearTenantFromPool(tenant.getBusinessKey());
		
        return "redirect:tenants?size=5";
    }

	
    @RequestMapping(params = "form", produces = "text/html")
    public String createForm(Model uiModel) {
        populateEditForm(uiModel, new OATenant());
        return CREATE_VIEW;
    }

    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("tenant", tenantService.findOATenant(id));
        uiModel.addAttribute("itemId", id);
        return SHOW_VIEW;
    }

    
    @RequestMapping(produces = "text/html")
    public String list(
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel) {
    	
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("tenants", tenantService.findOATenantEntries(firstResult, sizeNo));
            float nrOfPages = (float) tenantService.countOATenants() / sizeNo;
            uiModel.addAttribute("numberRow",tenantService.countOATenants());
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
            uiModel.addAttribute("tenants", tenantService.findAllOATenants());
        }
        
        populateEditForm(uiModel, new OATenant());
        
        uiModel.addAttribute("currentNavigationvalue", "tenants");
        uiModel.addAttribute("currentSiteMapvalue", "list");
        
        return LIST_VIEW;
    }
    
	
    private void populateEditForm(Model uiModel, OATenant tenant) {
        uiModel.addAttribute("tenant", tenant);
        uiModel.addAttribute("businessKeys", OAUtils.sortBusinessKeys(tenantService.getTenantPool().values()));
        
    }
}
