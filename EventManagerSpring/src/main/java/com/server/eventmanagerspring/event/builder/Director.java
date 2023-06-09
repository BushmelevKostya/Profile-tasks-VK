package com.server.eventmanagerspring.event.builder;

import com.server.eventmanagerspring.event.model.Event;

public class Director {
	private Builder builder;
	
	public Director(Builder builder) {
		this.builder = builder;
	}
	
	public void changeBuilder(Builder builder) {
		this.builder = builder;
	}
	
	public Event make(Integer Ip, String name, String state) {
		builder.reset();
		builder.setIp(Ip);
		builder.setName(name);
		builder.setState(state);
		builder.setTime();
		return builder.getResult();
	}
}
