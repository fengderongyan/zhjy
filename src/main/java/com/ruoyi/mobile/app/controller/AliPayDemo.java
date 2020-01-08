package com.ruoyi.mobile.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.ruoyi.mobile.app.service.AliPayDemoService;

@RestController()
@RequestMapping("/mobile/app")
public class AliPayDemo {
	
	@Autowired
	private AliPayDemoService aliPayDemoService;
	
	/**
	 * 支付宝H5支付接口demo
	 * @return
	 */
	@RequestMapping("/doH5Alipay")
	public String doH5Alipay(){
		try {
			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
			model.setOutTradeNo("1212");//商户订单号
			model.setSubject("测试支付");//标题
			model.setTotalAmount("1");//支付金额（单位元）
			model.setProductCode("QUICK_WAP_WAY");
			String return_url = "";//支付成功H5页面回调
			String notify_url = "";//服务器异步回调
			String res = aliPayDemoService.doH5Alipay(model,return_url , notify_url);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * H5退款
	 * @return
	 */
	@RequestMapping("/refundH5Alipay")
	public String refundH5Alipay(){
		try {
			AlipayTradeRefundModel model=new AlipayTradeRefundModel();
			model.setOutTradeNo("1234");//商户订单号(商户订单号、支付宝交易号 二选一)
			//model.setTradeNo("");//支付宝交易号
			model.setRefundAmount("1");//退款金额
			model.setRefundReason("不想买了");//退款理由
			//model.setOutRequestNo(out_request_no);//退款订单号
			String res = aliPayDemoService.refundH5Alipay(model);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	} 

}
