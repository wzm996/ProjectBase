package com.projectbase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

@SpringBootTest
class ProjectbaseApplicationTests {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void test1() {
        //Redis订阅
        redisTemplate.convertAndSend("topic1","好帅!");
    }

    @Test
    void test2() {
        //由于 enableTransactionSupport 属性的默认值是 false，导致了每一个 RedisConnection 都是重新获取的。
        // 所以，执行的 MULTI 和 EXEC 这两个命令不在同一个 Connection 中。
        // 开启事务支持，在同一个 Connection 中执行命令
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.multi();
        redisTemplate.opsForValue().set("name", "qinyi");
        redisTemplate.opsForValue().set("gender", "male");
        redisTemplate.opsForValue().set("age", "19");
        // [true, true, true]
        System.out.println(redisTemplate.exec());
    }

    @Test
    void test3() {
        //redis流水线操作
        //通过 SessionCallback，保证所有的操作都在同一个 Session 中完成
        //更常见的写法仍是采用 RedisTemplate 的默认配置，即不开启事务支持。但是，我们可以通过使用 SessionCallback，该接口保证其内部所有操作都是在同一个Session中。
        redisTemplate.executePipelined(new SessionCallback(){
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                for(int i=1;i<=100;i++){
                    redisTemplate.opsForValue().set("keys"+i,i);
                }
                return null;
            }
        });
    }

}
