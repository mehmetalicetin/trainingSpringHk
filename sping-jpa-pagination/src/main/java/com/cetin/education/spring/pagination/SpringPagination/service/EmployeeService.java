package com.cetin.education.spring.pagination.SpringPagination.service;

import com.cetin.education.spring.pagination.SpringPagination.model.Employee;
import com.cetin.education.spring.pagination.SpringPagination.page.EmployeePage;
import com.cetin.education.spring.pagination.SpringPagination.repo.EmployeeCriteriaRepository;
import com.cetin.education.spring.pagination.SpringPagination.repo.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeCriteriaRepository employeeCriteriaRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           EmployeeCriteriaRepository employeeCriteriaRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeCriteriaRepository = employeeCriteriaRepository;
    }

    public Page<Employee> getEmployees(EmployeePage page,
                                       EmployeeSearchCriteria criteria){
        return employeeCriteriaRepository.findAllWithFilters(page,criteria);
    }

    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
}
