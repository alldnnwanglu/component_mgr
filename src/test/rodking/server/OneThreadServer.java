package rodking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author rodking
 * @des ���߳̿��Դ���һ��һ�����󣬵������Ҫ�������ͻ�������ʱ������
 * (����һ���Ŷӻ���)
 * (�ʴ�ģʽ,��ͨ��ͨ��) ����ͨ��
 */
public class OneThreadServer {
	private ServerSocket server;
	private Socket socket = null;
	
	public void serverStart()
	{
		BufferedReader is = null;
		PrintWriter os = null;
		try {
			// ����һ�������
			server = new ServerSocket(9000);
			System.out.println("[ S ]: game server start port 9000.");
			// ����һ���ͻ�������
			socket = server.accept();
			String line;
			// ��ȡ�ͻ��˵ģ���д��
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			os = new PrintWriter(socket.getOutputStream());
			line = is.readLine();
			
			System.out.println("[ S ]: one client in server");
			// ���̴߳����ȡ
			while (!line.equals("bye")) {

				System.out.println("Server:" + line);
				System.out.println("Client:" + line);
				
				// TODO ���������Э���ɷ� (��װ os)
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
