package com.hakilisoft.asuivre.operations.web.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContext;

import com.hakilisoft.asuivre.operations.domain.OAGroup;
import com.hakilisoft.asuivre.operations.domain.OATenant;
import com.hakilisoft.asuivre.operations.domain.OAUser;
import com.hakilisoft.asuivre.operations.domain.OAUserRoleInTenant;
import com.hakilisoft.asuivre.operations.frwk.OAUtils;
import com.hakilisoft.asuivre.operations.services.ActivityService;
import com.hakilisoft.asuivre.operations.services.SecurityService;
import com.hakilisoft.asuivre.operations.services.TenantService;
import com.hakilisoft.asuivre.operations.services.UserManagementService;
import com.hakilisoft.asuivre.operations.web.forms.UserAssignForm;


@RequestMapping("/operations/usermanagement")
@Controller
public class UserManagementController {

	private static final String CREATE_USER_VIEW = "operations/usermanagement/users/create";
	private static final String SHOW_USER_VIEW = "operations/usermanagement/users/show";
	private static final String LIST_USER_VIEW = "operations/usermanagement/users/list";
	private static final String ASSIGN_TENANT_GROUP_USER_VIEW = "operations/usermanagement/users/assigntenantgroup";
	
	private static final String CREATE_GROUP_VIEW = "operations/usermanagement/groups/create";
	private static final String SHOW_GROUP_VIEW = "operations/usermanagement/groups/show";
	private static final String LIST_GROUP_VIEW = "operations/usermanagement/groups/list";
	

	@Autowired
	private UserManagementService userManagementService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private TenantService tenantService;
	
	
    @RequestMapping(value="/user", method = RequestMethod.POST, produces = "text/html")
    public String createUser(@Valid OAUser user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
        	populateUserEditForm(uiModel, user);
            return CREATE_USER_VIEW;
        }
        uiModel.asMap().clear();
        
        List<OAUser> users = userManagementService.findOAUsersByUserNameEquals(user.getUserName());
        if (users.isEmpty()) {
            String password = securityService.encodePassword(user.getPassword());
            user.setPassword(password);
            user.setCreatedBy(securityService.currentUser());
            user.setCreationDate(new Date());
            
            userManagementService.persist(user);
            
        	RequestContext requestContext = new RequestContext(httpServletRequest);
        	String event = requestContext.getMessage(OAUtils.OA_ACTIVITYSTREAM_USER_CREATION_MSG, new Object[]{user.getUserName()});
        	activityService.logActivityStream(event, OAUtils.OPERATIONS_DEFAULT_BK, securityService.currentUser(), new Date());
		}
        
