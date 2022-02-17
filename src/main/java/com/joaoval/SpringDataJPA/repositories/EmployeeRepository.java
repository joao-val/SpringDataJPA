package com.joaoval.SpringDataJPA.repositories;

import com.joaoval.SpringDataJPA.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findEmployeeByLastNameContaining(String str);
}
