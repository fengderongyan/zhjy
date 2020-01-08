package com.ruoyi.mobile.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.ruoyi.common.utils.alipay.Alipay;
import com.ruoyi.common.utils.alipay.AlipayBean;

@Service
public class AliPayDemoService {

	@Autowired
	private Alipay alipay;
	
	public String doH5Alipay(AlipayTradeWapPayModel model, String return_url, String notify_url) throws AlipayApiException{
		return alipay.doH5Alipay(model, return_url, notify_url);
	}

	public String refundH5Alipay(AlipayTradeRefundModel model) throws AlipayApiException {
		return alipay.refundH5Alipay(model);
	}
}
