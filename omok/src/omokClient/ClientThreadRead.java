package omokClient;

import java.io.InputStream;
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
			while(true){ 				
				InputStream receiver = connSock.getInputStream();// ���Ͽ��� �����͸� ���������� �� �ʿ���.
				byte []bt = new byte[256];
				int size = receiver.read(bt);
				String readMsg = new String (bt, 0, size, "UTF-8");				
				
				if(readMsg.startsWith("chat:")){
					readMsg = readMsg.substring(5, readMsg.length());
					infoArea.setText(infoArea.getText()+readMsg+"\n");
				}
				else if(readMsg.startsWith("point:")){
					infoArea.setText(infoArea.getText()+"\n"+"���梺 "+readMsg);
				}
			}          
			
		} catch (Exception e) {			
			System.out.println("error : " + e);
		}      
	}   
}
