package com.projectbase.controller;

import com.projectbase.entity.User;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2021-02-09 20:48:21
 */
@Slf4j
@RestController
@RequestMapping("test")
@Api(value = "用户相关Controller", tags = {"用户相关接口"})
public class TestController {
    /**
     * 服务对象
     */
    /*@Resource
    private UserService userService;*/

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    @ApiOperation(value = "根据uid查询用户信息", notes = "根据uid从数据库中查询用户信息")
    @ApiImplicitParams({
            /**
             * paramType：参数 ———— header (请求参数的获取@RequestHeader)，query (请求参数的获取@RequestParam)，path (请求参数的获取@RequestVariable)
             */
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", dataType = "Integer")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "缺少必要的请求参数"),
            @ApiResponse(code = 401, message = "请求路径没有相应权限"),
            @ApiResponse(code = 403, message = "请求路径被隐藏不能访问"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 405, message = "请求方法不支持")
    })
    //@ApiIgnore("忽略这个api,在页面不显示")
    public User selectOne(Long id) {
        return null;
    }

}
