package com.cetin.education.spring.redis.daoimpl;

import com.cetin.education.spring.redis.dao.IStudentDao;
import com.cetin.education.spring.redis.model.Student;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

@Repository
public class StudentDaoImpl implements IStudentDao {

    private final String KEY = "STUDENT"; // table gibi dusunebilirsin

    //RefType,KeyType,ValueType
    @Resource(name = "rt")
    private HashOperations<String, Integer, Student> operations;

    @Override
    public void add(Student student) {
        operations.putIfAbsent(KEY, student.getStdId(), student);
    }

    @Override
    public void modify(Student student) {
        operations.put(KEY, student.getStdId(), student);
    }

    @Override
    public Student getOne(Integer id) {
        return operations.get(KEY, id);
    }

    @Override
    public Map<Integer, Student> getAll() {
        return  operations.entries(KEY);
    }

    @Override
    public void remove(Integer id) {
        operations.delete(KEY, id);
    }
}
