package com.liqihua.tally.service;

import com.liqihua.core.basic.service.IBaseService;
import com.liqihua.tally.entity.TallyCountType;
import com.liqihua.tally.entity.dto.DTOCountType;

import java.util.List;

public interface TallyCountTypeService extends IBaseService<TallyCountType> {

    public DTOCountType getDTO(TallyCountType entity);

    public List<DTOCountType> getDTOList(List<TallyCountType> entityList);

}