package common;

import java.io.Serializable;

public class Request implements Serializable {
	private String method;
	private String name;
	private String state;
	
	public Request(String method, String name, String state) {
		this.method = method;
		this.name = name;
		this.state = state;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
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
}
