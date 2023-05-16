package com.server.eventmanagerspring.event.services;

import com.server.eventmanagerspring.event.model.Event;
import com.server.eventmanagerspring.event.repositories.EventRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	
	public void saveAll(Event... events) {
		eventRepository.saveAll(Arrays.asList(events));
	}
	
	public List<Event> getAll() {
		return eventRepository.findAll();
	}
	
	public List<Event> filterByName(String name) {
		List<Event> allEvent = eventRepository.findAll();
		List<Event> filteredEvents = new ArrayList<>();
		for (Event event : allEvent) {
			if (event.getName().equals(name)) {
				filteredEvents.add(event);
			}
		}
		return filteredEvents;
	}
	
	public List<Event> filterByDate(LocalDate date) {
		List<Event> allEvent = eventRepository.findAll();
		List<Event> filteredEvents = new ArrayList<>();
		for (Event event : allEvent) {
			if (event.getTime().equals(date)) filteredEvents.add(event);
		}
		return filteredEvents;
	}
	
	public int countEventsByClientIP(int IP) {
		System.out.printf("Events with user_ip = %d total : ", IP);
		return eventRepository.countEventsByClientIP(IP);
	}
	
	public int countEventsByName(String name) {
		System.out.printf("Events with name = %s total : ", name);
		return eventRepository.countEventsByName(name);
	}
	
	public int countEventsByState(String state) {
		System.out.printf("Events with state = %s total : ", state);
		return eventRepository.countEventsByState(state);
	}
}
