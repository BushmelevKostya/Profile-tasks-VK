package com.server.eventmanagerspring.event.builder;

import com.server.eventmanagerspring.event.model.Event;

import java.time.LocalDate;

public class EventBuilder implements Builder{
	private Event event;
	
	public EventBuilder() {
	}
	
	@Override
	public void reset() {
	 this.event = new Event();
	}
	
	@Override
	public void setIp(Integer ip) {
		this.event.setClientIP(ip);
	}
	
	@Override
	public void setName(String name) {
		this.event.setName(name);
	}
	
	@Override
	public void setState(String state) {
		this.event.setState(state);
	}
	
	@Override
	public void setTime() {
		this.event.setTime(LocalDate.now());
	}
	
	@Override
	public Event getResult() {
		return this.event;
	}
}
