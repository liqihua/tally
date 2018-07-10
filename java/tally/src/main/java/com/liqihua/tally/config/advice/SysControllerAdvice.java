package com.liqihua.tally.config.advice;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.liqihua.core.basic.controller.BaseController;
import com.liqihua.core.basic.result.BaseResult;
import com.liqihua.core.constance.ApiConstance;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(basePackages = {"com"})
public class SysControllerAdvice extends BaseController {


	//400错误->缺少参数异常
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseEntity<BaseResult> requestMissingServletRequest(MissingServletRequestParameterException ex){
        return buildFailedInfo(ApiConstance.PARAM_IS_NULL,"："+ex.getParameterName());
    }
    
    
    
    //400错误->参数类型异常
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<BaseResult> requestTypeMismatch(TypeMismatchException ex){
    	return buildFailedInfo(ApiConstance.PARAM_TYPE_ERROR," "+ex.getValue()+"："+ex.getRequiredType().getName());
    }


    //400错误->json参数格式有误
    @ExceptionHandler({JsonParseException.class})
    @ResponseBody
    public ResponseEntity<BaseResult> jsonParamError(JsonParseException ex){
        return buildFailedInfo(ApiConstance.PARAM_JSON_ERROR);
    }

    //400错误->参数格式有误
    @ExceptionHandler({InvalidFormatException.class})
    @ResponseBody
    public ResponseEntity<BaseResult> invalidFormatException(InvalidFormatException ex){
        return buildFailedInfo(ApiConstance.PARAM_FORMAT_ERROR);
    }
    
    
}
