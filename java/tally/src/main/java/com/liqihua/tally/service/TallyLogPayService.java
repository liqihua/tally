package com.liqihua.tally.service;

import com.liqihua.core.basic.service.IBaseService;
import com.liqihua.tally.entity.TallyLogPay;
import com.liqihua.tally.entity.dto.DTOLogPay;

import java.util.List;

public interface TallyLogPayService extends IBaseService<TallyLogPay> {

    public DTOLogPay getDTO(TallyLogPay entity);


    public List<DTOLogPay> getDTOList(List<TallyLogPay> entityList);



}