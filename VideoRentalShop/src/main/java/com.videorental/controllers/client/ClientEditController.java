package com.videorental.controllers.client;

import com.videorental.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/client-edit")
@Controller
class ClientEditController {

    private static final String M_EDIT_FORM = "clientForm";
    private static final String V_CLIENT_EDIT = "client-edit-view";
    private static final String CLIENTS_LIST_URL = "/client-list";
    private static final String P_CLIENT_ID = "id";

    private final ClientService clientService;
    private final ClientFormFactory clientFormFactory;
    private final ClientValidator clientValidator;


    @Autowired
    private ClientEditController(ClientService clientService,
        ClientFormFactory clientFormFactory,
        ClientValidator clientValidator) {

        Assert.notNull(clientService, "clientService must not be null");
        Assert.notNull(clientFormFactory, "clientFormFactory must not be null");
        Assert.notNull(clientValidator, "clientValidator must not be null");

        this.clientService = clientService;
        this.clientFormFactory = clientFormFactory;
        this.clientValidator = clientValidator;
    }


    @InitBinder(M_EDIT_FORM)
    private void initBinder(WebDataBinder binder) {

        binder.addValidators(clientValidator);
    }

    @ModelAttribute(M_EDIT_FORM)
    private ClientForm getClientForm(@RequestParam(value = P_CLIENT_ID, required = false) Long id) {

        if (id == null) {

            return new ClientForm();
        }

        return clientFormFactory.create(id);
    }

    @GetMapping
    private String showClientForm() {

        return V_CLIENT_EDIT;
    }

    @PostMapping
    private String addOrUpdateClient(@ModelAttribute(M_EDIT_FORM) @Valid ClientForm clientForm,
                                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            return V_CLIENT_EDIT;
        }

        clientService.saveOrUpdateClient(clientForm);

        return "redirect:" + CLIENTS_LIST_URL;
    }

    @GetMapping("/delete")
    private String deleteClient(@RequestParam(P_CLIENT_ID) Long id) {

        clientService.deleteById(id);

        return "redirect:" + CLIENTS_LIST_URL;
    }
}
