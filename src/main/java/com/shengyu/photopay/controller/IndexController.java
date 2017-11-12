package com.shengyu.photopay.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
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
 * @date 2017骞�0鏈�4鏃�
 */
@Controller
public class IndexController {
	
	public static final String  pattern = "^IMG_\\d{4}.JPG";
	
	@RequestMapping("upload")
	public String upload()
	{
		return "upload";
	}
	
	
	@RequestMapping("doUpload")
	public String doUpload(@RequestParam MultipartFile uploadFile[])
	{
		ArrayList<Integer> fileNos = new ArrayList<Integer>();
		if (uploadFile!=null && uploadFile.length==3)
		{
			for(MultipartFile file :uploadFile)
			{
				String fileName = file.getOriginalFilename();
				if (fileName.matches(pattern))
				{
					String [] fileNameSplits = fileName.split("\\.|\\_");
					fileNos.add(Integer.parseInt(fileNameSplits[1]));
				}else
				{
					return null;
				}
			}
		}else
		{
			return null;
		}
		Collections.sort(fileNos);
		for(Integer i:fileNos)
		{
			System.out.println(i);
		}
		
		return null;
	}
	
	public static void main(String args[])
	{
		
		String fileName = "IMG_00013.JPG";
		System.out.println(fileName.matches(pattern));
		String []fileNames = fileName.split("\\.|\\_");
		System.out.println(fileNames[0]);
		System.out.println(fileNames[1]);
		System.out.println(fileNames[2]);
	}
	
}
