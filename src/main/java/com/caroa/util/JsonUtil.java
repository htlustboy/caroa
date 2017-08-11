package com.caroa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * JSON工具类
 * @author ht
 *
 */
public class JsonUtil {
	
	/**
	 * list转化为json文本
	 * @param list
	 * @return
	 */
	public static<T> String list2string(List<T> list){
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}
	
	/**
	 * 将对象转化为json数据
	 * @param object
	 * @return
	 */
	public static String obj2json(Object object){
		JSONArray jsonArray = JSONArray.fromObject(object);
		return jsonArray.toString();
	}
	
	/**
	 * 将数组转化为json数据
	 * @param jsonArray
	 */
	public static String jArray2json(JSONArray jsonArray){
		return jsonArray.toString();
	}
	
	/**
	 * 将json对象妆化为json数据
	 * @param jsonObject
	 * @return
	 */
	public static String json2string(JSONObject jsonObject){
		return jsonObject.toString();
	}
	
	/**
	 * 将对象转化为list
	 * @param object
	 * @return
	 */
	public static List<Object> json2list(Object object){
		List<Object> list = new ArrayList<Object>();
		JSONArray jsonArray = JSONArray.fromObject(object);
		Iterator<Object> it = jsonArray.iterator();
		while (it.hasNext()) {
			JSONObject jsonObject = (JSONObject) it.next();
			Iterator<String> keys = jsonObject.keys();
			while (keys.hasNext()) {
				String key = keys.next();
				Object value = jsonObject.get(key);
				list.add(value);
			}
		}
		return list;
	}
	
	/***
     * 将对象转换为Collection对象
     * @param object
     * @return
     */
    public static Collection json2collection(Object object)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);
        return JSONArray.toCollection(jsonArray);
    }

    /***
     * 将对象转换为JSON对象数组
     * @param object
     * @return
     */
    public static JSONArray obj2jsonArray(Object object)
    {
        return JSONArray.fromObject(object);
    }

    /***
     * 将对象转换为JSON对象
     * @param object
     * @return
     */
    public static JSONObject obj2jsonObj(Object object)
    {
        return JSONObject.fromObject(object);
    }

    /***
     * 将对象转换为HashMap
     * @param object
     * @return
     */
    public static HashMap json2Map(Object object)
    {
        HashMap<String, Object> data = new HashMap<String, Object>();
        JSONObject jsonObject = obj2jsonObj(object);
        Iterator it = jsonObject.keys();
        while (it.hasNext())
        {
            String key = String.valueOf(it.next());
            Object value = jsonObject.get(key);
            data.put(key, value);
        }

        return data;
    }

    /***
     * 将对象转换为List<Map<String,Object>>
     * @param object
     * @return
     */
    // 返回非实体类型(Map<String,Object>)的List
    public static List<Map<String, Object>> json2listMap(Object object)
    {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        JSONArray jsonArray = JSONArray.fromObject(object);
        for (Object obj : jsonArray)
        {
            JSONObject jsonObject = (JSONObject) obj;
            Map<String, Object> map = new HashMap<String, Object>();
            Iterator it = jsonObject.keys();
            while (it.hasNext())
            {
                String key = (String) it.next();
                Object value = jsonObject.get(key);
                map.put((String) key, value);
            }
            list.add(map);
        }
        return list;
    }

    /***
     * 将JSON对象数组转换为传入类型的List
     * @param <T>
     * @param jsonArray
     * @param objectClass
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> List<T> json2lisyByType(JSONArray jsonArray, Class<T> objectClass)
    {
        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * 将对象转换为传入类型的List
     * @param <T>
     * @param jsonArray
     * @param objectClass
     * @return
     */
    public static <T> List<T> obj2jsonByType(Object object, Class<T> objectClass)
    {
        JSONArray jsonArray = JSONArray.fromObject(object);

        return JSONArray.toList(jsonArray, objectClass);
    }

    /***
     * 将JSON对象转换为传入类型的对象
     * @param <T>
     * @param jsonObject
     * @param beanClass
     * @return
     */
    public static <T> T json2objByType(JSONObject jsonObject, Class<T> beanClass)
    {
        return (T) JSONObject.toBean(jsonObject, beanClass);
    }

    /***
     * 将将对象转换为传入类型的对象
     * @param <T>
     * @param object
     * @param beanClass
     * @return
     */
    public static <T> T obj2obj(Object object, Class<T> beanClass)
    {
        JSONObject jsonObject = JSONObject.fromObject(object);

        return (T) JSONObject.toBean(jsonObject, beanClass);
    }
    
    public static void main(String[] args) {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = new HashMap<>();
		map.put("aaa", "hello");
		map.put("bbb", "world");
		list.add(map);
		System.out.println(list2string(list));
		
    }
}
