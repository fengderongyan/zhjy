package com.ruoyi.mobile.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.mobile.app.service.TestService;
import com.ruoyi.project.service.RedisService;
import com.ruoyi.project.system.dept.domain.Dept;
import com.ruoyi.project.system.user.domain.User;

@Controller
@RequestMapping("/mobile/app2")
public class TestMobileController extends BaseController{
	
	@Value("${weixin.appid}")
	private String appid;
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private TestService testService;

	@RequestMapping("/testMethod")
	@ResponseBody
	public Long testMethod(){
		Map map = new HashMap();
		map.put("userid", null);
		map.put("username", "测试");
		List list = new ArrayList();
		list.add(map);
		list.add(map);
		
		Dept dept = new Dept();
		dept.setDeptId(123L);
		dept.setDeptName("测试实体类");
//		redisService.addObject("ry:testDept", dept);
		//redisService.addObjectWithTime("ry:testMap", map, 60L);
//		redisService.addObject("ry:testList", list);
//		redisService.addObject("ry:testStr", "测试字符串");
//		System.out.println((Dept)redisService.getObject("ry:testDept"));
//		System.out.println((Map)redisService.getObject("ry:testMap"));
//		System.out.println((List)redisService.getObject("ry:testList"));
//		System.out.println((String)redisService.getObject("ry:testStr"));
		//redisService.addExpire("ry:testMap", 60);
		redisService.delete("ry:testList");
		redisService.delete("ry:testMap");
		
		System.out.println(redisService.getExpire("ry:testMap"));//获取过期时间
		System.out.println(redisService.hasKey("ry:testMap"));
		System.out.println(redisService.hasKey("ry:testStr"));
		return redisService.getExpire("ry:testList");
	}
	
	@RequestMapping("/testmethod2")
	@ResponseBody
	public Map testmethod2(){
		PageData pd = this.getPageData();
		return pd;
	}
	
	@RequestMapping("/testpd")
	@ResponseBody
	public List testpd(){
		PageData pd = this.getPageData();
		List<PageData> res = testService.testpd(pd);
		return res;
		
	}
}
