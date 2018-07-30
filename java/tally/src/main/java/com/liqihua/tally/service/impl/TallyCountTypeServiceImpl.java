package com.liqihua.tally.service.impl;

import com.liqihua.core.basic.service.impl.BaseServiceImpl;
import com.liqihua.tally.dao.TallyCountTypeDao;
import com.liqihua.tally.entity.TallyCountType;
import com.liqihua.tally.entity.dto.DTOCountType;
import com.liqihua.tally.service.TallyCountTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class TallyCountTypeServiceImpl extends BaseServiceImpl<TallyCountTypeDao,TallyCountType> implements TallyCountTypeService {

	@Override
	public DTOCountType getDTO(TallyCountType entity){
		DTOCountType counttype = new DTOCountType();
		if(entity != null){
			try{
				BeanUtils.copyProperties(entity, counttype);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return counttype;
	}

	@Override
	public List<DTOCountType> getDTOList(List<TallyCountType> entityList){
    	List<DTOCountType> list = new ArrayList<DTOCountType>();
        if(entityList != null && entityList.size()>0){
        	for(TallyCountType entity : entityList){
        		list.add(getDTO(entity));
        	}
        }
        return list;
    }

}