package com.omok.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//--------------------------------------------------------------------------
class ThreadUser extends Thread{	
	Socket cliSock;	
	ArrayList<Socket> clientInfo;
	
	ThreadUser(Socket cliSock, ArrayList<Socket> clientInfo){
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

//--------------------------------------------------------------------------
class ThreadConnect extends Thread{	
	//���� �����ϴ� list
	ArrayList<Socket> clientInfo = new ArrayList<Socket>();
	ServerSocket servSock = null;
	
	ThreadConnect(ServerSocket servSock){
		this.servSock = servSock;
	}
	
	//start�� �ڵ� ����
	public void run(){
		try {
			while(true){
				Socket cliSock = servSock.accept();//������������ ã�ƿ� ���� �� ���� �������� ���������
				System.out.println("Ŭ���̾�Ʈ�� �����Ͽ����ϴ�.");
				
				//���� Ŭ���̾�Ʈ �����ϴ� ����Ʈ�� �����Ѵ�.			
				clientInfo.add(cliSock);				
				
				//������ ��
				ThreadUser user = new ThreadUser(cliSock, clientInfo);
				user.start();
			}				
		} catch (Exception e) {
			System.out.println("error : " + e);
		}		
	}		
}


//---------------------------------------------------------------------------
public class GameServerClass{	
	
	public static void main(String[] args){
		try {			
			ServerSocket servSock = new ServerSocket();//������ ���� ����
			System.out.println("���� ������ �����Ͽ����ϴ�.");
			
			InetSocketAddress servAddr = new InetSocketAddress("127.0.0.1", 1818);//������ IP�ּҿ� ��Ʈ��ȣ�� ����
			servSock.bind(servAddr); //���Ͽ� ������ ������ �ּ�(IP,Port)�� ����Ѵ�.	
			System.out.println("Ŭ���̾�Ʈ ���ε��� �����Ͽ����ϴ�. ������ ��ٸ��ϴ�...");
			
			//�����ϴ� ������
			ThreadConnect connect = new ThreadConnect(servSock);
			connect.start();		
			
		} catch (Exception e) {
			System.out.println(e+"���� �߻�");
		}
	}

}
