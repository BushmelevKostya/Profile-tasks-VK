package com.server.eventmanagerspring.event.controller;

import com.server.eventmanagerspring.messages.AppError;
import com.server.eventmanagerspring.event.model.Event;
import com.server.eventmanagerspring.event.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class EventController {
	private final EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@PostMapping(value = "/events")
	public ResponseEntity<?> save(@RequestBody Event event) {
		eventService.save(event);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/events")
	public ResponseEntity<?> read() {
		try {
			final List<Event> events = eventService.getAll();
			if (events == null || events.isEmpty()) throw new RuntimeException();
			return new ResponseEntity<>(events, HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Events not found, please try" +
					" to add them"), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "/events/count_by_ip/{ip}")
	public ResponseEntity<?> countEventsByClientIP(@PathVariable int ip) {
		int result = eventService.countEventsByClientIP(ip);
		String response = "found events with specified ip : " + result;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/events/count_by_name/{name}")
	public ResponseEntity<?> countEventsByName(@PathVariable String name) {
		int result = eventService.countEventsByName(name);
		String response = "found events with specified name : " + result;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "/events/count_by_state/{state}")
	public ResponseEntity<?> countEventsByState(@PathVariable String state) {
		int result = eventService.countEventsByState(state);
		String response = "found events with specified state : " + result;
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping(value = "events/filtered_by_name/{name}")
	public ResponseEntity<?> filterByName(@PathVariable String name) {
		try {
			List<Event> filteredEvents = eventService.filterByName(name);
			if (filteredEvents == null || filteredEvents.isEmpty()) throw new RuntimeException();
			return new ResponseEntity<>(filteredEvents, HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Events with name = " +
					name + " not found, please check existing events on the page /events"), HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value = "events/filtered_by_date/{date}")
	public ResponseEntity<?> filterByDate(@PathVariable LocalDate date) {
		try {
			List<Event> filteredEvents = eventService.filterByDate(date);
			if (filteredEvents == null || filteredEvents.isEmpty()) throw new RuntimeException();
			return new ResponseEntity<>(filteredEvents, HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Events with date = " +
					date + " not found, please check existing events on the page /events"), HttpStatus.NOT_FOUND);
		}
	}
}
