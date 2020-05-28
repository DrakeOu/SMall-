package com.xjtuse.mall.customException;

import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@ControllerAdvice
//public class JsonInputExceptionHandler extends ResponseEntityExceptionHandler {
//
//    /**
//     * 响应全局的json输入解析错误
//     * @param ex
//     * @param body
//     * @param headers
//     * @param status
//     * @param request
//     * @return
//     */
//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return new ResponseEntity<Object>(ResultUtil.genFailResult("请输入合法的参数！！！"), HttpStatus.OK) ;
//    }
//}
