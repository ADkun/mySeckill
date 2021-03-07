package com.adkun.mySeckill.controller.advice;

import com.adkun.mySeckill.common.BusinessException;
import com.adkun.mySeckill.common.ErrorCode;
import com.adkun.mySeckill.common.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

/**
 * controller异常处理
 * @author adkun
 */
@RestControllerAdvice
@Slf4j
public class ExceptionAdvice implements ErrorCode {

    /**
     * 统一的controller抛出异常处理组件
     * @param e 接收到的异常
     * @return 异常错误信息（ResponseModel封装）
     */
    @ExceptionHandler
    public ResponseModel handleException(Exception e) {
        Map<Object, Object> data = new HashMap<>();
        if (e instanceof BusinessException) {
            data.put("code", ((BusinessException) e).getCode());
            data.put("message", ((BusinessException) e).getMessage());
        } else if (e instanceof NoHandlerFoundException) {
            // HandlerMapping找不到对应的Controller地址
            data.put("code", UNDEFINED_ERROR);
            data.put("message", "该资源不存在！");
        } else {
            data.put("code", UNDEFINED_ERROR);
            data.put("message", "发生未知错误！");
            log.error("发生未知错误！", e);
        }

        return new ResponseModel(ResponseModel.STATUS_FAILURE, data);
    }

}
