package com.videorental.controllers.client;

import com.videorental.dtos.client.ClientDetailDto;
import com.videorental.dtos.client.ClientDto;
import com.videorental.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/client-list")
@Controller
public class ClientListController {

    private static final String M_CLIENT_LIST = "clients";
    private static final String M_CLIENT_DETAILS_LIST = "clientsDetails";
    private static final String V_CLIENTS_LIST_VIEW = "client-list-view";
    private static final String V_CLIENTS_LIST_DETAIL_VIEW = "client-list-detail-view";
    private static final String P_CLIENT_ID = "id";
    private final ClientService clientService;


    @Autowired
    public ClientListController(ClientService clientService) {

        Assert.notNull(clientService, "clientService must not be null");

        this.clientService = clientService;
    }

    @ModelAttribute(M_CLIENT_LIST)
    private List<ClientDto> getClients() {

        return clientService.getAllClientsDto();
    }

    @ModelAttribute(M_CLIENT_DETAILS_LIST)
    private List<ClientDetailDto> getAllClientsDetailsDto() {

        return clientService.getAllClientsDetailDto();
    }

    @GetMapping
    private String showClientList() {

        return V_CLIENTS_LIST_VIEW;
    }

    @GetMapping("/details")
    private String showClientDetail(@RequestParam(value = P_CLIENT_ID) Long id, Model model) {

        model.addAttribute("showDetails", true);
        model.addAttribute("clientDetail", clientService.getClientDetailDtoById(id));

        return V_CLIENTS_LIST_VIEW;
    }

    @GetMapping("/clients-details")
    private String showDetailedClientList() {

        return V_CLIENTS_LIST_DETAIL_VIEW;
    }
}
