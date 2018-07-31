package com.liqihua.tally.controller.api;

import cn.hutool.core.date.DateUtil;
import com.liqihua.core.basic.controller.BaseController;
import com.liqihua.core.basic.result.BaseResult;
import com.liqihua.core.constance.ApiConstance;
import com.liqihua.core.page.SysPage;
import com.liqihua.tally.entity.TallyCountType;
import com.liqihua.tally.entity.TallyLogPay;
import com.liqihua.tally.entity.dto.DTOLogPay;
import com.liqihua.tally.service.TallyCountTypeService;
import com.liqihua.tally.service.TallyLogPayService;
import com.liqihua.tally.service.TallyUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liqihua
 * @since 2018/7/12
 */
@Api(value="api-count-controller",description="统计")
@SpringBootApplication
@RequestMapping("/api/countApiController")
public class CountApiController extends BaseController {
    @Autowired
    private TallyCountTypeService countTypeService;


    @ApiOperation(value = "各类型总额")
    @RequestMapping(value = "/countType", method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> countType(@ApiParam(value = "userId",required = true) @RequestParam(value="userId",required=true) Long userId){
        TallyCountType _count = new TallyCountType();
        _count.setUserId(userId);
        _count.setOrderBy(" a.total DESC ");
        List<TallyCountType> list = countTypeService.findList(_count);
        return buildSuccessInfo(countTypeService.getDTOList(list));
    }




}
