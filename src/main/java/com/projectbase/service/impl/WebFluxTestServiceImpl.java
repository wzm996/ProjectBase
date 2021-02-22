package com.projectbase.service.impl;

import com.projectbase.service.WebFluxTestService;
import com.projectbase.entity.WebFluxUser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebFluxTestServiceImpl implements WebFluxTestService {

    private static final Map<String, WebFluxUser> dataMap = new HashMap<>();

    static {
        dataMap.put("1", new WebFluxUser("小X老师", 1));
        dataMap.put("2", new WebFluxUser("小X老师", 2));
        dataMap.put("3", new WebFluxUser("小X老师", 3));
        dataMap.put("4", new WebFluxUser("小X老师", 4));
        dataMap.put("5", new WebFluxUser("小X老师", 5));
        dataMap.put("6", new WebFluxUser("小X老师", 6));
        dataMap.put("7", new WebFluxUser("小X老师", 7));
    }

    /**
     * 返回用户列表
     *
     * @return
     */
    @Override
    public Flux<WebFluxUser> getUserList() {
        Collection<WebFluxUser> list = WebFluxTestServiceImpl.dataMap.values();
        return Flux.fromIterable(list);
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @Override
    public Mono<WebFluxUser> getById(String id) {
        return Mono.justOrEmpty(WebFluxTestServiceImpl.dataMap.get(id));
    }

}
