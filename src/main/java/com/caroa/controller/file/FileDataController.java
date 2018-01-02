package com.caroa.controller.file;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.caroa.controller.BaseController;

@RestController
@RequestMapping("/file")
public class FileDataController extends BaseController{
	
	@ResponseBody
	@RequestMapping(value="/loadFile",method=RequestMethod.POST)
	public Map<String, Object> loadFile(){
		String filePath = this.getRequest().getParameter("filePath");
		String content = (String) readFile(filePath);
		Map<String, Object> map = new HashMap<>();
		map.put("content", content);
		return map;
	}
	
}
