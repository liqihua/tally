package com.liqihua.tally.dao;

import com.liqihua.core.basic.dao.BaseDao;
import com.liqihua.tally.entity.TallyLogPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;


@Mapper
public interface TallyLogPayDao extends BaseDao<TallyLogPay>{


    @Select("SELECT COALESCE(MAX(rank),0) FROM tally_log_pay WHERE TO_DAYS(pay_time) = TO_DAYS(#{payTime})")
    public Integer getMaxRankByPayTime(@Param("payTime") Date payTime);


}
