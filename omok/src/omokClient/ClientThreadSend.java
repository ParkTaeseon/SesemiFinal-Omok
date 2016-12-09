package omokClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ClientThreadSend extends Thread{
	Socket connSock;
	TextArea infoArea;
	TextField chatInput;
	
	ClientThreadSend(Socket connSock, TextArea infoArea, TextField chatInput) {
		this.connSock = connSock;
		this.infoArea = infoArea;
		this.chatInput = chatInput;
	}
	
	public void run(){			
		try {
			//�޽��� ������
			OutputStream sender = connSock.getOutputStream(); //�������� �����͸� �������� �� �ʿ���.
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sender));

			//�Է¹޾� ���۵彺Ʈ������ ����
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

			//���� �޽���
			String sendMsg = null;
			
			//while�� ������ ��� �� �� �ְ� ���ش�.
			while(true){
				ClientThreadRead read = new ClientThreadRead(connSock, infoArea);
				read.start();

				//run�� ���鼭 �����尡 ����ȴ�.
				sendMsg = keyboard.readLine(); 
				//chatInput.setText(sendMsg);	
				System.out.println("������");
				
				pw.println(sendMsg);
				pw.flush();

				if(sendMsg.equals("esc")){
					break;
				}          
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}	
}
