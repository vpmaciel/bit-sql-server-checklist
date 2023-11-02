SELECT (DATEDIFF(DAY, create_date, GETDATE()))
       AS [Days],
       ((DATEDIFF(MINUTE, create_date, GETDATE())/60)%24)
       AS [Hours],
       DATEDIFF(MINUTE, create_date, GETDATE())%60
       AS [Minutes]
FROM   sys.databases
WHERE  name = 'tempdb';