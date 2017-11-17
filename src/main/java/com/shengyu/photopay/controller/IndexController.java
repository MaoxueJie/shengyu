package com.shengyu.photopay.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shengyu.photopay.util.Config;

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
	public static final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	@RequestMapping("upload")
	public String upload()
	{
		return "upload";
	}
	
	@RequestMapping("doUpload")
	public String doUpload(HttpServletRequest request, @RequestParam MultipartFile uploadFile[])
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
					request.setAttribute("msg","文件名称不匹配");
					return "result";
				}
			}
		}else
		{
			request.setAttribute("msg","文件个数不匹配");
			return "result";
		}
		Collections.sort(fileNos);
		if (fileNos.get(0) != fileNos.get(1)-1 || fileNos.get(1) != fileNos.get(2)-1)
		{
			request.setAttribute("msg","不是连续的文件");
			return "result";
		}
		
		if (!(fileNos.get(0)%3==0))
		{
			request.setAttribute("msg","起始文件名称不对");
			return "result";
		}
		
		
		String filePath = Config.getContextProperty("file.path");
		
		if (filePath.endsWith(File.separator))
		{
			filePath += fileNos.get(0)/3;
		}else
		{
			filePath += File.separator+fileNos.get(0)/3;
		}
		Date now = new Date();
		synchronized(IndexController.class){
			File file = new File(filePath);
			if (file.exists() )
			{
				if (file.isDirectory())
				{
					String[] files =  file.list();
					if (file!=null)
					{
						for(String fileName:files)
						{
							if ((format.format(now)+".txt").equals(fileName))
							{
								request.setAttribute("msg","编号"+fileNos.get(0)/3+"今日已用");
								return "result";
							}
						}
					}
					deleteDir(filePath);
				}else
				{
					if (file.delete())
					{
						file.mkdirs();
					}
				}
			}else
			{
				file.mkdirs();
			}
			try{
				for(MultipartFile mutifile :uploadFile)
				{
					mutifile.transferTo(new File(filePath+File.separator+mutifile.getOriginalFilename()));
				}
			}catch(Exception e)
			{
				request.setAttribute("msg","上传文件异常，请重新上传");
				return "result";
			}
		}
		request.setAttribute("msg","上传成功  支付编号为："+fileNos.get(0)/3);
		return "result";
	}
	public static boolean deleteDir(String path){  
	        File file = new File(path);  
	        if(!file.exists()){//判断是否待删除目录是否存在  
	            System.err.println("The dir are not exists!");  
	            return false;  
	        }  
	          
	        String[] content = file.list();//取得当前目录下所有文件和文件夹  
	        for(String name : content){  
	            File temp = new File(path, name);  
	            if(temp.isDirectory()){//判断是否是目录  
	                deleteDir(temp.getAbsolutePath());//递归调用，删除目录里的内容  
	                temp.delete();//删除空目录  
	            }else{  
	                if(!temp.delete()){//直接删除文件  
	                    System.err.println("Failed to delete " + name);  
	                }  
	            }  
	        }  
	        return true;  
	    }
	
	@RequestMapping("pay")
	public String pay()
	{
		return "pay";
	}
	
	@RequestMapping("doPay")
	public String doPay()
	{
		return "result";
	}
	
	public static void main(String args[])
	{
		String fileName = "IMG_00013.JPG";
		System.out.println(fileName.matches(pattern));
		String []fileNames = fileName.split("\\.|\\_");
		System.out.println(fileNames[0]);
		System.out.println(fileNames[1]);
		System.out.println(fileNames[2]);
		
		deleteDir("d://abc");
	}
	
}
