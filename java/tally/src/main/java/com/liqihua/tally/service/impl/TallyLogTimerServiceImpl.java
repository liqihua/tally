package com.liqihua.tally.service.impl;

import com.liqihua.core.basic.service.impl.BaseServiceImpl;
import com.liqihua.tally.dao.TallyLogTimerDao;
import com.liqihua.tally.entity.TallyLogTimer;
import com.liqihua.tally.entity.dto.DTOLogTimer;
import com.liqihua.tally.service.TallyLogTimerService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class TallyLogTimerServiceImpl extends BaseServiceImpl<TallyLogTimerDao,TallyLogTimer> implements TallyLogTimerService {

	@Override
	public DTOLogTimer getDTO(TallyLogTimer entity){
		DTOLogTimer logtimer = new DTOLogTimer();
		if(entity != null){
			try{
				BeanUtils.copyProperties(entity, logtimer);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return logtimer;
	}

	@Override
	public List<DTOLogTimer> getDTOList(List<TallyLogTimer> entityList){
    	List<DTOLogTimer> list = new ArrayList<DTOLogTimer>();
        if(entityList != null && entityList.size()>0){
        	for(TallyLogTimer entity : entityList){
        		list.add(getDTO(entity));
        	}
        }
        return list;
    }

}