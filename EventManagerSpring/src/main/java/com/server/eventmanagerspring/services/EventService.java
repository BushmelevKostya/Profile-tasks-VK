package com.server.eventmanagerspring.services;

import com.server.eventmanagerspring.model.Event;
import com.server.eventmanagerspring.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class EventService {

	private final EventRepository eventRepository;
	
	public EventService(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}
	
	public void save(Event event) {
		eventRepository.save(event);
	}
	
	public void saveAll(Event... events){
		eventRepository.saveAll(Arrays.asList(events));
	}
	
	public List<Event> getAll() {
		return eventRepository.findAll();
	}
	
	public int countEventsByClientIP(int IP) {
		System.out.printf("Events with user_ip = %d total : ", IP);
		return eventRepository.countEventsByClientIP(IP);
	}
	public int countEventsByName(String name) {
		System.out.printf("Events with name = %s total : ", name);
		return eventRepository.countEventsByName(name);
	}
	public int countEventsByState(String state){
		System.out.printf("Events with state = %s total : ", state);
		return eventRepository.countEventsByState(state);
	}
}
