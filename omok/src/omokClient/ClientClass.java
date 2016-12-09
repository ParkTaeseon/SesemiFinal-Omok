package omokClient;

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
			
			//���Ӹ���
			GameEntryPoint gep = new GameEntryPoint();
			gep.gameMain(connSock); //���� ������ ������ GUI�� ���ư���.			

		} catch (Exception e) {
			System.out.println("error : " + e);
		}

	}
}