        return "redirect:users?size=5";
    }
    
    
    @RequestMapping(value="/users", produces = "text/html")
    public String listUsers(
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel) {
    	
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            
        	List<OAUser> users = enrichUsersView(userManagementService.findAOUserEntries(firstResult, sizeNo));
        	
        	////////////
//        	List<OAUser> enrichedUsers = new ArrayList<OAUser>();
//        	for (OAUser user : users) {
//        		
//        		String tbkGr = ""; 
//        		
//        		List<OAUserRoleInTenant> urts = userManagementService.findOAUserRoleInTenantsByUserNameEquals(user.getUserName());
//        		for (OAUserRoleInTenant urt : urts) {
//					String tbk = urt.getTenantBusinessKey();
//					String gr = urt.getGroupName();
//        			
//					tbkGr +=  tbk + " / " + gr + ", "; 
//				}
//        		
//        		if (!StringUtils.isEmpty(tbkGr)) {
//            		String str = tbkGr.substring(0,tbkGr.length()-2);
//            		user.setTenantGroupNames(str);
//				} 
//        		
//        		
//        		enrichedUsers.add(user);
//        		
//			}
        	//////////////
        	
            uiModel.addAttribute("users", users);
            
            
//            uiModel.addAttribute("users",  userManagementService.findAOUserEntries(firstResult, sizeNo));
            
            
            float nrOfPages = (float) userManagementService.countAOUsers() / sizeNo;
            uiModel.addAttribute("numberRow",userManagementService.countAOUsers());
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
        	
        	// TODO - REVIEW - Performance
        	List<OAUser> users = enrichUsersView(userManagementService.findAllAOUsers());
//        	List<OAUser> enrichedUsers = new ArrayList<OAUser>();
//        	for (OAUser user : users) {
//        		
//        		String tbkGr = ""; 
//        		
//        		List<OAUserRoleInTenant> urts = userManagementService.findOAUserRoleInTenantsByUserNameEquals(user.getUserName());
//        		for (OAUserRoleInTenant urt : urts) {
//					String tbk = urt.getTenantBusinessKey();
//					String gr = urt.getGroupName();
//        			
//					tbkGr +=  tbk + " / " + gr + ", "; 
//				}
//        		
//        		user.setTenantGroupNames(tbkGr);
//        		
//			}
        	
            uiModel.addAttribute("users", users);
        }
        
        populateUserEditForm(uiModel, new OAUser());
        
        uiModel.addAttribute("currentNavigationvalue", "users");
        uiModel.addAttribute("currentSiteMapvalue", "list");
        
        return LIST_USER_VIEW;
    }
    
    ///////////////////////
    private List<OAUser> enrichUsersView(List<OAUser> users) {
    	List<OAUser> enrichedUsers = new ArrayList<OAUser>();
    	for (OAUser user : users) {
    		String tenantsGroups= ""; 
    		List<OAUserRoleInTenant> urts = userManagementService.findOAUserRoleInTenantsByUserNameEquals(user.getUserName());
    		for (OAUserRoleInTenant urt : urts) {
				String tbk = urt.getTenantBusinessKey();
				String gr = urt.getGroupName();
				tenantsGroups +=  tbk + " / " + gr + ", "; 
			}
    		if (!StringUtils.isEmpty(tenantsGroups)) {
        		String str = tenantsGroups.substring(0,tenantsGroups.length()-2);
        		user.setTenantGroupNames(str);
			} 
    		
    		enrichedUsers.add(user);
		}
    	
    	return enrichedUsers;
    }
    
    
    @RequestMapping(value="/group", method = RequestMethod.POST, produces = "text/html")
    public String createGroup(@Valid OAGroup group, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateGroupEditForm(uiModel, group);
            return CREATE_GROUP_VIEW;
        }
        uiModel.asMap().clear();
        
        group.setCreatedBy(securityService.currentUser());
        group.setCreationDate(new Date());
        
        userManagementService.persist(group);
        
    	RequestContext requestContext = new RequestContext(httpServletRequest);
    	String event = requestContext.getMessage(OAUtils.OA_ACTIVITYSTREAM_GROUP_CREATION_MSG, new Object[]{group.getName()});
    	activityService.logActivityStream(event, OAUtils.OPERATIONS_DEFAULT_BK, securityService.currentUser(), new Date());
        
        return "redirect:groups?size=5";
    }
    
    
    @RequestMapping(value="/groups", produces = "text/html")
    public String listGroups(
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel) {
    	
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("groups",  userManagementService.findAOGroupEntries(firstResult, sizeNo));
            float nrOfPages = (float) userManagementService.countAOGroups() / sizeNo;
            uiModel.addAttribute("numberRow",userManagementService.countAOGroups());
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
            uiModel.addAttribute("groups", userManagementService.findAllAOGroups());
        }
        
        populateGroupEditForm(uiModel, new OAGroup());
        
        uiModel.addAttribute("currentNavigationvalue", "groups");
        uiModel.addAttribute("currentSiteMapvalue", "list");
        
        return LIST_GROUP_VIEW;
    }    
    
    
