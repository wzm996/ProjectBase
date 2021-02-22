package com.projectbase.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projectbase.common.enums.ResultCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author
 */
@Getter
@ApiModel
public class ResultVO<T> implements Serializable {

    @ApiModelProperty(value = "状态码",notes = "默认0是成功")
    private int code;
    @ApiModelProperty(value = "响应信息",notes = "来说明响应情况")
    private String msg;
    @ApiModelProperty(value = "响应数据",notes = "响应的具体数据")
    @JsonInclude(JsonInclude.Include.NON_NULL) //如果为空，则不返回
    private T data;

    public ResultVO(T data){
        this.code = ResultCode.SUCCESS.getCode();
        this.msg = ResultCode.SUCCESS.getMsg();
        this.data = data;
    }

    public ResultVO(ResultCode resultCode, String msg){
        this(resultCode.getCode(),msg);
    }

    public ResultVO(int code, String msg){
        this(code,msg, null);
    }

    public ResultVO(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
