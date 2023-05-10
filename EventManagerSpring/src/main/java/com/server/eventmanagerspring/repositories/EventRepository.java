package com.server.eventmanagerspring.repositories;

import com.server.eventmanagerspring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
	int countEventsByClientIP(int IP);
	int countEventsByName(String name);
	int countEventsByState(String state);
}
