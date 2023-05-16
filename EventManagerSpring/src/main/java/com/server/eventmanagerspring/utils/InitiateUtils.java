package com.server.eventmanagerspring.utils;

import com.server.eventmanagerspring.event.builder.Director;
import com.server.eventmanagerspring.event.builder.EventBuilder;
import com.server.eventmanagerspring.event.model.Event;
import com.server.eventmanagerspring.event.services.EventService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

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
	}
}
