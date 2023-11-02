USE master
--GO
SET NOCOUNT ON
DECLARE @loginName sysname, @dbName sysname

/* Set the two Parameters here. By defaul All logins and databases will be reported */
SET @loginName = '%' -- e.g. 'NorthAmerica\JSmith1'
SET @dbName = '%' -- e.g. 'ReportServer'

-- Retrieve DB Role Level Info
DECLARE @DBRolePermissions TABLE(
 DatabaseName varchar(300), 
 Principal_Name sysname, 
 Login_Name sysname NULL, 
 DB_RoleMember varchar(300), 
 Permission_Type sysname)

INSERT INTO @DBRolePermissions
EXEC sp_MSforeachdb '
 SELECT DISTINCT ''?'' AS DatabaseName, users.Name AS UserName, suser_sname(users.sid) AS Login_Name, 
 roles.Name AS Role_Member_Name, roles.type_desc
 FROM [?].sys.database_role_members r 
 LEFT OUTER JOIN [?].sys.database_principals users on r.member_principal_id = users.principal_id
 LEFT OUTER JOIN [?].sys.database_principals roles on r.role_principal_id = roles.principal_id
 --WHERE users.type not in (''R'')'

-- Capture permissions generated FROM sys.database_permissions
INSERT INTO @DBRolePermissions
EXEC sp_msforeachdb '
 SELECT DISTINCT ''?'' AS DatabaseName, users.Name AS UserName, suser_sname(users.sid) AS Login_Name, 
 r.Permission_Name AS DB_RoleMember, r.class_desc
 FROM [?].sys.database_permissions r 
 LEFT OUTER JOIN [?].sys.database_principals users on r.Grantee_principal_id = users.principal_id
 WHERE r.class_desc = ''DATABASE'''

SELECT DISTINCT Principal_Name, Login_Name, DatabaseName, DB_RoleMember AS Permission_Name, Permission_Type
FROM @DBRolePermissions 
WHERE (ISNULL(Login_Name, '') LIKE @loginName OR ISNULL(Principal_Name, '') LIKE @loginName)
 AND DatabaseName LIKE @dbName
ORDER BY Principal_Name, DatabaseName, DB_RoleMember
