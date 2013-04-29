-- ================
-- some test data
-- ================

--OA_TENANT
--ID  	BUSINESS_KEY  	CREATED_BY  	CREATION_DATE  	MODIFICATION_DATE  	MODIFIED_BY  	NAME  	VERSION  
insert into OA_TENANT values (1, 'tenantDS1', 'admin', '2013-04-26 10:34:13.134', null, null, 'Ministere Interieur', 1);

--OA_GROUP
--ID  	CREATED_BY  	CREATION_DATE  	DESCRIPTION  	MODIFICATION_DATE  	NAME  	TENANT_BUSINESS_KEY  	VERSION
insert into OA_GROUP values(2, 'admin', '2013-04-26 10:34:21.666', 'Admin access in an organization', null, 'ROLE_ADMIN', null, 1);


--OA_USER
--ID  	CREATED_BY  	CREATION_DATE  	EMAIL  	FIRST_NAME  	GROUP_NAME  	LAST_NAME  	MODIFICATION_DATE  	MODIFIED_BY  	PASSWORD  	TENANT_BUSINESS_KEY  	TENANT_NAME  	USER_NAME  	VERSION
insert into OA_USER values (3, 'admin', '2013-04-26 10:34:37.562', 'dino@home.com', 'Dino', null, 'Kelly', null, null, '8fbefc95205d5645b24823f75d1935b607af6fd3ff792b14d74db9241c43c884', null, null, 'dino', 1);

--OA_ACTIVITY_STREAM
--ID  	CREATEDBY  	CREATION_DATE  	EVENT  	TENANT_BUSINESS_KEY  	VERSION
insert into OA_ACTIVITY_STREAM values('699f0f71-271f-46b3-aa4f-a20e7ba1a4f2', 'admin', '2013-04-26 10:34:13.149', 'Assignment of BusinessKey [ tenantDS1 ] to Tenant [ Ministere Interieur ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values('40719d37-e308-4ac7-83b7-1cbd0a5c9962', 'admin', '2013-04-26 10:34:21.67', 'Creation of Group [ ROLE_ADMIN ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values('53feac74-d197-4770-9a55-da90d71488a0', 'admin', '2013-04-26 10:34:37.574', 'Creation of User [ dino ]', 'Operations', 1);


update sequence  set seq_count =  50;





