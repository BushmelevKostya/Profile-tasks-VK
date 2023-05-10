package com.server.eventmanagerspring.services;

import com.server.eventmanagerspring.model.Client;
import com.server.eventmanagerspring.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceBD {
	private final ClientRepository clientRepository;
	
	public ClientServiceBD(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	public void save(Client client) {
		clientRepository.save(client);
	}
	
	public List<Client> getAll() {
		return clientRepository.findAll();
	}
}
