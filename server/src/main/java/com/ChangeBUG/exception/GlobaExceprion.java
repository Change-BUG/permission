package com.ChangeBUG.exception;

import com.ChangeBUG.utils.RespListUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class GlobaExceprion {

    /**
     * 空构造方法，避免反序列化问题
     */
    public GlobaExceprion()
    {
    }

    /**
     * 数据库 异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public RespListUtils SQLException(SQLException e) {
        if (e instanceof SQLIntegrityConstraintViolationException) {
            log.error("================================ 该数据错误 ================================", e);
            return RespListUtils.error(e.getMessage());
        }
        log.error("================================ 数据库错误 ================================", e);
        return RespListUtils.error(e.getMessage());
    }

    /**
     * 数据格式 异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RespListUtils HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("================================ 传入数据异常或者格式异常 ================================", e);
        return RespListUtils.error("传入数据异常或者格式异常");
    }

    /**
     * 捕获运行时异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public RespListUtils RuntimeException(RuntimeException e) {
        log.error("====================================== 运行时异常 ======================================", e);
        return RespListUtils.error(e.getMessage());
    }

    /**
     * 实体校验异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public RespListUtils MethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("===================================== 实体捕获异常 =====================================", e);
        return RespListUtils.error("实体捕获异常");
    }

    /**
     * 处理请求方法不支持的异常
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public RespListUtils exceptionHandler(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        log.error("发生请求方法不支持异常！原因是:", e);
        return RespListUtils.error("发生请求方法不支持异常");
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespListUtils exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常！原因是:", e);
        return RespListUtils.error(e.getMessage());
    }

}
