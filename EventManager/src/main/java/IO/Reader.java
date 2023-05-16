package IO;

public class Reader {
	private final String data;
	private String method = null;
	private String state = null;
	private String name= null;
	
	public Reader(String data) {
		this.data = data;
		String[] strings = data.split(" ");
		if (strings.length > 0) this.method = strings[0];
		if (strings.length > 1) this.name = strings[1];
		if (strings.length > 2) this.state = strings[2];
	}
	
	public String getData() {
		return data;
	}
	
	public String getMethod() {
		return method;
	}
	
	public String getState() {
		return state;
	}
	
	public String getName() {
		return name;
	}
}
