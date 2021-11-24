package com.cetin.education.spring.redis.dao;

import com.cetin.education.spring.redis.model.Student;
import org.springframework.stereotype.Repository;


public interface IStudentDao extends IDao<Integer, Student>{
    //you can add extra mandatory behaviors for student
}
