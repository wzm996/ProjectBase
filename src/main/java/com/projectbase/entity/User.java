package com.projectbase.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-02-09 20:48:14
 */
//@TableName("tb_user")   //数据库中的表名
public class User implements Serializable {
    private static final long serialVersionUID = 427787387367236121L;
    /**
     * 用户id
     */
    //@TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户姓名
     */
    //@TableField(value = "name")  //指定数据表中的字段名
    private String userName;
    /**
     * 用户密码
     */
    //@TableField(select = false)   //查询时不返回该字段的值
    private String userPassword;

    //@TableField(exist = false)   //数据库不存在该字段
    private String test;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
