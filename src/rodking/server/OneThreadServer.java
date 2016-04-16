package rodking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author rodking
 * @des 单线程可以处理一对一的请求，当如果有要处理多个客户端连接时不适用
 * (类似一种排队机制)
 * (问答模式,单通道通信) 阻塞通信
 */
public class OneThreadServer {
	private ServerSocket server;
	private Socket socket = null;
	
	public void serverStart()
	{
		BufferedReader is = null;
		PrintWriter os = null;
		try {
			// 启动一个服务端
			server = new ServerSocket(9000);
			System.out.println("[ S ]: game server start port 9000.");
			// 监听一个客户端连接
			socket = server.accept();
			String line;
			// 获取客户端的，读写流
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream());
			line = is.readLine();
			
			System.out.println("[ S ]: one client in server");
			// 单线程处理读取
			while (!line.equals("bye")) {

				System.out.println("Server:" + line);
				System.out.println("Client:" + line);
				
				// TODO 这里可以做协议派发 (封装 os)
				os.println("[ received ] "+ line);
				os.flush();
				
				line = is.readLine();
			}
			
			System.out.println("[ S ]: game server close.");
			
			os.close();
			is.close();
			socket.close();
			server.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		OneThreadServer server = new OneThreadServer();
		server.serverStart();
	}
}
