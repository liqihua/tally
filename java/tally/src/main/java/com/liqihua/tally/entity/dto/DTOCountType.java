package com.liqihua.tally.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("类型消费总额统计")
public class DTOCountType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("id")
	public Long id;
	@ApiModelProperty("类型")
	public String type;
	@ApiModelProperty("总金额/分")
	public Long total;
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
    

    public String getType() {
    	return type;
    }
    public void setType(String type) {
    	this.type = type;
    }
    

    public Long getTotal() {
    	return total;
    }
    public void setTotal(Long total) {
    	this.total = total;
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