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
		//���Ӹ���
		GameEntryPoint gep = new GameEntryPoint();
		gep.gameMain(); //���� ������ ������ GUI�� ���ư���.	
		
	}
}