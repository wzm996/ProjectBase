package com.projectbase.common.handler;

import com.projectbase.common.enums.ResultCode;
import com.projectbase.common.exception.BusinessException;
import com.projectbase.entity.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * 全局异常处理类
 * @author tianren
 */
@Slf4j
@RestControllerAdvice(annotations = {Controller.class, RestController.class})
public class GlobalExceptionHandler{

    /**
     * 处理自定义异常
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public ResultVO<String> businessExceptionHandle(BusinessException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(e.getMsg());
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResultVO<String> bindExceptionHandler(BindException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED,message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是ConstraintViolationException
     * 包路径：javax.validation.ConstraintViolationException
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultVO<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED,message);
    }

    /**
     * 方法参数校验
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultVO<String> methodArgumentNotValidExceptionHandle(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return new ResultVO<>(ResultCode.VALIDATE_FAILED, message);
    }

    /**
     * 向数据库新增数据主键冲突失败
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateKeyException.class)
    public ResultVO<String> duplicateKeyExceptionHandle(DuplicateKeyException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(ResultCode.FAILED, "数据重复，请检查后提交");
    }

    /**
     * 404异常
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultVO<String> noFoundExceptionHandler(Exception e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(ResultCode.FAILED, "路径不存在，请检查路径是否正确");
    }

    /**
     * 处理所有异常
     * @param e
     * @return
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResultVO<String> exceptionHandle(Exception e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(ResultCode.FAILED, e.getMessage());
    }

}
