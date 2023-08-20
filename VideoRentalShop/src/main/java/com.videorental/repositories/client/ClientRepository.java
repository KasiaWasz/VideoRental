package com.videorental.repositories.client;

import com.videorental.entities.client.Client;

public interface ClientRepository {

    void saveOrUpdate(Client client);

    void deleteById(Long id);
}
