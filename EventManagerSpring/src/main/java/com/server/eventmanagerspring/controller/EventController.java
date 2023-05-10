package com.server.eventmanagerspring.controller;

import com.server.eventmanagerspring.model.Event;
import com.server.eventmanagerspring.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventController {
	private final EventService eventService;
	
	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}
	
	@PostMapping(value = "/events")
	public ResponseEntity<?> save(@RequestBody Event event){
		eventService.save(event);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/events/{ip}")
	public ResponseEntity<?> countEventsByClientIP(@PathVariable int ip){
		int result = eventService.countEventsByClientIP(ip);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/events/{name}")
	public ResponseEntity<?> countEventsByName(@PathVariable String name){
		int result = eventService.countEventsByName(name);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/events/{state}")
	public ResponseEntity<?> countEventsByState(@PathVariable String state){
		int result = eventService.countEventsByState(state);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
