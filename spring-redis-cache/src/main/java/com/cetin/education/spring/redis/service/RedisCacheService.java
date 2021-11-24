package com.cetin.education.spring.redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService {

    private int counter;

    @Cacheable(cacheNames = "mySpecialCache")
    public String longRunningMethod() throws InterruptedException {
        Thread.sleep(5000);
        return "Method has been run";
    }

    public void clearCache(){
        System.out.println("Cache Temizlendi.");
    }

}
