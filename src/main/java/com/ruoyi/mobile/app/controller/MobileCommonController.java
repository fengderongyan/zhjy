package com.ruoyi.mobile.app.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.ResultUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.web.controller.BaseController;

@RestController
@RequestMapping("/mobile/app")
public class MobileCommonController extends BaseController{

	@RequestMapping("/getYjbbLoginInfo")
	public Map getYjbbLoginInfo(){
		try {
			String yjbbOper = this.getMobileUser().getYjbbOper();
			String yjbbPwd = this.getMobileUser().getYjbbPwd();
			if(yjbbOper == null || "".equals(yjbbOper)){
				return ResultUtils.returnError(12002, "您未绑定饮酒报备账号，请联系管理员");
			}
			String param = "loginName=" + yjbbOper + "&loginPassword=" + yjbbPwd;
	    	String result = HttpUtils.sendPost("http://218.92.34.254:8091/sys/mobile/loginmi/checkLogin.do", param);
	    	if(result == null || "".equals(result)){
	    		return ResultUtils.returnError(-1, "后台运行异常");
	    	}
	    	Map map = (Map)JSONObject.parse(result);
	    	Integer errorCode = (Integer)map.get("errorCode");
	    	if(errorCode == -3){
	    		return ResultUtils.returnError(-1, "后台运行异常");
	    	}else if(errorCode != 0){
	    		return ResultUtils.returnError(12003, "您绑定的饮酒报备账号密码有误，请联系管理员");
	    	}
	    	Map userInfo = (Map)map.get("data");
	    	return ResultUtils.returnOk(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
}