//    @Transactional
//    @RequestMapping(value = "/assigntenant/{userId}/{businessKey}", produces = "text/html")
//    public String assignTenantToUser(
//    		@PathVariable("userId") Long id, 
//    		@PathVariable("businessKey") String businessKey, 
//    		Model uiModel) {
//    	
//    	OAUser user = userManagementService.findOAUser(id);
//    	user.setTenantBusinessKey(businessKey);
//    	
//    	OATenant tenant = tenantService.findOATenantsByBusinessKeyEquals(businessKey);
//    	user.setTenantName(tenant.getName());
//
//    	tenant.getUsers().add(user);
//    	user.getTenants().add(tenant);
//    	
//    	tenantService.merge(tenant);
//    	
//        return "redirect:/operations/usermanagement/users?size=5";
//    }
    

    @RequestMapping(value = "/users/assignmentform")
    public String assignmentForm(Model uiModel){
    	
    	uiModel.addAttribute("userAssignForm", new UserAssignForm());
    	uiModel.addAttribute("userNames", retreiveAllUserNames());
    	uiModel.addAttribute("tenantBusinessKeys", retrieveAllTenantBusinessKeys());
    	uiModel.addAttribute("groupNames", retreiveAllGroupNames());
    	
        uiModel.addAttribute("currentNavigationvalue", "users");
        uiModel.addAttribute("currentSiteMapvalue", "list");
    	
    	return ASSIGN_TENANT_GROUP_USER_VIEW;
    }
    
    
    @Transactional
    @RequestMapping(value = "/users/assigntenantsgroups")
    public String assignTenantsGroups(
    		@ModelAttribute(value="userAssignForm") UserAssignForm form,
    		BindingResult result, 
    		Model uiModel, HttpServletRequest httpServletRequest){
    	
		OAUser user = null;
		
    	String action = httpServletRequest.getParameter("action");
    	if ("assign".equals(action)) {
    		
    		String userName = form.getUserName();
    		
    		if (!StringUtils.isEmpty(userName)) {
				user = userManagementService.findOAUsersByUserNameEquals(userName).get(0);
			}
    		
    		String groupName = form.getGroupName();
    		
    		user = assignUserGroup(user, groupName);
    		
    		String tenantBusinessKey = form.getTenantBusinessKey();
    		
    		user = assignUserTenant(user, tenantBusinessKey);
    		
    		user = saveUserRoleInTenant(user, userName, groupName, tenantBusinessKey);
    		
		} else if ("search".equals(action)) {
			
			
			
			// TODO - TBD via AJAX
		}
    	
    	List<OAUserRoleInTenant> userRoleInTenants = null;
    	if (!StringUtils.isEmpty(form.getUserName())) {
    		userRoleInTenants = userManagementService.findOAUserRoleInTenantsByUserNameEquals(form.getUserName());
		} 
    	
    	uiModel.addAttribute("userAssignForm", new UserAssignForm());
    	uiModel.addAttribute("userNames", retreiveAllUserNames());
    	uiModel.addAttribute("tenantBusinessKeys", retrieveAllTenantBusinessKeys());
    	uiModel.addAttribute("groupNames", retreiveAllGroupNames());
    	
    	uiModel.addAttribute("userRoleInTenants", userRoleInTenants);
    	
    	if (userRoleInTenants != null && !userRoleInTenants.isEmpty()) {
        	uiModel.addAttribute("assignedUser", user);
		}

        uiModel.addAttribute("currentNavigationvalue", "users");
        uiModel.addAttribute("currentSiteMapvalue", "list");
        
    	return ASSIGN_TENANT_GROUP_USER_VIEW;
    }
    
    
    private void populateUserEditForm(Model uiModel, OAUser user) {
        uiModel.addAttribute("user", user);
    }
    
    
    private void populateGroupEditForm(Model uiModel, OAGroup group) {
        uiModel.addAttribute("group", group);
    }
    
    private List<String> retreiveAllUserNames() {
    	List<String> userNames = new ArrayList<String>();
    	List<OAUser> users = userManagementService.findAllAOUsers();
		userNames.add("");
    	for (OAUser user : users) {
			userNames.add(user.getUserName());
		}
    	return userNames;
    }
    
    private List<String> retrieveAllTenantBusinessKeys() {
    	List<String> tenantBusinessKeys = new ArrayList<String>();
    	List<OATenant> tenants = tenantService.findAllOATenants();
		tenantBusinessKeys.add("");
    	for (OATenant tenant : tenants) {
    		tenantBusinessKeys.add(tenant.getBusinessKey());
		}
    	
    	return tenantBusinessKeys;
    }
    
    private List<String> retreiveAllGroupNames() {
    	List<String> groupNames =  new ArrayList<String>();
    	List<OAGroup> groups = userManagementService.findAllAOGroups();
		groupNames.add("");
    	for (OAGroup group : groups) {
			groupNames.add(group.getName());
		}
    	return groupNames;
    }

    private OAUser assignUserGroup(OAUser user, String groupName) {
		if (!StringUtils.isEmpty(groupName)) {
			OAGroup group = userManagementService.findOAGroupsByNameEquals(groupName);
			if (user != null) {
				boolean hasRole = false;
				List<OAUser> users = group.getUsers();
				for (OAUser oaUser : users) {
					if (oaUser.getUserName().equals(user.getUserName())) {
						hasRole = true;
						break;
					}
				}
				if (!hasRole) {
					user.setGroupName(groupName);
					user.getGroups().add(group);
					group.getUsers().add(user);
					userManagementService.merge(group);
				}
			}
		}
		
		return user;
    }
    
    private OAUser assignUserTenant(OAUser user, String tenantBusinessKey) {
		if (!StringUtils.isEmpty(tenantBusinessKey)) {
			OATenant tenant = tenantService.findOATenantsByBusinessKeyEquals(tenantBusinessKey);
			if (user != null) {
				boolean hasTenant = false;
				List<OATenant> tenants = user.getTenants();
				for (OATenant oaTenant : tenants) {
					if (oaTenant.getBusinessKey().equals(tenantBusinessKey)) {
						hasTenant = true;
						break;
					}
				}
				
				if (!hasTenant) {
					user.setTenantBusinessKey(tenantBusinessKey);
					user.setTenantName(tenant.getName());
					user.getTenants().add(tenant);
					tenant.getUsers().add(user);
					tenantService.merge(tenant);
				}
			}
		}
		
		return user;
    }
    
    private OAUser saveUserRoleInTenant(OAUser user, String userName, String groupName, String tenantBusinessKey) {
		if (user != null) {
			List<OAUserRoleInTenant>  userRoleInTenants = userManagementService.findCombinationOAUserRoleInTenants(tenantBusinessKey, groupName);
			if (userRoleInTenants.isEmpty()) {
				OAUserRoleInTenant urt = new OAUserRoleInTenant();
				urt.setUserName(userName);
				urt.setGroupName(groupName);
				urt.setTenantBusinessKey(tenantBusinessKey);
				userManagementService.persist(urt);
			}
		}
    	return user;
    }
    
    
}
