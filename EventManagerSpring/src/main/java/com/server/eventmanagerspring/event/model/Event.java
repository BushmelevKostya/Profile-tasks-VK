package com.server.eventmanagerspring.event.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "event")
public class Event {
	@Id
	@Column(name = "id")
	@GenericGenerator(name = "generator", strategy = "increment")
	private Integer id;
	@Column(name = "client_IP")
	private Integer clientIP;
	@Column(name = "name")
	private String name;
	@Column(name = "state")
	private String state;
	@Column(name = "time")
	private LocalDate time = LocalDate.now();
	
	public Event() {
	}
	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getClientIP() {
		return clientIP;
	}
	
	public void setClientIP(Integer clientIP) {
		this.clientIP = clientIP;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public LocalDate getTime() {
		return time;
	}
	
	public void setTime(LocalDate time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "Event{" +
				"id=" + id +
				", clientIP=" + clientIP +
				", name='" + name + '\'' +
				", state='" + state + '\'' +
				", time=" + time +
				'}';
	}
}
