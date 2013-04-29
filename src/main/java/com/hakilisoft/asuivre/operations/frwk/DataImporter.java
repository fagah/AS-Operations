package com.hakilisoft.asuivre.operations.frwk;

import org.springframework.stereotype.Component;

import com.hakilisoft.asuivre.operations.services.TenantService;

/**
 * <tt>DataImporter</tt> helps import data within the application. 
 * As a Singleton, this process happens only once when the first and only instance is loaded  
 * 
 * @author cbalde
 *
 */
@Component
public class DataImporter  {
	
	private static DataImporter sInstance;
	
	
	private DataImporter() {
	}
	
	private DataImporter(TenantService tenantService, String fileName, String driver, String url, String userName, String password) {
		OAUtils.executeSql(fileName, driver, url, userName, password);
		
		tenantService.loadTenantsPool();
	}
	
	/**
	 * Singleton access from outside
	 * */
	public static DataImporter getInstance(TenantService tenantService, String fileName, String driver, String url, String userName, String password) {
		if (sInstance == null) {
			sInstance = new DataImporter(tenantService, fileName, driver, url, userName, password);
		}
		return sInstance;
	}
	
	
	

	
}
