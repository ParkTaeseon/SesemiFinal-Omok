package com.omok.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

//Ŭ���̾�Ʈ �����ϰ� �޽��� ������
public class ClientClass {

	public static void main(String[] args){
		try {
			//������ ������ �� ����� ���� ����
			Socket connSock = new Socket(); 
			System.out.println("Ŭ���̾�Ʈ ������ �����Ͽ����ϴ�.");

			InetSocketAddress connAddr = new InetSocketAddress("127.0.0.1", 1818);
			connSock.connect(connAddr); // ������ ������ �ּҷ� ���� 
			System.out.println("������ �����Ͽ����ϴ�.");            

			//�޽��� ������
			OutputStream sender = connSock.getOutputStream(); //�������� �����͸� �������� �� �ʿ���.
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sender));

			//�Է¹޾� ���۵彺Ʈ������ ����
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

			//���� �޽���
			String sendMsg = null;
			
			//while�� ������ ��� �� �� �ְ� ���ش�.
			while(true){
				ClientThreadRead read = new ClientThreadRead(connSock);
				read.start();

				//run�� ���鼭 �����尡 ����ȴ�.
				sendMsg = keyboard.readLine(); 
				pw.println(sendMsg);
				pw.flush();

				if(sendMsg.equals("esc")){
					break;
				}          
			}

		} catch (Exception e) {
			System.out.println("error : " + e);
		}

	}
}