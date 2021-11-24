package com.cetin.education.spring.pagination.SpringPagination.api;

import com.cetin.education.spring.pagination.SpringPagination.model.Employee;
import com.cetin.education.spring.pagination.SpringPagination.page.EmployeePage;
import com.cetin.education.spring.pagination.SpringPagination.service.EmployeeSearchCriteria;
import com.cetin.education.spring.pagination.SpringPagination.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployess(EmployeePage page,
                                                       EmployeeSearchCriteria criteria){
        return new ResponseEntity<>(employeeService.getEmployees(page, criteria), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }
}
