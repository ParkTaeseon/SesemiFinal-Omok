package omokClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

//���� �����ϰ� �޽��� �д� ������
public class ClientThreadRead extends Thread{
	Socket connSock = null;

	ClientThreadRead(Socket connSock){
		this.connSock = connSock;
	}

	public void run(){
		try {
			//�ޱ�
			InputStream receiver = connSock.getInputStream();// ���Ͽ��� �����͸� ���������� �� �ʿ���.
			BufferedReader br = new BufferedReader(new InputStreamReader(receiver));

			//�޽��� �б�
			String readMsg = null;         
			while(true){ 
				readMsg = br.readLine();
				System.out.println(readMsg); // ���۰� ���
			}          
		} catch (Exception e) {
			System.out.println("error : " + e);
		}      
	}   
}
