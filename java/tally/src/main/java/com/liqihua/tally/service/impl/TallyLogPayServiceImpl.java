package com.liqihua.tally.service.impl;

import com.liqihua.core.basic.service.impl.BaseServiceImpl;
import com.liqihua.tally.dao.TallyLogPayDao;
import com.liqihua.tally.entity.TallyLogPay;
import com.liqihua.tally.entity.dto.DTOLogPay;
import com.liqihua.tally.service.TallyLogPayService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true,propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class TallyLogPayServiceImpl extends BaseServiceImpl<TallyLogPayDao,TallyLogPay> implements TallyLogPayService {

	@Override
	public DTOLogPay getDTO(TallyLogPay entity){
		DTOLogPay logpay = new DTOLogPay();
		if(entity != null){
			try{
				BeanUtils.copyProperties(entity, logpay);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return logpay;
	}


	@Override
	public List<DTOLogPay> getDTOList(List<TallyLogPay> entityList){
    	List<DTOLogPay> list = new ArrayList<DTOLogPay>();
        if(entityList != null && entityList.size()>0){
        	for(TallyLogPay entity : entityList){
        		list.add(getDTO(entity));
        	}
        }
        return list;
    }

}