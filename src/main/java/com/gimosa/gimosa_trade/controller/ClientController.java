package com.gimosa.gimosa_trade.controller;

import com.gimosa.gimosa_trade.model.Client;
import com.gimosa.gimosa_trade.request.ClientRequest;
import com.gimosa.gimosa_trade.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientRequest clientRequest) {
        Client client = new Client();
        client.setName(clientRequest.getName());
        client.setAddress(clientRequest.getAddress());
        client.setOib(clientRequest.getOib());
        client.setEmail(clientRequest.getEmail());
        client.setPhone(clientRequest.getPhone());
        client.setDate(clientRequest.getDate());
        client.setPallets(clientRequest.getPallets());
        client.setPackages(clientRequest.getPackages());
        Client savedClient = clientService.createClient(client);

        return ResponseEntity.ok(savedClient);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        Client existingClient = clientService.getClientById(id);
        if (existingClient == null) {
            return ResponseEntity.notFound().build();
        }

        existingClient.setName(clientRequest.getName());
        existingClient.setAddress(clientRequest.getAddress());
        existingClient.setOib(clientRequest.getOib());
        existingClient.setEmail(clientRequest.getEmail());
        existingClient.setPhone(clientRequest.getPhone());
        existingClient.setDate(clientRequest.getDate());
        existingClient.setPallets(clientRequest.getPallets());
        existingClient.setPackages(clientRequest.getPackages());
        Client updatedClient = clientService.updateClient(existingClient);

        return ResponseEntity.ok(updatedClient);
    }

}
