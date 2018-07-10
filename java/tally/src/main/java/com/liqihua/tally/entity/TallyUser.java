package com.liqihua.tally.entity;

import org.springframework.format.annotation.DateTimeFormat;

import com.liqihua.core.basic.entity.BaseEntity;
import java.util.Date;

public class TallyUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String username;	//账号
	private String password;	//密码
	
	public TallyUser() {
		super();
	}
	
    
    public String getUsername() {
    	return username;
    }
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
	
}