package com.videorental.controllers.client;

import com.videorental.entities.client.Client;
import com.videorental.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.format.DateTimeFormatter;

@Component
class ClientFormFactory {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final ClientService clientService;


    @Autowired
    ClientFormFactory(ClientService clientService) {

        Assert.notNull(clientService, "clientService must not be null");

        this.clientService = clientService;
    }

    ClientForm create(Long id) {

        Assert.notNull(id, "id must not be null");

        Client client = clientService.getById(id);

        return new ClientForm(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getRegistrationDate().format(DATE_FORMAT),
                client.getPhoneNumber(),
                client.getEmail()
        );
    }
}
