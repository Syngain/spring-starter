package com.hualongdata.springstarter.web.controllers;

import com.hualongdata.domain.ApiResponse;
import com.hualongdata.exception.HlBaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认异常处理
 * Created by yangbajing on 16-9-14.
 */
@ControllerAdvice(basePackages = "com.hualongdata.springstarter.web.controllers")
public class SpringStarterControllerAdvice extends ResponseEntityExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(SpringStarterControllerAdvice.class);

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        logger.error("系统错误", ex);
        return new ResponseEntity<>(ApiResponse.error(status.value(), "系统错误"), headers, HttpStatus.OK);
    }

    /**
     * 重设HlBaseException为返回200状态码，并输出用户用好的JSON内容：{"errCode":500,"errMsg":""}
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(HlBaseException.class)
    @ResponseBody
    ResponseEntity<?> handleSBaseException(HttpServletRequest request, HlBaseException ex) {
        logger.debug("运行异常", ex);
        return new ResponseEntity<>(ApiResponse.error(ex.getErrCode(), ex.getErrMsg()), HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    ResponseEntity<?> handleRuntimeException(HttpServletRequest request, RuntimeException ex) {
        HttpStatus status = getStatus(request);
        logger.error("运行异常", ex);
        return new ResponseEntity<>(ApiResponse.error(status.value(), "运行异常"), HttpStatus.OK);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
