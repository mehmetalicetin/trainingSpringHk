package com.cetin.education.nit.spring.redis.rediscache.service;

import com.cetin.education.nit.spring.redis.rediscache.exception.ResourceNotFoundException;
import com.cetin.education.nit.spring.redis.rediscache.model.Employee;
import com.cetin.education.nit.spring.redis.rediscache.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee) {
        Assert.notNull(employee, "This employee object cannot be null");
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    @CachePut(value = "employees", key = "#id")//empId->parametre ile aynı isimde olmali
    public Employee update(Integer id, Employee employee) {
        Assert.notNull(id,"Thid Employee Id object cannot be null");
        Optional<Employee> e = employeeRepository.findById(id);
        e.orElseThrow(()->new ResourceNotFoundException("Employee not exist"));
        e.get().setEmpName(employee.getEmpName());
        e.get().setEmpSale(employee.getEmpSale());
        employeeRepository.save(e.get());
        return e.get();
    }

    @Override
    @CacheEvict(value = "employees", allEntries = true)//empId->parametre ile aynı isimde olmali
    public void delete(Integer id) {
        Assert.notNull(id,"Thid Employee Id object cannot be null");
        employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee not exist"));
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Cacheable(value = "employees", key = "#empId")//empId->parametre ile aynı isimde olmali
    public Employee getOne(Integer empId) {
        Assert.notNull(empId,"Thid Employee Id object cannot be null");
        Optional<Employee> e = employeeRepository.findById(empId);
        e.orElseThrow(()->new ResourceNotFoundException("Employee not exist"));
        return employeeRepository.getById(empId);
    }
}
