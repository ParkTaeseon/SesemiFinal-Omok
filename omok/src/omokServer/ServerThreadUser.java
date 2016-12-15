package omokServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//�޽��� �а� ���� �ϴ� ������
class ServerThreadUser extends Thread{	
	Socket cliSock;	
	ArrayList<ServerClientInfo> clientInfo;
	ServerThreadDB db;	
	
	ServerThreadUser(Socket cliSock, ArrayList<ServerClientInfo> clientInfo, ServerThreadDB db){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
		this.db = db;
	}
	
	@Override
	public void run(){
		try {
			InetAddress cliAddr = cliSock.getInetAddress();
			System.out.println(cliAddr.getHostAddress() + "����");

			//� Ŭ���̾�Ʈ ���Դ��� Ȯ���ؾ���.
			for(ServerClientInfo ci:clientInfo){
				System.out.println(ci);
			}				
			
			//Ŭ���̾�Ʈ���� ���� �� �ޱ�
			while(true){
				InputStream receiver = cliSock.getInputStream();
				byte []bt = new byte[256];
				int size = receiver.read(bt);
				String readMsg = new String (bt, 0, size, "UTF-8");		
				System.out.println("�޾ƽ�");
				if(readMsg.startsWith("chat:")||readMsg.startsWith("point:")){ //chat���� �����ϰų� point:�����ϸ�  
					send(cliAddr, readMsg); //���ӵ� Ŭ���̾�Ʈ���� �޽��� �״�� ����...//�� �޼����� ���� Ŭ���̾�Ʈ�� �ƴ϶�....������� Ŭ���̾�Ʈ���� �����ϵ��� ������...������ ���������� �ٽ� �����ִµ�...
				}else if(readMsg.startsWith("login:")){//login���� �����ϸ�
					loginCheck(readMsg); //�α����� Ȯ���ϴ� �޼ҵ�loginCheck�� ȣ��, ���ڴ� ��� ���� �޽����� �־��ش�.
					System.out.println("�α��� üũ");
				}
			}		
			
		} catch (Exception e) {
			System.out.println("error : " + e);
		}
	}
	
	//�޽��� �Ѹ���
	public void send(InetAddress cliAddr, String readMsg) {
		try {
			
			for (int a = 0; a < clientInfo.size(); a++) {
				ServerClientInfo cliSocket = clientInfo.get(a);				
				byte []br = readMsg.getBytes("UTF-8");
				OutputStream os = cliSocket.ss.getOutputStream();
				os.write(br);
				System.out.println("�ٽ� ������");
			}	
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}	
	
	//�α��� üũ
	public void loginCheck(String readMsg){		
		//adMsg = readMsg.substring(6, readMsg.length());//�̵����� ��������!! ���ŵ� �޽����� login: <--�䷸�� 6���ڰ� ������ �ڿ� ���̵�
		String [] logCheck = readMsg.split(":");
		
		String loginResult = null;
		try {			
			//�α��� Ȯ�� ���ǹ�
			if(!(logCheck[1].equals(db.getID))){	
				System.out.println("�������� �ʴ� ID");
				loginResult = "failID:";
				sendLoginResult(loginResult);
			}else if(!(logCheck[2].equals(db.getPW))){
				System.out.println("pw Ʋ��");
				loginResult = "failPW:";
				sendLoginResult(loginResult);
			}else{
				System.out.println("�α��� ����");
				System.out.println("total="+db.getTOTAL);
				loginResult = "welcome" + ":" + db.getNICK + ":" + db.getTOTAL; //�г���,���� �����ͼ� welcome Ű����� ����
				sendLoginResult(loginResult);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	//Ȯ�� ��� send
	public void sendLoginResult(String loginResult) {	
		try {
			byte[] bt = loginResult.getBytes("UTF-8");
			OutputStream sender = cliSock.getOutputStream();
			sender.write(bt);
			System.out.println("�α��� �ٽ� ������");

		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}
}