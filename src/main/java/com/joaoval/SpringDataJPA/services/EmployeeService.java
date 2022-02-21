package com.joaoval.SpringDataJPA.services;

import com.joaoval.SpringDataJPA.entities.Employee;
import com.joaoval.SpringDataJPA.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return Optional.ofNullable(validateId(id));
    }

    public Employee saveEmployee(@RequestBody Employee employee) {
        validateFields(employee);
        Employee employee1 = new Employee(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getSalary());
        return employeeRepository.save(employee);
    }

    public Employee deleteEmployee(@PathVariable Long id) {
        validateId(id);
        employeeRepository.deleteById(id);
        return null;
    }

    public Employee validateId(@PathVariable Long id) {
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        Employee employee = null;
        if (optEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found by ID: " + id);
        }
        return employee = optEmployee.get();
    }

    private void validateFields(Employee employee) {
        if (employee.getFirstName() == null || employee.getSalary() == null)
            throw new RuntimeException("Fields are required...");
    }
}
