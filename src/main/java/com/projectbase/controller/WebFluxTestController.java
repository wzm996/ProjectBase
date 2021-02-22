package com.projectbase.controller;

import com.projectbase.service.WebFluxTestService;
import com.projectbase.common.annotation.NotResponseBody;
import com.projectbase.entity.WebFluxUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

/**
 * WebFlux的测试Controller
 * @author
 */
@Slf4j
@RestController
@RequestMapping("webFlux")
public class WebFluxTestController {

    private final WebFluxTestService webFluxTestService;

    public WebFluxTestController(final WebFluxTestService webFluxTestService){
        this.webFluxTestService=webFluxTestService;
    }

    /**
     * 查找所有用户，延时加载
     * MediaType.APPLICATION_NDJSON_VALUE   低版本暂时不可用，需将版本切换至springboot2.4.0
     * @return
     */
    @NotResponseBody
    @GetMapping(value = "user",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<WebFluxUser> getUserAll(){
        log.info("请求成功");
        return webFluxTestService.getUserList().delayElements(Duration.ofSeconds(2));
    }

}
