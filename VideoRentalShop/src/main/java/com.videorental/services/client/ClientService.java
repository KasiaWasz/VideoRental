package com.videorental.services.client;

import com.videorental.controllers.client.ClientForm;
import com.videorental.dtos.client.ClientDetailDto;
import com.videorental.dtos.client.ClientDto;
import com.videorental.dtos.client.ClientSimpleDto;
import com.videorental.entities.client.Client;
import com.videorental.queries.client.ClientQueries;
import com.videorental.repositories.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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


    public List<Client> getAll() {

        return clientQueries.getAll();
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

    public void saveOrUpdateClient(ClientForm clientForm) {

        Assert.notNull(clientForm, "clientForm must not be null");

        Optional<Client> existingClient = clientQueries.findById(clientForm.getId());

        if (existingClient.isPresent()) {
            Client client = existingClient.get();
            updateClientFields(client, clientForm);
            clientRepository.saveOrUpdate(client);
        } else {
            Client newClient = createNewClient(clientForm);
            clientRepository.saveOrUpdate(newClient);
        }
    }

    private void updateClientFields(Client client, ClientForm clientForm) {

        client.setFirstName(clientForm.getFirstName());
        client.setLastName(clientForm.getLastName());
        client.setPhoneNumber(clientForm.getPhoneNumber());
        client.setRegistrationDate(LocalDate.parse(clientForm.getRegistrationDate()));
        client.setEmail(clientForm.getEmail());
    }

    private Client createNewClient(ClientForm clientForm) {

        return new Client(
                clientForm.getFirstName(),
                clientForm.getLastName(),
                LocalDate.parse(clientForm.getRegistrationDate()),
                clientForm.getPhoneNumber(),
                clientForm.getEmail()
        );
    }

    public void deleteById(Long id) {

        Assert.notNull(id, "id must not be null");

        clientRepository.deleteById(id);
    }
}
