package com.joaoval.SpringDataJPA.services;

import com.joaoval.SpringDataJPA.entities.Client;
import com.joaoval.SpringDataJPA.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(@PathVariable Long id) {
        return Optional.ofNullable(validateId(id));
    }

    public Client saveClient(@RequestBody Client client) {
        validateFields(client);
        Client cli = new Client(client.getId(), client.getUsername(), pe.encode(client.getPassword()));
        return clientRepository.save(cli);
    }

    public void deleteClient(@PathVariable Long id) {
        validateId(id);
        clientRepository.deleteById(id);
    }

    public Client validateId(@PathVariable Long id) {
        Optional<Client> optClient = clientRepository.findById(id);
        Client client = null;
        if (optClient.isEmpty()) {
            throw new RuntimeException("Client not found by ID: " + id);
        }
        return client = optClient.get();
    }

    private void validateFields(Client Client) {
        if (Client.getUsername()== null || Client.getPassword() == null)
            throw new RuntimeException("Fields are required...");
    }
}
