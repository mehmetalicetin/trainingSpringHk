package com.cetin.education.nit.spring.redis.rediscache.repo;

import com.cetin.education.nit.spring.redis.rediscache.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
