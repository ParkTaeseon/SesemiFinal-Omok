package omokLogin;

import java.io.InputStream;
import java.net.Socket;

//�α��� ���
public class ClientThreadLoginResult extends Thread{
	Socket connSock = null;
	
	public ClientThreadLoginResult(Socket connSock) {
		this.connSock = connSock;
	}
	
	public void run(){
	      try {         
	         while(true){             
	            InputStream receiver = connSock.getInputStream();// ���Ͽ��� �����͸� ���������� �� �ʿ���.
	            byte []bt = new byte[256];
	            int size = receiver.read(bt);
	            String readMsg = new String (bt, 0, size, "UTF-8");
	            
	            if(readMsg.startsWith("failID:")){
	               //�˾� ����
	            	System.out.println("Ŭ���̾�Ʈ ���̵� ����");
	            }else if(readMsg.startsWith("failPW:")){
	            	System.out.println("Ŭ���̾�Ʈ ��� ����");
	            }else if(readMsg.startsWith("welcome:")){
	            	System.out.println("Ŭ���̾�Ʈ ����");
	            }
	         }          
	         
	      } catch (Exception e) {         
	         System.out.println("error : " + e);
	      }      
	   }
	
}
