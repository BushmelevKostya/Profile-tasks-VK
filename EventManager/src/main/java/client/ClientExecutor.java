package client;

import IO.Reader;
import common.Recipient;
import common.Response;
import common.Sender;
import method.Method;
import method.MethodMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;

public class ClientExecutor {
	private final HashMap<String, Method> map = new MethodMap().getMap();
	
	public ClientExecutor() {
	}
	
	/**
	 * this method use when program has been started
	 */
	public void run() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		     Socket socket = new Socket("localhost", 8888)) {

			System.out.println("Enter the command in the format: \"<method> <event name> <user state>\"");
			while (true) {
				String string = br.readLine().strip();
				execute(socket, string);
				System.out.println("\nВведите команду в формате: \"<команда> <ключ>\"");
			}
		}catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
	}
	
	public void execute(Socket socket, String string) throws IOException {
		Reader reader = new Reader(string);
		String method = reader.getMethod();
		String name = reader.getName();
		String state = reader.getState();
		map.get(method).execute(name, state);

		try {
			Sender.sendRequest(socket, method, name, state);
			Response response = Recipient.getResponse(clientSocket);
			System.out.println("From Server : " + response.getAnswer());
			} catch (EOFException exception){
			System.out.println("Сервер недоступен, проверьте соединение!");
		}
	}
}
