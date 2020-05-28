package com.xjtuse.mall.customException;

import com.xjtuse.mall.result.TResultVo;
import com.xjtuse.mall.utils.ResultUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({CustomException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TResultVo handleCustomException(CustomException e){
        return ResultUtil.genFailResult(e.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public TResultVo handleMessageNotReadableException(HttpMessageNotReadableException e){
        return ResultUtil.genFailResult("请输入合法的参数！！！");
    }
}
