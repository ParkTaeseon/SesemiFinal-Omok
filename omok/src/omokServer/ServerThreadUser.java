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
	ArrayList<Socket> clientInfo;
	
	ServerThreadUser(Socket cliSock, ArrayList<Socket> clientInfo){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
	}
	
	@Override
	public void run(){
		try {
			InetAddress cliAddr = cliSock.getInetAddress();
			System.out.println(cliAddr.getHostAddress() + "����");

			//� Ŭ���̾�Ʈ ���Դ��� Ȯ���ؾ���.
			for(Socket ci:clientInfo){
				System.out.println(ci);
			}

			//�ޱ�
			InputStream receiver = cliSock.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(receiver));

			String readMsg = null;         
			while(true){ 
				readMsg = br.readLine();
				send(cliAddr, readMsg); //�޼ҵ� ��
			} 
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}
	
	//�޽��� �Ѹ���
	public void send(InetAddress cliAddr, String readMsg) {
		try {
			for(Socket socket:clientInfo){
				
				OutputStream sender = socket.getOutputStream();// client �������κ���
				PrintWriter pw = new PrintWriter(new OutputStreamWriter(sender));

				pw.println(cliAddr.getHostAddress() + " : " + readMsg); // Ŭ���̾�Ʈ����
				pw.flush(); // �÷���~
			}			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}		
}