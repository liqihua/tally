package com.liqihua.tally.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.liqihua.core.basic.service.impl.BaseServiceImpl;

import com.liqihua.tally.entity.dto.DTOUser;
import com.liqihua.tally.entity.TallyUser;
import com.liqihua.tally.dao.TallyUserDao;
import com.liqihua.tally.service.TallyUserService;




@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class TallyUserServiceImpl extends BaseServiceImpl<TallyUserDao,TallyUser> implements TallyUserService {


	@Override
	public DTOUser getDTO(TallyUser entity) {
		DTOUser user = new DTOUser();
		if(entity != null){
			try{
				BeanUtils.copyProperties(entity, user);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<DTOUser> getDTOList(List<TallyUser> entityList) {
		List<DTOUser> list = new ArrayList<DTOUser>();
		if(entityList != null && entityList.size()>0){
			for(TallyUser entity : entityList){
				list.add(getDTO(entity));
			}
		}
		return list;
	}


}