package com.liqihua.tally.dao;

import org.apache.ibatis.annotations.Mapper;
import com.liqihua.tally.entity.TallyUser;
import com.liqihua.core.basic.dao.BaseDao;


@Mapper
public interface TallyUserDao extends BaseDao<TallyUser>{
	
}
