package com.shengyu.photopay.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Title: IndexController.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2013</p>
 * <p>Company:www.liequ.cn</p>
 * @author jiemaoxue@yourmall.com
 * @date 2017年10月14日
 */
@Controller
public class IndexController {
	
	@RequestMapping("upload")
	public String upload()
	{
		return "upload";
	}
	
	
	@RequestMapping("doUpload")
	public String doUpload(@RequestParam MultipartFile uploadFile[])
	{
		for(MultipartFile file :uploadFile)
		{
			String fileName = file.getOriginalFilename();
			
		}
		return null;
	}
	
	public static void main(String args[])
	{
		String pattern = "^IMG_\\d{4}.JPG";
		String fileName = "IMG_0001.JPG";
		System.out.println(fileName.matches(pattern));
		String []fileNames = fileName.split("\\.|\\_");
		System.out.println(fileNames[0]);
		System.out.println(fileNames[1]);
		System.out.println(fileNames[2]);
	}
	
}
