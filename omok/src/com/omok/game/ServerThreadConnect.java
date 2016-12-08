package com.omok.game;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//Ŭ���̾�Ʈ ������ �������ִ� ������
public class ServerThreadConnect extends Thread{
	//���� �����ϴ� list
	ArrayList<Socket> clientInfo = new ArrayList<Socket>();
	ServerSocket servSock = null;

	ServerThreadConnect(ServerSocket servSock){
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
				ServerThreadUser user = new ServerThreadUser(cliSock, clientInfo);
				user.start();
			}				
		} catch (Exception e) {
			System.out.println("error : " + e);
		}		
	}	
}
