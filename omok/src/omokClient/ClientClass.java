package omokClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

//Ŭ���̾�Ʈ �����ϰ� �޽��� ������
public class ClientClass {
	Socket connSock = null;
	
	ClientClass(Socket connSock){
		this.connSock = connSock;
	}
	
	public void clientMain(){		
		//���Ӹ���
		GameEntryPoint gep = new GameEntryPoint();
		gep.gameMain(connSock); //���� ������ ������ GUI�� ���ư���.	
		System.out.println(1);//���ӿ�
	}
}