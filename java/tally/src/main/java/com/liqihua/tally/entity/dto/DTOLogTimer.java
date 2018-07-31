package com.liqihua.tally.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("timer运行日志表")
public class DTOLogTimer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("id")
	public Long id;
	@ApiModelProperty("任务名称")
	public String name;
	@ApiModelProperty("开始时间")
	public Date startTime;
	@ApiModelProperty("完成时间")
	public Date endTime;
	@ApiModelProperty("更新时间")
	public Date updateDate;
	@ApiModelProperty("创建时间")
	public Date createDate;
	
	
    

    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    

    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    

    public Date getStartTime() {
    	return startTime;
    }
    public void setStartTime(Date startTime) {
    	this.startTime = startTime;
    }
    

    public Date getEndTime() {
    	return endTime;
    }
    public void setEndTime(Date endTime) {
    	this.endTime = endTime;
    }
	
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}