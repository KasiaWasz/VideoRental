package com.videorental.services.client;

import com.videorental.dtos.client.ClientDetailDto;
import com.videorental.dtos.client.ClientDto;
import com.videorental.dtos.client.ClientSimpleDto;
import com.videorental.entities.client.Client;
import com.videorental.queries.client.ClientQueries;
import com.videorental.repositories.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ClientService {

    private final ClientQueries clientQueries;
    private final ClientRepository clientRepository;


    @Autowired
    private ClientService(ClientQueries clientQueries, ClientRepository clientRepository) {

        Assert.notNull(clientQueries, "clientQueries must not be null");
        Assert.notNull(clientRepository, "clientRepository must not be null");

        this.clientQueries = clientQueries;
        this.clientRepository = clientRepository;
    }

    public List<ClientDetailDto> getAllClientsDetailDto() {

        return clientQueries.getAllClientsDetailsDto();
    }

    public List<ClientDto> getAllClientsDto() {

        return clientQueries.getAllClientsDto();
    }

    public List<ClientSimpleDto> getAllClientSimpleDto() {

        return clientQueries.getAllClientsSimpleDto();
    }

    public ClientDetailDto getClientDetailDtoById(Long id) {

        Assert.notNull(id, "id must not be null");

        return clientQueries.getClientDetailDtoById(id);
    }

    public Client getById(Long id) {

        Assert.notNull(id, "id must not be null");

        return clientQueries.getById(id);
    }
}
