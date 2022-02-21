package com.joaoval.SpringDataJPA.controller;

import com.joaoval.SpringDataJPA.entities.Employee;
import com.joaoval.SpringDataJPA.repositories.EmployeeRepository;
import com.joaoval.SpringDataJPA.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class EmployeeControllerMockito {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    public void saveVerification() {
        Employee employee = new Employee();
        employee.setFirstName("Teste");
        employee.setLastName("Testando");
        employee.setSalary(BigDecimal.valueOf(1400.50));
        employeeService.saveEmployee(employee);
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test(expected = Exception.class)
    public void testRequiredFields() {
        Employee employee = new Employee();
        employeeService.saveEmployee(employee);
    }

}
