package omokLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBMemberJoin {

	public static void DBJoin(String joinId, String joinPw, String joinNick) {
		// TODO Auto-generated method stub
		
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
			
			//st -> INSERT INTO memberJoin(id, pw, nick, win, draw, lose)
			//pst -> INSERT INTO memberJoin(id, pw, nick, ?, ?, ?)
			
			//4. st�� �̿��ؼ� ���� �ۼ�
			String sql = "INSERT INTO memberJoin(id, pw, nick) "
					+ "VALUES ('"+joinId+"', '"+joinPw+"', '"+joinNick+"')";
			//����. sql���� ���� �� �״�� ��. ���⼭ ���ڸ� ������ ����ؼ� oracle�� �����.
			//�̷������� ���� �Ǹ� ����Ŭ�� 1000�� ����ǰ� �ȴ�. UPDATE, DELETE�� ����.

			//5. ���� ���α׷� ����
			st.executeUpdate(sql);	

			if(joinId.equals(null)){
				System.out.println("���̵� �Է����ּ���.");
			}
			
			if(joinPw.equals(null)){
				System.out.println("��й�ȣ�� �Է����ּ���.");
			}
			
			if(joinNick.equals(null)){
				System.out.println("�г����� �Է����ּ���.");
			}
			
			System.out.println("����Ŭ�� ����Ǿ����ϴ�.");
			System.out.println("�����Ͽ����ϴ�.");
			
		} catch (Exception e) {
			System.out.println(e);
		}	


	}
}
