package common;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Recipient {
	private static final Serializator serializator = new Serializator();
	
	public Recipient(){}
	
	public static Response getResponse(Socket socket) throws IOException, ClassNotFoundException {
		ByteBuffer buffer = ByteBuffer.allocate(0);
		InputStream in = socket.getInputStream();
		socket.setSoTimeout(3000);
		int size = 1000;
		try{
			while (true) {
				byte[] receiveBytes = new byte[size];
				in.read(receiveBytes);
				ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() + size);
				buffer.flip();
				newBuffer.put(buffer);
				newBuffer.put(receiveBytes);
				buffer.flip();
				buffer = newBuffer;
			}
		} catch (SocketTimeoutException ignored){
			System.out.println(buffer.capacity());
		}
		clientSocket.setSoTimeout(0);
		byte[] receivesBytes = buffer.array();
		return serializator.deserializeToResponse(receivesBytes);
	}
	
	public static Object[] getRequest(DatagramChannel datagramChannel) throws IOException, ClassNotFoundException {
		ByteBuffer buffer = ByteBuffer.allocate(0);
		InetSocketAddress clientAddress = null;
		try {
			while (true) {
				ByteBuffer nowBuffer = ByteBuffer.allocate(1000);
				InetSocketAddress newClientAddress = (InetSocketAddress) datagramChannel.receive(nowBuffer);
				if (newClientAddress == null) throw new ExitException();
				else  clientAddress = newClientAddress;
				nowBuffer.flip();
				ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() + 1000);
				newBuffer.put(buffer);
				newBuffer.put(nowBuffer);
				newBuffer.flip();
				buffer = newBuffer;
			}
		} catch (ExitException ignored) {
		}
		
		byte[] byteRequest = buffer.array();
		Request request = serializator.deserializeToRequest(byteRequest);
		Object[] objects = new Object[2];
		objects[0] = request;
		objects[1] = clientAddress;
		return objects;
	}
}
