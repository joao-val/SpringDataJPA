package com.joaoval.SpringDataJPA.controllers;

import com.joaoval.SpringDataJPA.entities.Employee;
import com.joaoval.SpringDataJPA.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id", value = "employee", unless = "#result.salary > 10000.00")
    public Optional<Employee> findEmployeeById(@PathVariable Long id) {
        System.out.println("called getEmployeeById() from DB");
        return employeeService.getEmployeeById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping()
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    @CacheEvict(key = "#id", value = "employee")
    public void deleteEmployee(@PathVariable Long id) {
        System.out.println("cache canceled from Id ");
        employeeService.deleteEmployee(id);
    }
}
