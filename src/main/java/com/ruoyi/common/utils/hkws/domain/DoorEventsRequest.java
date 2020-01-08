package com.ruoyi.common.utils.hkws.domain;

public class DoorEventsRequest {

	private String startTime;
	private String endTime;
	private String[] doorIndexCodes;
	private Integer pageNo;
	private Integer pageSize;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String[] getDoorIndexCodes() {
		return doorIndexCodes;
	}
	public void setDoorIndexCodes(String[] doorIndexCodes) {
		this.doorIndexCodes = doorIndexCodes;
	}
}
