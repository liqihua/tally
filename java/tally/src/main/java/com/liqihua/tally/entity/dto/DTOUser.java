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
	@ApiModelProperty("密码")
	public String password;
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