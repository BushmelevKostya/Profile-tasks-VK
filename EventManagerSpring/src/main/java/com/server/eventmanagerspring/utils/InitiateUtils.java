package com.server.eventmanagerspring.utils;

import com.server.eventmanagerspring.builder.Director;
import com.server.eventmanagerspring.builder.EventBuilder;
import com.server.eventmanagerspring.model.Event;
import com.server.eventmanagerspring.services.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.util.List;

@Service
public class InitiateUtils implements CommandLineRunner {
	private final EventService eventService;
	Director director = new Director(new EventBuilder());
	
	public InitiateUtils(EventService eventService) {
		this.eventService = eventService;
	}
	
	@Override
	public void run(String... args) {
		Event event1 = director.make(1921685454, "open", "not authorized");
		Event event2 = director.make(1921685454, "register", "not authorized");
		Event event3 = director.make(1921685454, "buy", "authorized");
		Event event4 = director.make(1921685455, "open", "not authorized");

		eventService.saveAll(event1, event2, event3, event4);
		
		var events = eventService.getAll();
		
		for (Event event : events) {
			System.out.println(event);
		}
		
		System.out.println(eventService.countEventsByClientIP(1921685454));
		System.out.println(eventService.countEventsByState("authorized"));
		System.out.println(eventService.countEventsByName("open"));
	}
}
