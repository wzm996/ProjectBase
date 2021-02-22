package com.projectbase.service;

import com.projectbase.entity.WebFluxUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface WebFluxTestService {

    /**
     * 返回用户列表
     *
     * @return
     */
    Flux<WebFluxUser> getUserList();

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    Mono<WebFluxUser> getById(final String id);

}
