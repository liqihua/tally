package com.liqihua.tally.timer;

import com.liqihua.core.utils.Tool;
import com.liqihua.tally.entity.TallyCountType;
import com.liqihua.tally.service.TallyCountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/7/30
 */
@Component
public class TallyTimer {
    @Autowired
    private TallyCountTypeService countTypeService;


    @Scheduled(cron = "0 0 2 * * ?")
    public void countType(){
        List<Map<String,Object>> list = countTypeService.findListSQL("SELECT DISTINCT(product_type) FROM tally_log_pay");
        if(list != null && list.size() > 0){
            for(Map<String,Object> map : list){
                String productType = (String) map.get("product_type");
                if(Tool.isNotBlank(productType)){
                    String SQL = "SELECT COALESCE(SUM(total),0) AS total FROM tally_log_pay WHERE product_type='"+productType+"'";
                    List<Map<String,Object>> totalList = countTypeService.findListSQL(SQL);
                    BigDecimal total = (BigDecimal) totalList.get(0).get("total");
                    if(total.compareTo(BigDecimal.ZERO) == 1){
                        TallyCountType countLog = null;
                        TallyCountType _type = new TallyCountType();
                        _type.setType(productType);
                        List<TallyCountType> countList = countTypeService.findList(_type);
                        if(countList != null && countList.size() > 0){
                            countLog = countList.get(0);
                        }else{
                            countLog = new TallyCountType();
                            countLog.setType(productType);
                        }
                        countLog.setTotal(total.longValue());
                        countTypeService.save(countLog);
                    }
                }
            }
        }

    }



}
