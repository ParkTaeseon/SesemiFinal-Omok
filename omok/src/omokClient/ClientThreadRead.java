package omokClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

//���� �����ϰ� �޽��� �д� ������
public class ClientThreadRead extends Thread{
	GameEntryPoint gep = null;
	Socket connSock = null;

	ClientThreadRead(Socket connSock, GameEntryPoint gep){
		this.connSock = connSock;
		this.gep = gep;
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
				gep.infoArea.getText();	
				System.out.println("�ޱ�");
				//System.out.println(readMsg); // ���۰� ���
			}          
		} catch (Exception e) {
			
			System.out.println("error : " + e);
		}      
	}   
}
