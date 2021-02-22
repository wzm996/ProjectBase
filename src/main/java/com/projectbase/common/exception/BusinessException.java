package com.projectbase.common.exception;

import com.projectbase.common.enums.ResultCode;
import lombok.Getter;

/**
 * 自定义业务异常
 * @author
 */
//只要getter方法，无需setter
@Getter
public class BusinessException extends RuntimeException {

        private final int code;
        private final String msg;

        public BusinessException(String msg) {
            this(ResultCode.FAILED.getCode(), msg);
        }

        public BusinessException(int code, String msg) {
            super(msg);
            this.code = code;
            this.msg = msg;
        }
}
