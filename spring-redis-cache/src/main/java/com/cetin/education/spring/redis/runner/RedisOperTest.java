package com.cetin.education.spring.redis.runner;

import com.cetin.education.spring.redis.dao.IStudentDao;
import com.cetin.education.spring.redis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RedisOperTest implements CommandLineRunner {

    @Autowired
    private IStudentDao dao;

    @Override
    public void run(String... args) throws Exception {
        dao.add(new Student(101,"SAM",500.25));
        dao.add(new Student(102,"SYED",800.25));
        dao.add(new Student(103,"RAM",600.25));

        dao.getAll().forEach((k,v)-> System.out.println(k+"---"+v));

        dao.remove(101);

        dao.modify(new Student(103, "RAM RAJ", 100.25));
        System.out.println("--After Remove/Modify--");
        dao.getAll().forEach((k,v)-> System.out.println(k+"---"+v));



    }
}
