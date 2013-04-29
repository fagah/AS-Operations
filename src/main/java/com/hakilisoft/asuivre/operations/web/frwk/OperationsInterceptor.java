package com.hakilisoft.asuivre.operations.web.frwk;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hakilisoft.asuivre.operations.frwk.DataImporter;
import com.hakilisoft.asuivre.operations.services.PropertiesService;
import com.hakilisoft.asuivre.operations.services.TenantService;

public class OperationsInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	private PropertiesService propertiesService;

	@Autowired
	private TenantService tenantService;
	
	public OperationsInterceptor() {
	}

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		// Data can already be loaded. Happens once anyway
		DataImporter.getInstance(
				tenantService,
				propertiesService.getSqlScript(), 
				propertiesService.getDbDriverClassName(), 
				propertiesService.getDbURL(), 
				propertiesService.getDbUserName(), 
				propertiesService.getDbPassword());
		
		
		return super.preHandle(request, response, handler);
	}
}
