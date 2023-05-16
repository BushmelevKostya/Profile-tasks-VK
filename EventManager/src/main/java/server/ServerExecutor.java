package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerExecutor {
	public ServerExecutor() {
	}
	
	public void run() throws IOException, ClassNotFoundException {
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		channel.connect(new InetSocketAddress("localhost", 9876));
		
		Selector selector = Selector.open();
		channel.register(selector, SelectionKey.OP_CONNECT);
		
		System.out.println("Server started");
		while (true) {
			int readyChannels = selector.select();
			if (readyChannels == 0) continue;
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			
			while (keyIterator.hasNext()) {
				SelectionKey key = keyIterator.next();
				if (key.isReadable()) {
					DatagramChannel datagramChannel = (DatagramChannel) key.channel();
					Object[] objects = Recipient.getRequest(datagramChannel);
					Request request = (Request) objects[0];
					InetSocketAddress clientAddress = (InetSocketAddress) objects[1];
					
					Sender.sendResponse(request, datagramChannel, clientAddress);
					keyIterator.remove();
				}
			}
		}
	}
}
