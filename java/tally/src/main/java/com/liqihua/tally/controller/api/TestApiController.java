package com.liqihua.tally.controller.api;

import com.liqihua.core.basic.controller.BaseController;
import com.liqihua.core.basic.result.BaseResult;
import com.liqihua.core.constance.ApiConstance;
import com.liqihua.core.utils.Tool;
import com.liqihua.tally.commons.security.SysSecurity;
import com.liqihua.tally.entity.TallyCountType;
import com.liqihua.tally.entity.TallyUser;
import com.liqihua.tally.service.TallyCountTypeService;
import com.liqihua.tally.service.TallyUserService;
import com.liqihua.tally.timer.TallyTimer;
import io.swagger.annotations.*;
import net.bytebuddy.asm.Advice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * @author liqihua
 * @since 2018/7/11
 */
@Api(value="api-test-controller",description="测试")
@SpringBootApplication
@RequestMapping("/api/testApiController")
public class TestApiController extends BaseController {
    private static Logger LOG = LogManager.getLogger(TestApiController.class);

    @Autowired
    private SysSecurity sysSecurity;
    @Autowired
    private TallyCountTypeService countTypeService;


    @ApiOperation(value = "描述aa")
    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> test1(@ApiParam(value = "aa",required = true) @RequestParam(value="aa",required=false) String aa){
        return buildSuccessInfo(null);
    }


    @ApiOperation(value = "加密")
    @RequestMapping(value = "/encode", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> encode(@ApiParam(value = "aa",required = true) @RequestParam(value="aa",required=true) String aa){
        String result = sysSecurity.encode(aa);
        LOG.info("--- encode : "+result);
        return buildSuccessInfo(result);
    }


    @ApiOperation(value = "解密")
    @RequestMapping(value = "/decode", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> decode(@ApiParam(value = "aa",required = true) @RequestParam(value="aa",required=true) String aa){
        String result = sysSecurity.decode(aa);
        LOG.info("--- decode : "+result);
        return buildSuccessInfo(result);
    }



    @ApiOperation(value = "统计A")
    @RequestMapping(value = "/countA", method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> countA(){
        return buildSuccessInfo(countTypeService.getDTOList(countTypeService.findList(null)));
    }
}
