package omokLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBWinAndLose {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
			String sql = "INSERT INTO vod(id, win, draw, lose, total) VALUES ('eunyeong', 1, 0, 0, 1)";
			//����. sql���� ���� �� �״�� ��. ���⼭ ���ڸ� ������ ����ؼ� oracle�� �����.
			//�̷������� ���� �Ǹ� ����Ŭ�� 1000�� ����ǰ� �ȴ�. UPDATE, DELETE�� ����.

			//5. ���� ���α׷� ����
			st.executeUpdate(sql);	
			System.out.println("����Ŭ�� ����Ǿ����ϴ�.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	

}
