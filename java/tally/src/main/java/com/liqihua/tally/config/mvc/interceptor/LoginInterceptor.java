package com.liqihua.tally.config.mvc.interceptor;

import com.liqihua.core.basic.result.BaseResult;
import com.liqihua.core.constance.ApiConstance;
import com.liqihua.core.utils.Tool;
import com.liqihua.tally.commons.redis.RedisUtil;
import com.liqihua.tally.commons.security.SysSecurity;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author liqihua
 * @since 2018/7/11
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SysSecurity sysSecurity;
    @Autowired
    private RedisUtil redisUtil;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        if(Tool.isNotBlank(token)){
            String userId = request.getParameter("userId");
            String cacheToken = (String) redisUtil.get("token_"+userId);
            if(Tool.isNotBlank(cacheToken) && token.equals(cacheToken)){
                return true;
            }
        }
        BaseResult result = new BaseResult(ApiConstance.NO_LOGIN, ApiConstance.getMessage(ApiConstance.NO_LOGIN), null);
        JSONObject json = JSONObject.fromObject(result);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().print(json.toString());
        return false;
    }
}
