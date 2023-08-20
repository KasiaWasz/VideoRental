package com.videorental.queries.client;

import com.videorental.dtos.client.ClientSimpleDto;
import com.videorental.entities.client.Client;
import org.springframework.stereotype.Component;

@Component
class ClientSimpleDtoFactory {

    ClientSimpleDto create(Client client) {

        if (client == null) {

            return null;
        }
        return new ClientSimpleDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName()
        );
    }
}
