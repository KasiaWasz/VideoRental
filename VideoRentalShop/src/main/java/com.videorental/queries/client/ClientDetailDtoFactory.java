package com.videorental.queries.client;

import com.videorental.dtos.client.ClientDetailDto;
import com.videorental.entities.client.Client;
import org.springframework.stereotype.Component;

@Component
class ClientDetailDtoFactory {

    ClientDetailDto create(Client client) {

        if (client == null) {

            return null;
        }
        return new ClientDetailDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getRegistrationDate(),
                client.getPhoneNumber(),
                client.getEmail()
        );
    }
}
