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

    public Employee getEmployeeById(@PathVariable Long id) {
        Optional<Employee> optionalEmployee = getOptionalEmployee(id);
        Employee employee = null;
        if (optionalEmployee.isEmpty()) {
            throw new RuntimeException("Employee not found by ID: " + id);
        }
        else {
            employee = optionalEmployee.get();
        }
        return employee;
    }

    private Optional<Employee> getOptionalEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(@RequestBody Employee employee) {
        validateFields(employee);
        return employeeRepository.save(employee);
    }

    private void validateFields(Employee employee) {
        if (employee.getFirstName() == null || employee.getSalary() == null)
            throw new RuntimeException("Fields are required...");
    }

    public Employee deleteEmployee(@PathVariable Long id) {
        employeeRepository.deleteById(id);
        return null;
    }
}
