package com.projectbase.entity.vo;

import com.fasterxml.jackson.annotation.*;

import java.util.Date;

@JsonPropertyOrder(value = {"content","title"})
public class TestVO {
    //该字段在返回时忽略
    @JsonIgnore
    private Long id;
    @JsonProperty("auther")
    private String author;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String reader;

}
