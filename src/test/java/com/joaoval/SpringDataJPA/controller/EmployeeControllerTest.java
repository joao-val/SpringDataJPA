package com.joaoval.SpringDataJPA.controller;

import com.joaoval.SpringDataJPA.entities.Employee;
import com.joaoval.SpringDataJPA.repositories.EmployeeRepository;
import com.joaoval.SpringDataJPA.services.EmployeeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class EmployeeControllerTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findByIdTest() {
        Employee employee = employeeService.getEmployeeById(2L);

        assertEquals("Anakin", employee.getFirstName());
        assertEquals(BigDecimal.valueOf(250000.01), employee.getSalary());
        assertEquals("Skywalker", employee.getLastName());
    }

    @Test
    public void deleteByIdTeste() {
        Employee employee = employeeService.deleteEmployee(3L);

        Optional<Employee> optionalEmployee = employeeRepository.findById(3L);

        assertFalse(optionalEmployee.isPresent());
    }

}
