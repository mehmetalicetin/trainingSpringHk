package com.cetin.education.nit.spring.redis.rediscache.controller;

import com.cetin.education.nit.spring.redis.rediscache.model.Employee;
import com.cetin.education.nit.spring.redis.rediscache.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeRestContoller {
    private final IEmployeeService employeeService;

    @PostMapping("/save")
    public Employee save(@RequestBody Employee employee){
      return  employeeService.save(employee);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Employee> getOne(@PathVariable Integer id){
       return ResponseEntity.ok(employeeService.getOne(id));
    }

    @PutMapping("/modify/{id}")
    public Employee update(@PathVariable Integer id, @RequestBody Employee employee){
        return employeeService.update(id,employee);
    }

    @DeleteMapping("/remove/{id}")
    public String delete(@PathVariable Integer id){
       employeeService.delete(id);
       return "Employee Deleted =>"+id;
    }
}
