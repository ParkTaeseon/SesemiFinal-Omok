package omokLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBLoginCheck {

	public static void DBlogin(String id, String pw) {
		// TODO Auto-generated method stub

		try {			
			//1. ����̹� �ε�
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver); //class���� ���� �־��ָ� �ڵ����� ��ü�� ������ִ� ������ ��.
			System.out.println("����̹� �ε��Ͽ����ϴ�.");

			//2.
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String username = "sys as sysdba"; //����Ŭ �α��� ���̵� (������ ū�ϳ�)
			String password = "jey1234"; //����Ŭ �α��� ��й�ȣ
			Connection con = DriverManager.getConnection(url, username, password);
			System.out.println("DB ���� ����!");

			//3.
			Statement st = con.createStatement();
			
			//4. st�� �̿��ؼ� ���� �ۼ�
			String sqlID = "SELECT * FROM memberJoin WHERE id = '"+id+"'";
			String sqlPW = "SELECT * FROM memberJoin WHERE pw = '"+pw+"'";
			
			if(id.equals(sqlID)){
				System.out.println("���̵� �½��ϴ�.");
			}
			
			if(pw.equals(sqlPW)){
				System.out.println("��й�ȣ �½��ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}			
		
		
	}

}
