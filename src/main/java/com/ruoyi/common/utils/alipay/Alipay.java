package com.ruoyi.common.utils.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;

/**
 * 支付宝支付接口
 * @author Louis
 * @date Dec 12, 2018
 */
@Component
public class Alipay {
	
	@Value("${alipay.gateway_url}")
	private String gateway_url;
	@Value("${alipay.appid}")
	private String appid;
	@Value("${alipay.public_key}")
	private String public_key;
	@Value("${alipay.private_key}")
	private String private_key;
	@Value("${alipay.format}")
	private String format;
	@Value("${alipay.charset}")
	private String charset;
	@Value("${alipay.signType}")
	private String signType;
	
    /**
     * H5支付接口
     * @param alipayBean
     * @return
     * @throws AlipayApiException
     */
    public String doH5Alipay(AlipayTradeWapPayModel model, String return_url, String notify_url) throws AlipayApiException {
        // 1、公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        AlipayClient alipayClient = new DefaultAlipayClient(gateway_url, appid, private_key, format, charset, public_key, signType);
        // 2、设置请求参数
        AlipayTradeWapPayRequest alipayRequest=new AlipayTradeWapPayRequest();
        // 页面跳转同步通知页面路径
        alipayRequest.setReturnUrl(return_url);
        // 服务器异步通知页面路径
        alipayRequest.setNotifyUrl(notify_url);
        // 封装参数
        alipayRequest.setBizModel(model);
        // 3、请求支付宝进行付款，并获取支付结果
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        System.out.println(result);
        // 返回付款信息
        return result;
    }

	public String refundH5Alipay(AlipayTradeRefundModel model) throws AlipayApiException {
		 AlipayClient alipayClient = new DefaultAlipayClient(gateway_url, appid, private_key, format, charset, public_key, signType);
		 AlipayTradeRefundRequest alipay_request = new AlipayTradeRefundRequest();
		 alipay_request.setBizModel(model);
		 AlipayTradeRefundResponse alipay_response =alipayClient.execute(alipay_request);
		return alipay_response.getBody();
	}
}