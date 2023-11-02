SELECT DISTINCT 'Information' AS [Category], 'Disk_Space' AS [Information], vs.logical_volume_name,
		vs.volume_mount_point, vs.file_system_type, CONVERT(int,vs.total_bytes/1048576.0) AS TotalSpace_MB,
		CONVERT(int,vs.available_bytes/1048576.0) AS FreeSpace_MB, vs.is_compressed AS Compressed
	FROM sys.master_files mf
	CROSS APPLY sys.dm_os_volume_stats(mf.database_id, mf.[file_id]) vs
	ORDER BY FreeSpace_MB ASC