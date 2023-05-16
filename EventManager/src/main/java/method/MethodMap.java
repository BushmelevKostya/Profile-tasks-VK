package method;

import java.util.HashMap;

public class MethodMap {
	private HashMap<String, Method> map = new HashMap<>();
	
	public MethodMap(){
		this.map.put("get", new Get());
		map.put("post", new Post());
	}
	
	public HashMap<String, Method> getMap() {
		return this.map;
	}
	
	public void setMap(HashMap<String, Method> map) {
		this.map = map;
	}
}
