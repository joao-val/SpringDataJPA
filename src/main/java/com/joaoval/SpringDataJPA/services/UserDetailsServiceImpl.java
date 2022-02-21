package com.joaoval.SpringDataJPA.services;

import com.joaoval.SpringDataJPA.entities.Client;
import com.joaoval.SpringDataJPA.repositories.ClientRepository;
import com.joaoval.SpringDataJPA.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Client client = repo.findByUsername(username);

        if (client == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserSS(client.getId(), client.getUsername(), client.getPassword(), client.getProfiles());
    }
}
