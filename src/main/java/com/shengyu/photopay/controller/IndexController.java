package com.shengyu.photopay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping("index")
	@ResponseBody
	public String index()
	{
		return "00000";
	}
}
