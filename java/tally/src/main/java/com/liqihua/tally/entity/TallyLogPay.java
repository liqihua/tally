package com.liqihua.tally.entity;

import com.liqihua.core.basic.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TallyLogPay extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;	//用户id
	private Long total;	//金额/分
	private String productName;	//商品名称
	private String productType;	//商品类型
	private Date payTime;	//消费时间
    private Integer rank;


    //查询条件
    public Date payTimeStart;
    public Date payTimeEnd;

	public TallyLogPay() {
		super();
	}

    public TallyLogPay(Long userId, Long total, String productName, String productType, Date payTime) {
        this.userId = userId;
        this.total = total;
        this.productName = productName;
        this.productType = productType;
        this.payTime = payTime;
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
    
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getPayTime() {
    	return payTime;
    }
    public void setPayTime(Date payTime) {
    	this.payTime = payTime;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}