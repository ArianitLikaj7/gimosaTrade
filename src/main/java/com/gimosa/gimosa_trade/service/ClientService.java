package com.gimosa.gimosa_trade.service;

import com.gimosa.gimosa_trade.model.Client;
import com.gimosa.gimosa_trade.model.Order;
import com.gimosa.gimosa_trade.repository.ClientRepository;
import com.gimosa.gimosa_trade.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;


    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Order createOrder(Long clientId, Order order) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + clientId));
        order.setClient(client);
        return orderRepository.save(order);
    }

    public List<Order> getOrdersByClientId(Long clientId) {
        return orderRepository.findByClientId(clientId);
    }

    public Client updateClient(Long clientId, Client updatedClient) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + clientId));

        existingClient.setName(updatedClient.getName());
        existingClient.setClientName(updatedClient.getClientName());
        existingClient.setAddress(updatedClient.getAddress());
        existingClient.setOib(updatedClient.getOib());
        existingClient.setEmail(updatedClient.getEmail());
        existingClient.setPhone(updatedClient.getPhone());
        return clientRepository.save(existingClient);
    }

    public Order updateOrder(Long orderId, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with ID: " + orderId));

        existingOrder.setPallets(updatedOrder.getPallets());
        existingOrder.setPackages(updatedOrder.getPackages());
        existingOrder.setOrderDate(updatedOrder.getOrderDate());
        return orderRepository.save(existingOrder);
    }

    @Transactional
    public void deleteClientById(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found with ID: " + clientId));
        clientRepository.delete(client);
    }
}
