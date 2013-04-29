package com.hakilisoft.asuivre.operations.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hakilisoft.asuivre.operations.services.ActivityService;

@RequestMapping("/operations/activity")
@Controller
public class ActivityController {

	private static final String SHOW_ACTIVITY_STREAM_VIEW = "operations/activity/activitystream/show";
	private static final String SHOW_SERVER_ACCESS_VIEW = "operations/activity/serveraccess/show";
	
	private static final String LIST_ACTIVITY_STREAM_VIEW = "operations/activity/activitystream/list";
	private static final String LIST_SERVER_ACCESS_VIEW = "operations/activity/serveraccess/list";
	
	
	@Autowired
	private ActivityService activityService;
	
	
    @RequestMapping(value="/activitystream", produces = "text/html")
    public String listActivityStreams(
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel) {
    	
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("activitystream", activityService.findOAActivityStreamEntries(firstResult, sizeNo));
            float nrOfPages = (float) activityService.countOAActivityStream() / sizeNo;
            uiModel.addAttribute("numberRow",activityService.countOAActivityStream());
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
            uiModel.addAttribute("activitystream", activityService.findAllOAActivityStream());
        }
        
        uiModel.addAttribute("currentNavigationvalue", "activity");
        uiModel.addAttribute("currentSiteMapvalue", "list");
        
        return LIST_ACTIVITY_STREAM_VIEW;
    }

    
    @RequestMapping(value="/serveraccess", produces = "text/html")
    public String listActivityAccess(
    		@RequestParam(value = "page", required = false) Integer page, 
    		@RequestParam(value = "size", required = false) Integer size, 
    		Model uiModel) {
    	
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("serveraccess", activityService.findOAServerAccessEntries(firstResult, sizeNo));
            float nrOfPages = (float) activityService.countOAServerAccess() / sizeNo;
            uiModel.addAttribute("numberRow",activityService.countOAServerAccess());
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
            
        } else {
            uiModel.addAttribute("serveraccess", activityService.findAllOAServerAccess());
        }
        
        uiModel.addAttribute("currentNavigationvalue", "activity");
        uiModel.addAttribute("currentSiteMapvalue", "list");
        
        return LIST_SERVER_ACCESS_VIEW;
    }


    @RequestMapping(value = "/activitystream/{id}", produces = "text/html")
    public String showActivityStream(@PathVariable("id") String id, Model uiModel) {
        uiModel.addAttribute("activitystream", activityService.findOAActivityStream(id));
        uiModel.addAttribute("itemId", id);
        return SHOW_ACTIVITY_STREAM_VIEW;
    }
    

    @RequestMapping(value = "/serveraccess/{id}", produces = "text/html")
    public String showServerAccess(@PathVariable("id") String id, Model uiModel) {
        uiModel.addAttribute("serveraccess", activityService.findOAServerAccess(id));
        uiModel.addAttribute("itemId", id);
        return SHOW_SERVER_ACCESS_VIEW;
    }
    
}
