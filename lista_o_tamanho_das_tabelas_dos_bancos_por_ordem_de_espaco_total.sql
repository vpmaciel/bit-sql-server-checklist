IF OBJECT_ID('tempdb.dbo.#space') IS NOT NULL
    DROP TABLE #space

CREATE TABLE #space (
      [db_name] SYSNAME
    , obj_name SYSNAME
    , total_pages BIGINT
    , used_pages BIGINT
    , total_rows BIGINT
)

DECLARE @SQL NVARCHAR(MAX)

SELECT @SQL = STUFF((
    SELECT '
    USE [' + d.name + ']
    INSERT INTO #space ([db_name], obj_name, total_pages, used_pages, total_rows)
    SELECT DB_NAME(), SCHEMA_NAME(o.[schema_id]) + ''.'' + o.name, t.total_pages, t.used_pages, t.total_rows
    FROM (
        SELECT
              i.[object_id]
            , total_pages = SUM(a.total_pages)
            , used_pages = SUM(a.used_pages)
            , total_rows = SUM(CASE WHEN i.index_id IN (0, 1) AND a.[type] = 1 THEN p.[rows] END)
        FROM sys.indexes i
        JOIN sys.partitions p ON i.[object_id] = p.[object_id] AND i.index_id = p.index_id
        JOIN sys.allocation_units a ON p.[partition_id] = a.container_id
        WHERE i.is_disabled = 0
            AND i.is_hypothetical = 0
        GROUP BY i.[object_id]
    ) t
    JOIN sys.objects o ON t.[object_id] = o.[object_id]
    WHERE o.name NOT LIKE ''dt%''
        AND o.is_ms_shipped = 0
        AND o.type = ''U''
        AND o.[object_id] > 255;'
    FROM sys.databases d
    WHERE d.[state] = 0
    FOR XML PATH(''), TYPE).value('.', 'NVARCHAR(MAX)'), 1, 2, '')

EXEC sys.sp_executesql @SQL

SELECT 
      [db_name]
    , obj_name
    , total_rows
    , total_space = CAST(total_pages * 8. / 1024 AS DECIMAL(18,2))
    , used_space = CAST(used_pages * 8. / 1024 AS DECIMAL(18,2))
    , unused_space = CAST((total_pages - used_pages) * 8. / 1024 AS DECIMAL(18,2))
FROM #space

order by total_space desc