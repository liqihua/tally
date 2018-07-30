package com.liqihua.tally.entity;

import com.liqihua.core.basic.entity.BaseEntity;

public class TallyCountType extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String type;	//类型
	private Long total;	//总金额/分
	
	public TallyCountType() {
		super();
	}

	public TallyCountType(String type, Long total) {
		this.type = type;
		this.total = total;
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
	
}