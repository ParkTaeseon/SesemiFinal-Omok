package omokClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThreadSend {
	Socket connSock;
	GameEntryPoint gep;
	
	ClientThreadSend(Socket connSock, GameEntryPoint gep) {
		this.connSock = connSock;
		this.gep = gep;
	}
	
	void clientSend(){	
		
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
				ClientThreadRead read = new ClientThreadRead(connSock, gep);
				read.start();

				//run�� ���鼭 �����尡 ����ȴ�.
				sendMsg = keyboard.readLine(); 
				gep.chatInput.setText(sendMsg);	
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
