package com.liqihua.tally.service;

import com.liqihua.core.basic.service.IBaseService;
import com.liqihua.tally.entity.TallyLogTimer;
import com.liqihua.tally.entity.dto.DTOLogTimer;

import java.util.List;

public interface TallyLogTimerService extends IBaseService<TallyLogTimer> {

    public DTOLogTimer getDTO(TallyLogTimer entity);

    public List<DTOLogTimer> getDTOList(List<TallyLogTimer> entityList);

}