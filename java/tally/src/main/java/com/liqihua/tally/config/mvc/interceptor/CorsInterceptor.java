package com.liqihua.tally.config.mvc.interceptor;

import com.liqihua.core.utils.Tool;
import org.apache.logging.log4j.*;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 允许跨域的拦截器
 */
public class CorsInterceptor extends HandlerInterceptorAdapter {
    private static Logger LOG = LogManager.getLogger(CorsInterceptor.class);

    @Resource
    Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String corsSuffix = environment.getProperty("cors.suffix");
        LOG.info("corsSuffix:"+corsSuffix,this.getClass());
        if(Tool.isBlank(corsSuffix)){
            response.addHeader("Access-Control-Allow-Origin", "*");
        }else{
            String origin = request.getHeader("Origin");
            LOG.info("origin:"+origin,this.getClass());
            if(Tool.isNotBlank(origin) && corsSuffix.contains(origin)){
                LOG.info("add origin : "+origin,this.getClass());
                response.addHeader("Access-Control-Allow-Origin", origin);
            }
        }
        response.addHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,PUT,DELETE,HEAD");
        response.addHeader("Access-Control-Allow-Headers", "S_ID,content-type");
        response.addHeader("Access-Control-Max-Age", "3600000");
        response.addHeader("Access-Control-Allow-Credentials", "true");

        //让请求，不被缓存，
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        return true;
    }

}
