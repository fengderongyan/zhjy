package com.ruoyi.mobile.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.support.json.JSONUtils;
import com.ruoyi.common.utils.PageData;
import com.ruoyi.common.utils.ResultUtils;
import com.ruoyi.common.utils.hkws.HaiKangVideoUtil;
import com.ruoyi.common.utils.hkws.domain.CamerasRequest;
import com.ruoyi.common.utils.hkws.domain.PreviewURLsRequest;
import com.ruoyi.common.utils.hkws.domain.RegionCamerasRequest;
import com.ruoyi.common.utils.hkws.domain.RegionsRequest;
import com.ruoyi.common.utils.hkws.domain.SubRegionsRequest;
import com.ruoyi.framework.web.controller.BaseController;

@RestController
@RequestMapping("/mobile/app/haikang")
public class HaiKangVideoController extends BaseController{
	
	/**
	 * 获取区域列表
	 * @return
	 */
	@RequestMapping("/regions")
	public Map regions(){
		try {
			PageData pd = this.getPageData();
			String pageNo = pd.getString("pageNo");
			if("".equals(pageNo)){
				return ResultUtils.returnError(12001, "pageNo不能为空");
				
			}
			String pageSize = pd.getString("pageSize");
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
				
			}
			String treeCode = pd.getString("treeCode");
			RegionsRequest request = new RegionsRequest();
			request.setPageNo(Integer.parseInt(pageNo));
			request.setPageSize(Integer.parseInt(pageSize));
			if(!"".equals(treeCode)){
				request.setTreeCode(treeCode);
			}
			String result = HaiKangVideoUtil.regions(request);
			Map map = new HashMap();
			if(!"".equals(result)){
				map = (Map)JSONUtils.parse(result);
			}
			
			return ResultUtils.returnOk(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
	
	/**
	 * 获取区域的下级区域
	 * @return
	 */
	@RequestMapping("/subRegions")
	public Map subRegions(){
		try {
			PageData pd = this.getPageData();
			String parentIndexCode = pd.getString("parentIndexCode");
			if("".equals(parentIndexCode)){
				return ResultUtils.returnError(12001, "parentIndexCode不能为空");
				
			}
			String treeCode = pd.getString("treeCode");
			SubRegionsRequest request = new SubRegionsRequest();
			request.setParentIndexCode(parentIndexCode);
			if(!"".equals(treeCode)){
				request.setTreeCode(treeCode);
			}
			String result = HaiKangVideoUtil.subRegions(request);
			Map map = new HashMap();
			if(!"".equals(result)){
				map = (Map)JSONUtils.parse(result);
			}
			
			return ResultUtils.returnOk(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
	
	/**
	 * 获取区域下的监控资源列表
	 * @return
	 */
	@RequestMapping("/regionCameras")
	public Map regionCameras(){
		try {
			PageData pd = this.getPageData();
			String pageNo = pd.getString("pageNo");
			if("".equals(pageNo)){
				return ResultUtils.returnError(12001, "pageNo不能为空");
				
			}
			String pageSize = pd.getString("pageSize");
			if("".equals(pageSize)){
				return ResultUtils.returnError(12001, "pageSize不能为空");
				
			}
			String regionIndexCode = pd.getString("regionIndexCode");
			if("".equals(regionIndexCode)){
				return ResultUtils.returnError(12001, "regionIndexCode不能为空");
				
			}
			String treeCode = pd.getString("treeCode");
			RegionCamerasRequest request = new RegionCamerasRequest();
			request.setPageNo(Integer.parseInt(pageNo));
			request.setPageSize(Integer.parseInt(pageSize));
			request.setRegionIndexCode(regionIndexCode);
			if(!"".equals(treeCode)){
				request.setTreeCode(treeCode);
			}
			String result = HaiKangVideoUtil.regionCameras(request);
			Map map = new HashMap();
			if(!"".equals(result)){
				map = (Map)JSONUtils.parse(result);
			}
			
			return ResultUtils.returnOk(map);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError(-1, "后台运行异常");
		}
		
	}
	
	
	/**
	 * 描述：获取监控列表
	 * @return
	 * @author yanbs
	 * @Date 2019-12-02
	 */
	@RequestMapping("/cameras")
	public Map cameras(){
		PageData pd = this.getPageData();
		String pageNo = pd.getString("pageNo");
		if("".equals(pageNo)){
			return ResultUtils.returnError(12001, "pageNo不能为空");
			
		}
		String pageSize = pd.getString("pageSize");
		if("".equals(pageSize)){
			return ResultUtils.returnError(12001, "pageSize不能为空");
			
		}
		String treeCode = pd.getString("treeCode");
		CamerasRequest request = new CamerasRequest();
		request.setPageNo(Integer.parseInt(pageNo));
		request.setPageSize(Integer.parseInt(pageSize));
		request.setTreeCode(treeCode);
		String result = HaiKangVideoUtil.cameras(request);
		Map map = new HashMap();
		if(!"".equals(result)){
			map = (Map)JSONUtils.parse(result);
		}
		
		return ResultUtils.returnOk(map);
		
	}
	
	/**
	 * 描述：获取视频流地址
	 * @return
	 * @author yanbs
	 * @Date 2019-12-02
	 */
	@RequestMapping("/previewURLs")
	public Map previewURLs(){
		PageData pd = this.getPageData();
		String cameraIndexCode = pd.getString("cameraIndexCode");
		if("".equals(cameraIndexCode)){
			return ResultUtils.returnError(12001, "cameraIndexCode不能为空");
			
		}
		String streamType = pd.getString("streamType");
		String protocol = pd.getString("protocol");
		String transmode = pd.getString("transmode");
		String expand = pd.getString("expand");
		PreviewURLsRequest request = new PreviewURLsRequest();
		request.setCameraIndexCode(cameraIndexCode);
		if(!"".equals(streamType)){
			request.setStreamType(Integer.parseInt(streamType));
		}
		if(!"".equals(protocol)){
			request.setProtocol(protocol);
		}
		
		if(!"".equals(transmode)){
			request.setTransmode(Integer.parseInt(transmode));
		}
		if(!"".equals(expand)){
			request.setExpand(expand);
		}
		String result = HaiKangVideoUtil.previewURLs(request);
		Map map = new HashMap();
		if(!"".equals(result)){
			map = (Map)JSONUtils.parse(result);
		}
		
		return ResultUtils.returnOk(map);
	}
}
