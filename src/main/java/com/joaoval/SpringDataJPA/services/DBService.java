package com.joaoval.SpringDataJPA.services;

import com.joaoval.SpringDataJPA.entities.Client;
import com.joaoval.SpringDataJPA.entities.Employee;
import com.joaoval.SpringDataJPA.entities.enums.Profile;
import com.joaoval.SpringDataJPA.repositories.ClientRepository;
import com.joaoval.SpringDataJPA.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Service
public class DBService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Bean
    public void instantiateTestDatabase() throws ParseException {

        Client cli1 = new Client(1L, "Maria Silva", pe.encode("123"));
        Client cli2 = new Client(2L, "Ana Costa", pe.encode("123"));
        cli2.addProfile(Profile.ADMIN);

        clientRepository.saveAll(Arrays.asList(cli1,cli2));

        Employee emp1 = new Employee(null, "João Vitor", "Alves Lima", BigDecimal.valueOf(2500.00));
        Employee emp2 = new Employee(null, "Anakin", "Skywalker", BigDecimal.valueOf(250000.01));
        Employee emp3 = new Employee(null, "Laurien", "Costa", BigDecimal.valueOf(3500.00));
        Employee emp4 = new Employee(null, "Breno", "Morais", BigDecimal.valueOf(99999999.00));
        Employee emp5 = new Employee(null, "Scooby", "Lima", BigDecimal.valueOf(1500.00));

        employeeRepository.saveAll(Arrays.asList(emp1, emp2, emp3, emp4, emp5));

    }

}
