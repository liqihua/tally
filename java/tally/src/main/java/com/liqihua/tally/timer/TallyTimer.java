package com.liqihua.tally.timer;

import com.liqihua.core.utils.Tool;
import com.liqihua.tally.controller.api.TestApiController;
import com.liqihua.tally.entity.TallyCountType;
import com.liqihua.tally.entity.TallyLogTimer;
import com.liqihua.tally.entity.TallyUser;
import com.liqihua.tally.service.TallyCountTypeService;
import com.liqihua.tally.service.TallyLogTimerService;
import com.liqihua.tally.service.TallyUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/7/30
 */
@Component
public class TallyTimer {
    private static Logger LOG = LogManager.getLogger(TallyTimer.class);

    @Autowired
    private TallyCountTypeService countTypeService;
    @Autowired
    private TallyUserService userService;
    @Autowired
    private TallyLogTimerService logTimerService;


    @Scheduled(cron = "0 0 2 * * ?")
    public void countType(){
        Date start = new Date();
        LOG.info("--- countType start " +Tool.formatDate(start,null));

        List<TallyUser> userList = userService.findList(null);
        if(userList != null && userList.size() > 0){
            for(TallyUser user : userList){
                List<Map<String,Object>> list = countTypeService.findListSQL("SELECT DISTINCT(product_type) FROM tally_log_pay WHERE user_id = "+user.getId());
                if(list != null && list.size() > 0){
                    for(Map<String,Object> map : list){
                        String productType = (String) map.get("product_type");
                        if(Tool.isNotBlank(productType)){
                            String SQL = "SELECT COALESCE(SUM(total),0) AS total FROM tally_log_pay WHERE product_type='"+productType+"' AND user_id = "+user.getId();
                            List<Map<String,Object>> totalList = countTypeService.findListSQL(SQL);
                            BigDecimal total = (BigDecimal) totalList.get(0).get("total");
                            if(total.compareTo(BigDecimal.ZERO) == 1){
                                TallyCountType countLog = null;
                                TallyCountType _type = new TallyCountType();
                                _type.setType(productType);
                                _type.setUserId(user.getId());
                                List<TallyCountType> countList = countTypeService.findList(_type);
                                if(countList != null && countList.size() > 0){
                                    countLog = countList.get(0);
                                }else{
                                    countLog = new TallyCountType();
                                    countLog.setType(productType);
                                    countLog.setUserId(user.getId());
                                }
                                countLog.setTotal(total.longValue());
                                countTypeService.save(countLog);
                                LOG.info("--- saved ：" + countLog.toString());

                            }
                        }
                    }
                }
            }
        }


        Date end = new Date();
        LOG.info("--- countType end " +Tool.formatDate(end,null));

        //保存运行记录
        TallyLogTimer runLog = new TallyLogTimer("countType",start,end);
        logTimerService.save(runLog);

    }



}
