package com.gimosa.gimosa_trade.controller;

import com.gimosa.gimosa_trade.model.Client;
import com.gimosa.gimosa_trade.model.Order;
import com.gimosa.gimosa_trade.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/clients")
@Slf4j
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        log.info("object {}",client);
        Client savedClient = clientService.createClient(client);
        return ResponseEntity.ok(savedClient);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{clientId}/orders")
    public ResponseEntity<Order> createOrder(@PathVariable Long clientId, @RequestBody Order order) {
        Order savedOrder = clientService.createOrder(clientId, order);
        return ResponseEntity.ok(savedOrder);
    }

    @GetMapping("/{clientId}/orders")
    public ResponseEntity<List<Order>> getOrdersByClientId(@PathVariable Long clientId) {
        List<Order> orders = clientService.getOrdersByClientId(clientId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Client savedClient = clientService.updateClient(id, updatedClient);
        return ResponseEntity.ok(savedClient);
    }

    @PutMapping("/{clientId}/orders/{orderId}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long clientId, @PathVariable Long orderId, @RequestBody Order updatedOrder) {
        clientService.getClientById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + clientId));

        Order savedOrder = clientService.updateOrder(orderId, updatedOrder);
        return ResponseEntity.ok(savedOrder);
    }

}
