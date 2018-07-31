package com.liqihua.tally.entity;

import com.liqihua.core.basic.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class TallyLogTimer extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;	//任务名称
	private Date startTime;	//开始时间
	private Date endTime;	//完成时间
	
	public TallyLogTimer() {
		super();
	}
	
    
    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getStartTime() {
    	return startTime;
    }
    public void setStartTime(Date startTime) {
    	this.startTime = startTime;
    }
    
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getEndTime() {
    	return endTime;
    }
    public void setEndTime(Date endTime) {
    	this.endTime = endTime;
    }
	
}