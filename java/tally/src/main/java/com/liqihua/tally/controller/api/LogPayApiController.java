package com.liqihua.tally.controller.api;

import cn.hutool.core.date.DateUtil;
import com.liqihua.core.basic.controller.BaseController;
import com.liqihua.core.basic.result.BaseResult;
import com.liqihua.core.constance.ApiConstance;
import com.liqihua.core.page.SysPage;
import com.liqihua.core.utils.Tool;
import com.liqihua.tally.entity.TallyLogPay;
import com.liqihua.tally.entity.dto.DTOLogPay;
import com.liqihua.tally.service.TallyLogPayService;
import com.liqihua.tally.service.TallyUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/7/12
 */
@Api(value="api-log-pay-controller",description="支出记录")
@SpringBootApplication
@RequestMapping("/api/logPayApiController")
public class LogPayApiController extends BaseController {

    @Autowired
    private TallyUserService userService;
    @Autowired
    private TallyLogPayService logPayService;


    @ApiOperation(value = "保存记录")
    @RequestMapping(value = "/saveLogPay", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> saveLogPay(@ApiParam(value = "logId",required = false) @RequestParam(value="logId",required=false) Long logId,
                                                @ApiParam(value = "userId",required = true) @RequestParam(value="userId",required=true) Long _userId,
                                                @ApiParam(value = "total",required = true) @RequestParam(value="total",required=true) Long total,
                                                @ApiParam(value = "productName",required = true) @RequestParam(value="productName",required=true) String productName,
                                                @ApiParam(value = "productType",required = true) @RequestParam(value="productType",required=true) String productType,
                                                @ApiParam(value = "payTime：yyyy-MM-dd",required = true) @RequestParam(value="payTime",required=true) String payTime){

        Long userId = Long.valueOf(_userId);
        TallyLogPay logPay = null;
        if(logId != null){
            logPay = logPayService.get(logId);
            if(logPay == null){
                return buildFailedInfo(ApiConstance.LOG_NOT_EXIST);
            }
        }else{
            Date date = null;
            try {
                date = DateUtil.parse(payTime);
            }catch(Exception e){
                return buildFailedInfo(ApiConstance.PARAM_DATE_ERROR);
            }
            logPay = new TallyLogPay(userId, total, productName, productType, date);
        }
        logPayService.save(logPay);
        DTOLogPay dto = logPayService.getDTO(logPay);
        return buildSuccessInfo(dto);
    }


    @ApiOperation(value = "删除记录")
    @RequestMapping(value = "/deleteLogPay", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> deleteLogPay(@ApiParam(value = "logId",required = false) @RequestParam(value="logId",required=false) Long logId){
        TallyLogPay log = logPayService.get(logId);
        if(log == null){
            return buildFailedInfo(ApiConstance.LOG_NOT_EXIST);
        }
        logPayService.delete(log);
        DTOLogPay dto = logPayService.getDTO(log);
        return buildSuccessInfo(dto);
    }



    @ApiOperation(value = "查询记录列表")
    @RequestMapping(value = "/findPayLogList", method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> findPayLogList(@ApiParam(value = "userId",required = true) @RequestParam(value="userId",required=true) Long _userId,
                                                     @ApiParam(value = "page",required = true) @RequestParam(value="page",required=true) Integer page,
                                                     @ApiParam(value = "pageSize",required = true) @RequestParam(value="pageSize",required=true) Integer pageSize,
                                                     @ApiParam(value = "productName",required = false) @RequestParam(value="productName",required=false) String productName,
                                                     @ApiParam(value = "productType",required = false) @RequestParam(value="productType",required=false) String productType,
                                                     @ApiParam(value = "payTimeStart：yyyy-MM-dd",required = false) @RequestParam(value="payTimeStart",required=false) String payTimeStart,
                                                     @ApiParam(value = "payTimeEnd：yyyy-MM-dd",required = false) @RequestParam(value="payTimeEnd",required=false) String payTimeEnd){
        Long userId = Long.valueOf(_userId);
        TallyLogPay _log = new TallyLogPay();
        _log.setOrderBy(" a.pay_time DESC");
        _log.setUserId(userId);
        _log.setProductName(productName);
        _log.setProductType(productType);
        _log.payTimeStart = DateUtil.parse(payTimeStart);
        _log.payTimeEnd = DateUtil.parse(payTimeEnd);
        _log.setFirst(page*pageSize);
        _log.setMax(pageSize);
        SysPage<TallyLogPay> sysPage = logPayService.findPage(_log);
        Map<String,Object> map = new HashMap<String,Object>(5);
        map.put("page",page);
        map.put("pageSize",pageSize);
        map.put("pageTotal",sysPage.getPageTotal());
        map.put("count",sysPage.getCount());
        map.put("list",sysPage.getList());
        return buildSuccessInfo(map);
    }






}
