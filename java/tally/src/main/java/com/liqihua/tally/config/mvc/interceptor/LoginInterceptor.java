package com.liqihua.tally.config.mvc.interceptor;

import com.liqihua.core.basic.result.BaseResult;
import com.liqihua.core.constance.ApiConstance;
import com.liqihua.core.utils.Tool;
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


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] arr = request.getCookies();
        if(arr != null && arr.length > 0){
            String username = null;
            String token = null;
            for(Cookie cookie : arr){
                if("username".equals(cookie.getName())){
                    username = cookie.getValue();
                }
                if("token".equals(cookie.getName())){
                    token = cookie.getValue();
                }
            }
            if(Tool.isNotBlank(username,token)){
                token = sysSecurity.decode(token);
                if(username.equals(token)){
                    return true;
                }
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
