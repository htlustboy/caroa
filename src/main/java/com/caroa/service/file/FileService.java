package com.caroa.service.file;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

import com.caroa.common.RedisClient;
import com.caroa.constant.Constant;
import com.caroa.dao.FileMapper;
import com.caroa.model.File;
import com.caroa.util.JsonUtil;

@Service
public class FileService {
	
	@Resource
	RedisClient redisClient;
	
	@Resource
	FileMapper fileMapper;
	
	//最新文件
	public List<Map<String, Object>> findNewFile(){
		List<Map<String, Object>> list = new ArrayList<>();
		Jedis jedis = redisClient.getJedis();
		try {
			//先从redis中读取
			String jsonObj = jedis.get(Constant.File_TYPE_NEWFILE);
			if(jsonObj!=null){
				return JsonUtil.json2listMap(jsonObj);
			}else{
				//再从数据库中读取
				list = fileMapper.findNewFile();
				jedis.set(Constant.File_TYPE_NEWFILE, JsonUtil.obj2json(list));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			redisClient.returnReource(jedis);
		}
	}
	
	//热门文件
	public List<Map<String, Object>> findHotFile(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Jedis jedis = redisClient.getJedis();
		try {
			//先从redis中获取
			String jsonObj = jedis.get(Constant.File_TYPE_HOTFILE);
			if(jsonObj!=null){
				return JsonUtil.json2listMap(jsonObj);
			}else{
				list = fileMapper.findHotFile();
				jedis.set(Constant.File_TYPE_HOTFILE, JsonUtil.obj2json(list));
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			redisClient.returnReource(jedis);
		}
	}
	
	@Transactional
	//根据ID查找文件,更新文件点击数
	public File findById(String fid){
		File file = fileMapper.findById(fid);
		if(file!=null){
			//更新文件点击数
			fileMapper.updateClickRateById(fid);
		}
		return file;
	}
}
