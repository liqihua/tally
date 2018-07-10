package com.liqihua.tally.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel("支出记录")
public class DTOLogPay implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("id")
	public Long id;
	@ApiModelProperty("用户id")
	public Long userId;
	@ApiModelProperty("金额/分")
	public Long total;
	@ApiModelProperty("商品名称")
	public String productName;
	@ApiModelProperty("商品类型")
	public String productType;
	@ApiModelProperty("消费时间")
	public Date payTime;
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
    

    public Long getUserId() {
    	return userId;
    }
    public void setUserId(Long userId) {
    	this.userId = userId;
    }
    

    public Long getTotal() {
    	return total;
    }
    public void setTotal(Long total) {
    	this.total = total;
    }
    

    public String getProductName() {
    	return productName;
    }
    public void setProductName(String productName) {
    	this.productName = productName;
    }
    

    public String getProductType() {
    	return productType;
    }
    public void setProductType(String productType) {
    	this.productType = productType;
    }
    

    public Date getPayTime() {
    	return payTime;
    }
    public void setPayTime(Date payTime) {
    	this.payTime = payTime;
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