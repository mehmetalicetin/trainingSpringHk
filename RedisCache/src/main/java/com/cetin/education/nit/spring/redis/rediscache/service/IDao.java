package com.cetin.education.nit.spring.redis.rediscache.service;

import com.cetin.education.nit.spring.redis.rediscache.model.Employee;

import java.util.List;

public interface IDao<T, K> {

    T save(T t);
    T update(K k, T t);
    void delete(K k);

    List<T> getAll();
    T getOne(K k);
}
