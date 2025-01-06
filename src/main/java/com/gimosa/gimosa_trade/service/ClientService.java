package com.gimosa.gimosa_trade.service;

import com.gimosa.gimosa_trade.model.Client;
import com.gimosa.gimosa_trade.repository.ClientRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    public Client updateClient(Client updatedClient) {
        if (clientRepository.existsById(updatedClient.getId())) {
            return clientRepository.save(updatedClient);
        }
        return null;
    }
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}
