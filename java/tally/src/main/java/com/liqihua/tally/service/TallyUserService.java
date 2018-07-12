package com.liqihua.tally.service;

import com.liqihua.tally.entity.TallyUser;
import com.liqihua.core.basic.service.IBaseService;
import com.liqihua.tally.entity.dto.DTOUser;

import java.util.List;

public interface TallyUserService extends IBaseService<TallyUser> {

    public DTOUser getDTO(TallyUser entity);

    public List<DTOUser> getDTOList(List<TallyUser> entityList);


}