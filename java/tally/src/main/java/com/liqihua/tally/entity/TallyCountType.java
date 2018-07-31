package com.liqihua.tally.entity;

import com.liqihua.core.basic.entity.BaseEntity;

public class TallyCountType extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private Long userId;//用户id
	private String type;	//类型
	private Long total;	//总金额/分
	
	public TallyCountType() {
		super();
	}

	public TallyCountType(Long userId, String type, Long total) {
		this.userId = userId;
		this.type = type;
		this.total = total;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "TallyCountType{" +
				"userId=" + userId +
				", type='" + type + '\'' +
				", total=" + total +
				'}';
	}
}