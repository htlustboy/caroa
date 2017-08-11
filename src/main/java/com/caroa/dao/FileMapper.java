package com.caroa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.caroa.model.File;

public interface FileMapper {
	
	//查找最新文件
	List<Map<String, Object>> findNewFile();
	
	//查找热门文件
	List<Map<String, Object>> findHotFile();
	
	//根据ID查找File
	File findById(@Param("fid")String fid);
	
	//更新文件点击数
	void updateClickRateById(@Param("fid")String fid);
}
