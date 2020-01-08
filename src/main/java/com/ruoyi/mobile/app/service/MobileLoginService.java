package com.ruoyi.mobile.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.PageData;
import com.ruoyi.mobile.app.mapper.MobileLoginMapper;

@Service
public class MobileLoginService{

	@Autowired
	private MobileLoginMapper mobileLoginMapper;

	public Map checkLoginResult(PageData pd) {
		
		return null;
	}

	public void updateTokenAndResId(PageData pd) {
		
		mobileLoginMapper.updateTokenAndResId(pd);
	}
}
