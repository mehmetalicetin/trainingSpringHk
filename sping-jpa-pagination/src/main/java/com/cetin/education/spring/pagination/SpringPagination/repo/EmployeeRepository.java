package com.cetin.education.spring.pagination.SpringPagination.repo;

import com.cetin.education.spring.pagination.SpringPagination.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
