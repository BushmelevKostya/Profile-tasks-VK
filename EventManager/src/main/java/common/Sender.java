package common;

import method.Method;
import method.MethodMap;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;
import java.util.HashMap;

public class Sender {
	private static final Serializator serializator = new Serializator();
	public Sender() {}
	
	public static void sendRequest(Socket socket, String method, String name, String state) throws IOException {
		Request request = new Request(method, name, state);

		OutputStream out = socket.getOutputStream();
		byte[] byteRequest = serializator.serializeObject(request);
		int size = 1000;
		for (int i = 0; i * size < byteRequest.length; i++) {
			byte[] newByteRequest = Arrays.copyOfRange(byteRequest, i * size, Math.min((i+1) * size, byteRequest.length));
			out.write(newByteRequest);
		}
	}
	
	public static void sendResponse(Request request, DatagramChannel datagramChannel, InetSocketAddress clientAddress) throws IOException, ExitException{
		Response response = new Response();
		try {
			if (request.getRequestType() != null && request.getRequestType().equals("GET")) {
				var id = request.getValue();
				try {
					Product product = new ServerValidator().getById(Integer.parseInt(id));
					response.setProduct(product);
				} catch (NumberFormatException exception) {
					throw new NumberFormatException("Этой команде необходимо передать параметр типа int!");
				}
			} else {
				String answer = new CommandExecutor(request.getCommand().replace("insertScript", "insert"), request.getValue(), request.getProduct()).run();
				response.setAnswer(answer);
			}
		} catch (NumberFormatException | FalseValuesException | UniqueException |
		         NoElementException exception) {
			String message = response.getAnswer() + "\n" + exception.getMessage();
			response.setAnswer(message);
		}
		ByteBuffer buffer;
		int size = 10000;
		byte[] byteResponse = serializator.serializeObject(response);
//		System.out.println(byteResponse.length);
		for (int i = 0; i * size < byteResponse.length; i++) {
			buffer = ByteBuffer.allocate(size);
			byte[] newByteResponse = Arrays.copyOfRange(byteResponse, i * size, Math.min((i + 1) * size, byteResponse.length));
			buffer.put(newByteResponse);
			buffer.flip();
			datagramChannel.send(buffer, clientAddress);
//			System.out.println(i);
		}
	}
}
