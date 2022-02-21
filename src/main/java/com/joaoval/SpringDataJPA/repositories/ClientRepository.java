package com.joaoval.SpringDataJPA.repositories;

import com.joaoval.SpringDataJPA.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Transactional(readOnly = true)
    Client findByUsername(String username);
}
