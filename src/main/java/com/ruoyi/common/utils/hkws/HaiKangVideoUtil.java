package com.ruoyi.common.utils.hkws;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.ruoyi.common.utils.hkws.domain.AcsDoorRequest;
import com.ruoyi.common.utils.hkws.domain.CamerasRequest;
import com.ruoyi.common.utils.hkws.domain.DoorEventsRequest;
import com.ruoyi.common.utils.hkws.domain.GetAllTreeCodeRequest;
import com.ruoyi.common.utils.hkws.domain.PersonLibRequest;
import com.ruoyi.common.utils.hkws.domain.PreviewURLsRequest;
import com.ruoyi.common.utils.hkws.domain.RegionCamerasRequest;
import com.ruoyi.common.utils.hkws.domain.RegionsRequest;
import com.ruoyi.common.utils.hkws.domain.SubRegionsRequest;

import java.util.HashMap;
import java.util.Map;

public class HaiKangVideoUtil {
	/**
	 * 能力开放平台的网站路径
	 * 路径不用修改，就是/artemis
	 */
	private static final String ARTEMIS_PATH = "/artemis";
	
	static {
		ArtemisConfig.host = "221.178.190.51:444"; // 平台/nginx的IP和端口（必须使用https协议，https端口默认为443）
		ArtemisConfig.appKey = "21821110"; // 秘钥appkey
		ArtemisConfig.appSecret = "sSJZxwAMiSLDZqT0vU76";// 秘钥appSecret
	}

	public static String GetCameraPreviewURL() {

		/**
		 * STEP3：设置接口的URI地址
		 */
		final String previewURLsApi = ARTEMIS_PATH + "/api/video/v1/cameras/previewURLs";
		Map<String, String> path = new HashMap<String, String>(2) {
			{
				put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
			}
		};

		/**
		 * STEP4：设置参数提交方式
		 */
		String contentType = "application/json";

		/**
		 * STEP5：组装请求参数
		 */
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("cameraIndexCode", "748d84750e3a4a5bbad3cd4af9ed5101");
		jsonBody.put("streamType", 0);
		jsonBody.put("protocol", "rtsp");
		jsonBody.put("transmode", 1);
		jsonBody.put("expand", "streamform=ps");
		String body = jsonBody.toJSONString();
		/**
		 * STEP6：调用接口
		 */
		String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null, contentType , null);// post请求application/json类型参数
		return result;
	}
	
	
	public static String getAllTreeCode(GetAllTreeCodeRequest getAllTreeCodeRequest ){
		String getAllTreeCodeDataApi = ARTEMIS_PATH +"/api/resource/v1/unit/getAllTreeCode";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",getAllTreeCodeDataApi);
			}
		};
		String body=JSON.toJSONString(getAllTreeCodeRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	//分页获取区域列表
	public static String regions(RegionsRequest regionsRequest ){
		String regionsDataApi = ARTEMIS_PATH +"/api/resource/v1/regions";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",regionsDataApi);
			}
		};
		String body=JSON.toJSONString(regionsRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	/**
	 * 获取某个区域的下级区域
	 * @param subRegionsRequest
	 * @return
	 */
	public static String subRegions(SubRegionsRequest subRegionsRequest){
		String subRegionsDataApi = ARTEMIS_PATH +"/api/resource/v1/regions/subRegions";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",subRegionsDataApi);
			}
		};
		String body=JSON.toJSONString(subRegionsRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
		
	}
	
	/**
	 * 获取区域下的监控资源列表
	 * @return
	 */
	public static String regionCameras(RegionCamerasRequest request){
		String dataApi = ARTEMIS_PATH +"/api/resource/v1/regions/regionIndexCode/cameras";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",dataApi);
			}
		};
		String body=JSON.toJSONString(request);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	//分页获取监控点资源
	public static String cameras(CamerasRequest camerasRequest ){
		String camerasDataApi = ARTEMIS_PATH +"/api/resource/v1/cameras";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",camerasDataApi);
			}
		};
		String body=JSON.toJSONString(camerasRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	/**
	 * @param previewURLsRequest
	 * @return
	 */
	public static String previewURLs(PreviewURLsRequest previewURLsRequest ){
		String previewURLsDataApi = ARTEMIS_PATH +"/api/video/v1/cameras/previewURLs";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",previewURLsDataApi);
			}
		};
		String body=JSON.toJSONString(previewURLsRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	public static String getPersonLib(PersonLibRequest personLibRequest ){
		String dataApi = ARTEMIS_PATH +"/api/aiapplication/v1/face/faceLib/getPersonLib";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",dataApi);
			}
		};
		String body=JSON.toJSONString(personLibRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	/**
	 * 查询门禁点列表
	 * @param acsDoorRequest
	 * @return
	 */
	public static String acsDoorList(AcsDoorRequest acsDoorRequest ){
		String dataApi = ARTEMIS_PATH +"/api/resource/v1/acsDoor/advance/acsDoorList";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",dataApi);
			}
		};
		String body=JSON.toJSONString(acsDoorRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	public static String doorEvents(DoorEventsRequest doorEventsRequest ){
		String dataApi = ARTEMIS_PATH +"/api/acs/v1/door/events";
		Map<String,String> path = new HashMap<String,String>(2){
			{
				put("https://",dataApi);
			}
		};
		String body=JSON.toJSONString(doorEventsRequest);
		String result =ArtemisHttpUtil.doPostStringArtemis(path,body,null,null,"application/json");
		return result;
	}
	
	

	public static void main(String[] args){
		//GetAllTreeCodeRequest request=new GetAllTreeCodeRequest();
		//String result = getAllTreeCode(request);
		//String result = GetCameraPreviewURL();
		//System.out.println("result结果示例: " + result);
		
//		RegionsRequest request=new RegionsRequest();
//        request.setPageNo(1);
//        request.setPageSize(20);
//        request.setTreeCode("0");
//        regions(request);
        
//        CamerasRequest request=new CamerasRequest();
//        request.setPageNo(1);
//        request.setPageSize(20);
//        request.setTreeCode("0");
//        cameras(request);
		
//		PreviewURLsRequest request=new PreviewURLsRequest();
//        request.setCameraIndexCode("bbeef47300994d92900ab2c9db6a03d2");
//        request.setStreamType(0);
//        request.setProtocol("rtsp");
//        request.setTransmode(0);
//        String result = previewURLs(request);
//        System.out.println(result);
		
//		AcsDoorRequest acsDoorRequest = new AcsDoorRequest();
//		acsDoorRequest.setPageNo(1);
//		acsDoorRequest.setPageSize(10);
//		String acsDoorList = acsDoorList(acsDoorRequest);
//		System.out.println(acsDoorList);
		DoorEventsRequest doorEventsRequest = new DoorEventsRequest();
		doorEventsRequest.setDoorIndexCodes(new String[]{"b9d2320a297448c68c369a117866228a"});
		doorEventsRequest.setStartTime("2019-12-16T10:05:47.344+08:00");
		doorEventsRequest.setEndTime("2020-01-06T10:05:47.344+08:00");
		doorEventsRequest.setPageNo(3);
		doorEventsRequest.setPageSize(10);
		String acsDoorList = doorEvents(doorEventsRequest);
		System.out.println(acsDoorList);
    }
 }

