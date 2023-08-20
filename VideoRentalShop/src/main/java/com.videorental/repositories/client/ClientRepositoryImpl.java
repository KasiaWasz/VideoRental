package com.videorental.repositories.client;


import com.videorental.entities.client.Client;
import com.videorental.repositories.AbstractRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
class ClientRepositoryImpl extends AbstractRepository<Client> implements ClientRepository {


    @Autowired
    ClientRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Client.class);
    }
}
