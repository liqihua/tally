package com.liqihua.tally.controller.api;

import cn.hutool.crypto.SecureUtil;
import com.liqihua.core.basic.controller.BaseController;
import com.liqihua.core.basic.result.BaseResult;
import com.liqihua.core.constance.ApiConstance;
import com.liqihua.tally.commons.redis.RedisUtil;
import com.liqihua.tally.commons.security.SysSecurity;
import com.liqihua.tally.entity.TallyUser;
import com.liqihua.tally.entity.dto.DTOUser;
import com.liqihua.tally.service.TallyUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author liqihua
 * @since 2018/7/11
 */
@Api(value="api-login-controller",description="登录")
@SpringBootApplication
@RequestMapping("/api/loginApiController")
public class LoginApiControler extends BaseController {
    @Autowired
    private TallyUserService userService;
    @Autowired
    private SysSecurity sysSecurity;
    @Autowired
    private RedisUtil redisUtil;



    @ApiOperation(value = "login")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiResponses({@ApiResponse(code = ApiConstance.BASE_SUCCESS_CODE, message = "成功", response = String.class)})
    public ResponseEntity<BaseResult> login(@ApiParam(value = "username",required = true) @RequestParam(value="username",required=true) String username,
                                            @ApiParam(value = "password",required = true) @RequestParam(value="password",required=true) String password){
        TallyUser _u = new TallyUser();
        _u.setUsername(username);
        _u.setPassword(password);
        List<TallyUser> list = userService.findList(_u);
        if(list != null && list.size()>0){
            String token = SecureUtil.md5(username+System.currentTimeMillis());
            redisUtil.set("token_"+list.get(0).getId(), token,48 * 60 * 60);
            DTOUser dto = userService.getDTO(list.get(0));
            dto.token = token;
            return buildSuccessInfo(dto);
        }
        return buildFailedInfo("账号或密码错误");
    }










}
