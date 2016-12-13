package omokServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

//�޽��� �а� ���� �ϴ� ������
class ServerThreadUser extends Thread{	
	Socket cliSock;	
	ArrayList<ServerClientInfo> clientInfo;
	
	ServerThreadUser(Socket cliSock, ArrayList<ServerClientInfo> clientInfo){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
	}
	
	@Override
	public void run(){
		try {
			InetAddress cliAddr = cliSock.getInetAddress();
			System.out.println(cliAddr.getHostAddress() + "����");

			//� Ŭ���̾�Ʈ ���Դ��� Ȯ���ؾ���.
			for(ServerClientInfo ci:clientInfo){
				System.out.println(ci);
				
			}				
			
			//Ŭ���̾�Ʈ���� ���� �� �ޱ�
			while(true){
				InputStream receiver = cliSock.getInputStream();
				byte []bt = new byte[256];
				int size = receiver.read(bt);
				String readMsg = new String (bt, 0, size, "UTF-8");		
				System.out.println("�޾ƽ�");
				send(cliAddr, readMsg); //�޼ҵ� ��
			}		
			
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}
	
	//�޽��� �Ѹ���
	public void send(InetAddress cliAddr, String readMsg) {
		try {
			
			for (int a = 0; a < clientInfo.size(); a++) {
				ServerClientInfo cliSocket = clientInfo.get(a);				
				byte []br = readMsg.getBytes("UTF-8");
				OutputStream os = cliSocket.ss.getOutputStream();
				os.write(br);
				System.out.println("�ٽ� ������");
			}	
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}	
	
	
	
	
	
	
}