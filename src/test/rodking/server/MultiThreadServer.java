package rodking.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author rodking
 * @des ���õȴ����Կ���ͬʱ�������ͻ�������
 * (�����������������̻߳��Ϊϵͳƿ��)
 * (�ʴ�ģʽ,��ͨ��ͨ��) ����ͨ��
 */
public class MultiThreadServer {

	private ServerSocket server;
	private Socket socket = null;

	public void serverStart() {
		try {
			server = new ServerSocket(9000);
			// ѭ������socket����
			while (true) {
				// ����һ�������
				System.out.println("[ S ]: game server start port 9000.");
				// ����һ���ͻ�������
				socket = server.accept();

				// ���̴߳���ͻ�������
				MultiThread clientServer = new MultiThread(socket);
				new Thread(clientServer).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	class MultiThread implements Runnable {
		private BufferedReader is = null;
		private PrintWriter os = null;
		private String line;
		private Socket socket;

		public MultiThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			System.out.println("[ S ]: one client in server");
			try {
				this.is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				this.os = new PrintWriter(socket.getOutputStream());
				line = is.readLine();
				// TODO Auto-generated method stub
				while (!line.equals("bye")) {

					System.out.println("Server:" + line);
					System.out.println("Client:" + line);

					// TODO ���������Э���ɷ� (��װ os)
					os.println("[ received ] " + line);
					os.flush();

					line = is.readLine();
				}

				System.out.println("[ S ]: game server close.");

				os.close();
				is.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new MultiThreadServer().serverStart();
	}
}
