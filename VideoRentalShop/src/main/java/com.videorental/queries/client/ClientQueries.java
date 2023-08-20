package com.videorental.queries.client;

import com.videorental.dtos.client.ClientDetailDto;
import com.videorental.dtos.client.ClientDto;
import com.videorental.dtos.client.ClientSimpleDto;
import com.videorental.entities.client.Client;

import java.util.List;
import java.util.Optional;

public interface ClientQueries {

    Client getById(Long id);

    List<Client> getAll();

    Optional<Client> findById(Long id);

    List<ClientSimpleDto> getAllClientsSimpleDto();

    List<ClientDetailDto> getAllClientsDetailsDto();

    List<ClientDto> getAllClientsDto();

    ClientDetailDto getClientDetailDtoById(Long id);
}