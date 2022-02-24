package com.joaoval.SpringDataJPA.controllers;

import com.joaoval.SpringDataJPA.entities.Client;
import com.joaoval.SpringDataJPA.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService ClientService;

    @GetMapping()
    public List<Client> getAllClients() {
        return ClientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Optional<Client> findClientById(@PathVariable Long id) {

        return ClientService.getClientById(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping()
    public Client saveClient(@RequestBody Client Client) {
        return ClientService.saveClient(Client);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        ClientService.deleteClient(id);
    }

}
