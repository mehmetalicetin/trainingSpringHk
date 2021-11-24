package com.cetin.education.spring.redis.api;

import com.cetin.education.spring.redis.service.RedisCacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisCacheController {

    private final RedisCacheService redisCacheService;
    private int counter = 0;

    public RedisCacheController(RedisCacheService redisCacheService) {
        this.redisCacheService = redisCacheService;
    }

    @GetMapping
    private String cacheController() throws InterruptedException {
        if(counter == 5){
            redisCacheService.clearCache();
            counter = 0 ;
        }
        counter++;
        return redisCacheService.longRunningMethod();
    }
}
