package rodking.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author rodking
 * @des 非阻塞的通信
 */
public class NIOServer {
	private Selector selector;

	public void initServer(int port) throws IOException {
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		// 设置为非阻塞模式
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(port));
		this.selector = Selector.open();

		serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
	}

	public void lisiten() throws IOException {
		System.out.println("Server Start...");
		while (true) {
			selector.select();
			Iterator<?> it = selector.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();
				it.remove();
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();
					channel.configureBlocking(false);
					channel.write(ByteBuffer.wrap(new String("you giv me a message").getBytes()));

					channel.register(selector, SelectionKey.OP_READ);

				} else if (key.isReadable()) {
					read(key);
				}

			}
		}
	}

	private void read(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buf = ByteBuffer.allocate(10);
		channel.read(buf);
		byte[] data = buf.array();
		String str = new String(data).trim();
		System.out.println(" received message " + str);
		ByteBuffer outBuf = ByteBuffer.wrap(str.getBytes());
		channel.write(outBuf);
	}

	public static void main(String[] args) throws IOException {
		NIOServer nioServer = new NIOServer();
		nioServer.initServer(9000);
		nioServer.lisiten();
	}
}
