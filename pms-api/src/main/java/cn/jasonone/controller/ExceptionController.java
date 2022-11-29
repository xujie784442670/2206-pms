package cn.jasonone.controller;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.jasonone.model.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xujie
 * @version 1.0.0
 */
@Slf4j
@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResult handler(NotLoginException e){
        return HttpResult.error(40000,"未登录或登录已失效");
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HttpResult handler(NotPermissionException e){
        return HttpResult.error(40001,e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpResult handler(MethodArgumentNotValidException e) {
        BindingResult br = e.getBindingResult();
        List<FieldError> errors = br.getFieldErrors();
        String msg = errors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("<br>"));
        return HttpResult.error(400, msg);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HttpResult handler(Throwable t, HttpServletRequest req) {
        log.error("系统异常:["+req.getMethod()+"]"+ req.getRequestURI(), t);
        return HttpResult.error(500, t.getMessage());
    }
}
