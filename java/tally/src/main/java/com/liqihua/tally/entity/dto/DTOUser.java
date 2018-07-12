package com.liqihua.tally.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.io.Serializable;

@ApiModel("账号表")
public class DTOUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("id")
	public Long id;
	@ApiModelProperty("账号")
	public String username;
	
	
    

    public Long getId() {
    	return id;
    }
    public void setId(Long id) {
    	this.id = id;
    }
    

    public String getUsername() {
    	return username;
    }
    public void setUsername(String username) {
    	this.username = username;
    }
    

	
}