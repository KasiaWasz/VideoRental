package com.videorental.queries.client;

import com.videorental.dtos.client.ClientDetailDto;
import com.videorental.dtos.client.ClientDto;
import com.videorental.dtos.client.ClientSimpleDto;
import com.videorental.entities.client.Client;
import com.videorental.queries.AbstractQueries;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
class ClientQueriesImpl extends AbstractQueries<Client> implements ClientQueries {

    private final ClientDetailDtoFactory clientDetailDtoFactory;
    private final ClientSimpleDtoFactory clientSimpleDtoFactory;


    @Autowired
    ClientQueriesImpl(SessionFactory sessionFactory,
        ClientDetailDtoFactory clientDetailDtoFactory,
        ClientSimpleDtoFactory clientSimpleDtoFactory) {

        super(sessionFactory, Client.class);

        Assert.notNull(clientDetailDtoFactory, "clientDetailDtoFactory must not be null");
        Assert.notNull(clientSimpleDtoFactory, "clientSimpleDtoFactory must not be null");

        this.clientDetailDtoFactory = clientDetailDtoFactory;
        this.clientSimpleDtoFactory = clientSimpleDtoFactory;
    }

    public List<ClientSimpleDto> getAllClientsSimpleDto() {

        return getAll().stream()
                .map(clientSimpleDtoFactory::create)
                .toList();
    }

    public List<ClientDetailDto> getAllClientsDetailsDto() {

        return getAll().stream()
                .map(clientDetailDtoFactory::create)
                .toList();
    }

    public List<ClientDto> getAllClientsDto() {

        return getAll().stream()
                .map(client -> new ClientDto(
                        client.getId(),
                        client.getFirstName(),
                        client.getLastName(),
                        client.getPhoneNumber(),
                        client.getEmail()
                ))
                .toList();
    }

    public ClientDetailDto getClientDetailDtoById(Long id) {

        Assert.notNull(id, "id must not be null");

        Client client = getById(id);

        return clientDetailDtoFactory.create(client);
    }
}
