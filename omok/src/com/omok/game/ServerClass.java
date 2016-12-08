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

public class ServerClass{	
	
	public static void main(String[] args){
		try {			
			ServerSocket servSock = new ServerSocket();//������ ���� ����
			System.out.println("���� ������ �����Ͽ����ϴ�.");
			
			InetSocketAddress servAddr = new InetSocketAddress("127.0.0.1", 1818);//������ IP�ּҿ� ��Ʈ��ȣ�� ����
			servSock.bind(servAddr); //���Ͽ� ������ ������ �ּ�(IP,Port)�� ����Ѵ�.	
			System.out.println("Ŭ���̾�Ʈ ���ε��� �����Ͽ����ϴ�. ������ ��ٸ��ϴ�...");
			
			//�����ϴ� ������
			ServerThreadConnect connect = new ServerThreadConnect(servSock);
			connect.start();		
			
		} catch (Exception e) {
			System.out.println(e+"���� �߻�");
		}
	}

}
