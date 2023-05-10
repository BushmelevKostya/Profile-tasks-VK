package com.server.eventmanagerspring.services;

import com.server.eventmanagerspring.model.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClientService{
	void create(Client client);
	
	List<Client> readAll();
	Client read(int id);
	boolean update(Client client, int id);
	boolean delete(int id);
}
