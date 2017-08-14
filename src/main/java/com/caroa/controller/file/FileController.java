package com.caroa.controller.file;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.caroa.controller.BaseController;
import com.caroa.model.File;
import com.caroa.service.file.FileService;

@Controller
@RequestMapping("/file")
public class FileController extends BaseController{
	
	@Resource
	private FileService fileService;
	
	//查看文件
	@RequestMapping(value="/fileDetail/{fid}",method=RequestMethod.GET)
	public String fileDetail(@PathVariable("fid")String fid,Model model){
		File file = fileService.findById(fid);
		model.addAttribute("file", file);
		return "/file/detail";
	}
}
