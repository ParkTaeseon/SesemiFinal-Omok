package omokClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javafx.scene.control.TextArea;

//���� �����ϰ� �޽��� �д� ������
public class ClientThreadRead extends Thread{
	TextArea infoArea;
	Socket connSock;

	ClientThreadRead(Socket connSock, TextArea infoArea){
		this.connSock = connSock;
		this.infoArea = infoArea;
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
				//infoArea.getText();	
				System.out.println("�ޱ�");
				System.out.println(readMsg); // ���۰� ���
			}          
		} catch (Exception e) {
			
			System.out.println("error : " + e);
		}      
	}   
}
