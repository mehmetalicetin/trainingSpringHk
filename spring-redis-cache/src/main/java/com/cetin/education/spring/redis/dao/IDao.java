package com.cetin.education.spring.redis.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

public interface IDao<K, T> {
    void add(T t);

    void modify(T t);

    T getOne(K k);

    Map<K, T> getAll();

    void remove(K k);
}
