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
	
	ServerThreadUser(Socket cliSock, ArrayList<ServerClientInfo> clientInfo){
		this.cliSock = cliSock;
		this.clientInfo = clientInfo;
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
				if(readMsg.startsWith("chat:")||readMsg.startsWith("point:")){
					send(cliAddr, readMsg); //�޼ҵ� ��     
				}else if(readMsg.startsWith("login:")){
					loginCheck(cliAddr, readMsg);
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
	public void loginCheck(InetAddress cliAddr, String readMsg){
		
		readMsg = readMsg.substring(6, readMsg.length());
		String [] logCheck = readMsg.split(",");
		
		try {			
			//1. ����̹� �ε�
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver); //class���� ���� �־��ָ� �ڵ����� ��ü�� ������ִ� ������ ��.
			System.out.println("����̹� �ε��Ͽ����ϴ�.");

			//2.
			String url = "jdbc:oracle:thin:@192.168.20.23:1521:xe";
			String username = "sys as sysdba"; //����Ŭ �α��� ���̵� (������ ū�ϳ�)
			String password = "jey1234"; //����Ŭ �α��� ��й�ȣ
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("DB ���� ����!");

			//3.
			Statement st = con.createStatement();
			
			//4. st�� �̿��ؼ� ���� �ۼ�
			String sql = "SELECT * FROM memberJoin";
			
			//5. ���� ���α׷� ����
			ResultSet resultSQL = st.executeQuery(sql);
			System.out.println("����Ŭ���� �����Խ��ϴ�.");
			
			//6. select�ؿ� ����� �̾Ƴ�
			String getID = null;
			String getPW = null;
			String loginResult = null;
			while(resultSQL.next()){ 				
				getID = resultSQL.getString("id");
				getPW = resultSQL.getString("pw");					
			}	
			
			//�α��� Ȯ�� ���ǹ�
			if(!(logCheck[0].equals(getID))){	
				System.out.println("�������� �ʴ� ID");
				loginResult = "failID:";
			}else if(!(logCheck[1].equals(getPW))){
				System.out.println("pw Ʋ��");
				loginResult = "failPW:";
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void sendLoginResult(InetAddress cliAddr, String loginResult) {	// 
		try {
			byte[] bt = loginResult.getBytes("UTF-8");
			OutputStream sender = cliSock.getOutputStream();
			sender.write(bt);
			for (int a = 0; a < clientInfo.size(); a++) {
				ServerClientInfo cliSocket = clientInfo.get(a);				
				byte []br = loginResult.getBytes("UTF-8");
				OutputStream os = cliSocket.ss.getOutputStream();
				os.write(br);
				System.out.println("�ٽ� ������");
			}	
			
		} catch (IOException e) {
			System.out.println("error : " + e);
		}

	}
}