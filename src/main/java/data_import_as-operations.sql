--OA_USER
--ID  	CREATED_BY  	CREATION_DATE  	EMAIL  	FIRST_NAME  	GROUP_NAME  	LAST_NAME  	MODIFICATION_DATE  	MODIFIED_BY  	PASSWORD  	TENANT_BUSINESS_KEY  	TENANT_NAME  	USER_NAME  	VERSION
insert into OA_USER values (1, 'admin', '2013-04-28 16:50:29.966', 'dino@home.com', 'Dino', 'ROLE_WORKGROUP', 'Kelly', null, null, '8fbefc95205d5645b24823f75d1935b607af6fd3ff792b14d74db9241c43c884', 'tenantDS2', 'Bird & Bird', 'dino', 5);
insert into OA_USER values (2, 'admin', '2013-04-28 16:50:52.775', 'bob@home.com', 'Bob', 'ROLE_ADMIN', 'Marley', null, null, '81b637d8fcd2c6da6359e6963113a1170de795e4b725b84d1e0b4cfd9ec58ce9', 'tenantDS2', 'Bird & Bird', 'bob', 5);
insert into OA_USER values (3, 'admin', '2013-04-28 16:51:17.198', 'jdoe@home.com', 'John', 'ROLE_DOMAIN', 'Doe', null, null, '799ef92a11af918e3fb741df42934f3b568ed2d93ac1df74f1b8d41a27932a6f', 'tenantDS2', 'Bird & Bird', 'doe', 4);

SELECT * FROM OA_GROUP ;
--OA_GROUP
--ID  	CREATED_BY  	CREATION_DATE  	DESCRIPTION  	MODIFICATION_DATE  	NAME  	TENANT_BUSINESS_KEY  	VERSION  
insert into OA_GROUP values (4, 'admin', '2013-04-28 16:51:22.425', 'Admin access in an organization', null, 'ROLE_ADMIN', null, 3);
insert into OA_GROUP values (5, 'admin', '2013-04-28 16:51:35.406', 'Domain access in an organization', null, 'ROLE_DOMAIN', null, 3);
insert into OA_GROUP values (6, 'admin', '2013-04-28 16:51:50.986', 'Workgroup access in an organization', null, 'ROLE_WORKGROUP', null, 2);
insert into OA_GROUP values (7, 'admin', '2013-04-28 16:52:10.281', 'Public access', null, 'ROLE_USER', null, 1);


--OA_TENANT ;
--ID  	BUSINESS_KEY  	CREATED_BY  	CREATION_DATE  	MODIFICATION_DATE  	MODIFIED_BY  	NAME  	VERSION  
insert into OA_TENANT values (8, 'tenantDS1', 'admin', '2013-04-28 16:52:19.378', null, null, 'Ministere Interieur', 4);
insert into OA_TENANT values (9, 'tenantDS2', 'admin', '2013-04-28 16:52:31.589', null, null, 'Bird & Bird', 4);



--OA_GROUP_OA_USER ;
--OAGROUP_ID  	USERS_ID  
insert into OA_GROUP_OA_USER values (4, 1);
insert into OA_GROUP_OA_USER values (6,	1);
insert into OA_GROUP_OA_USER values (5, 2);
insert into OA_GROUP_OA_USER values (4, 2);
insert into OA_GROUP_OA_USER values (5, 3);

--OA_USER_OA_GROUP ;
--OAUSER_ID  	GROUPS_ID  
insert into OA_USER_OA_GROUP values (1, 4);
insert into OA_USER_OA_GROUP values (1,	6);
insert into OA_USER_OA_GROUP values (2, 5);
insert into OA_USER_OA_GROUP values (2, 4);
insert into OA_USER_OA_GROUP values (3,	5);



--OA_TENANT_OA_USER ;
--OATENANT_ID  	USERS_ID  
insert into OA_TENANT_OA_USER values (8, 1); 
insert into OA_TENANT_OA_USER values (9, 1);
insert into OA_TENANT_OA_USER values (8, 2);
insert into OA_TENANT_OA_USER values (9, 2);
insert into OA_TENANT_OA_USER values (8, 3);
insert into OA_TENANT_OA_USER values (9, 3);


--OA_USER_OA_TENANT ;
--OAUSER_ID  	TENANTS_ID  
insert into OA_USER_OA_TENANT values (1, 8);
insert into OA_USER_OA_TENANT values (1, 9);
insert into OA_USER_OA_TENANT values (2, 8);
insert into OA_USER_OA_TENANT values (2, 9);
insert into OA_USER_OA_TENANT values (3, 8);
insert into OA_USER_OA_TENANT values (3, 9);


--OA_USER_ROLE_IN_TENANT ;
--ID  	GROUP_NAME  	TENANT_BUSINESS_KEY  	USER_NAME  	VERSION  
insert into OA_USER_ROLE_IN_TENANT values (10, 'ROLE_ADMIN', 'tenantDS1', 'dino', 1);
insert into OA_USER_ROLE_IN_TENANT values (11, 'ROLE_WORKGROUP', 'tenantDS2', 'dino', 1);
insert into OA_USER_ROLE_IN_TENANT values (12, 'ROLE_DOMAIN', 'tenantDS1', 'bob', 1);
insert into OA_USER_ROLE_IN_TENANT values (13, 'ROLE_ADMIN', 'tenantDS2', 'bob', 1);
insert into OA_USER_ROLE_IN_TENANT values (14, 'ROLE_DOMAIN', 'tenantDS2', 'doe', 1);


--OA_ACTIVITY_STREAM ;
--ID  	CREATEDBY  	CREATION_DATE  	EVENT  	TENANT_BUSINESS_KEY  	VERSION  
insert into OA_ACTIVITY_STREAM values ('ed30fe33-1095-4a09-80f1-58f8d155c376', 'admin', '2013-04-28 16:50:29.98', 'Creation of User [ dino ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('f8aa2230-3e3a-45da-8ec8-b2fe8e58ff8d', 'admin', '2013-04-28 16:50:52.777', 'Creation of User [ bob ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('14c22b9f-3ad4-4540-ba85-677a2e197053', 'admin', '2013-04-28 16:51:17.2', 'Creation of User [ doe ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('561933e0-5ad4-4231-b88b-835d37907213', 'admin', '2013-04-28 16:51:22.428', 'Creation of Group [ ROLE_ADMIN ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('91d4c787-cf14-4f07-9940-73f66761313d', 'admin', '2013-04-28 16:51:35.406', 'Creation of Group [ ROLE_DOMAIN ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('97b9ce96-1061-4715-a4bf-d9207cb3227d', 'admin', '2013-04-28 16:51:50.986', 'Creation of Group [ ROLE_WORKGROUP ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('82b9c684-3151-4315-a021-57790fdc561c', 'admin', '2013-04-28 16:52:10.282', 'Creation of Group [ ROLE_USER ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('a993eca6-a1c3-4b74-8bb7-b30691c800d3', 'admin', '2013-04-28 16:52:19.381', 'Assignment of BusinessKey [ tenantDS1 ] to Tenant [ Ministere Interieur ]', 'Operations', 1);
insert into OA_ACTIVITY_STREAM values ('d30e9728-c222-42b8-9011-a2ad57d52e4b', 'admin', '2013-04-28 16:52:31.59', 'Assignment of BusinessKey [ tenantDS2 ] to Tenant [ Bird & Bird ]',  'Operations', 1);


update sequence  set seq_count =  50;

