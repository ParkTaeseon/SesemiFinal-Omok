package omokServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//Ŭ���̾�Ʈ ������ �������ִ� ������
public class ServerThreadDB extends Thread{
	String getID = null;
	String getPW = null;
	String getNICK = null;
	String getTOTAL = null;
	
	//start�� �ڵ� ����
	public void run(){
		
		try {			
			//1. ����̹� �ε�
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver); //class���� ���� �־��ָ� �ڵ����� ��ü�� ������ִ� ������ ��.
			System.out.println("����̹� �ε��Ͽ����ϴ�.");
			
			//2.
			String url = "jdbc:oracle:thin:@192.168.20.28:1521:xe";
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
			while(resultSQL.next()){ 				
				getID = resultSQL.getString("id");
				getPW = resultSQL.getString("pw");	
				getNICK = resultSQL.getString("nick");	
				getTOTAL = resultSQL.getString("total");
			}	
			
			
		} catch (Exception e) {
			System.out.println(e);
		}		
	}	
}
