package omokLogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBLoginCheck {

	public static void DBlogin(String logId, String logPw) {
		// TODO Auto-generated method stub

		try {			
			//1. ����̹� �ε�
			String driver = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driver); //class���� ���� �־��ָ� �ڵ����� ��ü�� ������ִ� ������ ��.
			System.out.println("����̹� �ε��Ͽ����ϴ�.");

			//2.
			String url = "jdbc:oracle:thin:@192.168.20.25:1521:xe";
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
			while(resultSQL.next()){ 				
				getID = resultSQL.getString("id");
				getPW = resultSQL.getString("pw");					
			}	
			
			//�α��� Ȯ�� ���ǹ�
			if(!(logId.equals(getID))){
				System.out.println("���̵� �ٽ� �Է����ּ���.");
			}else if(!(logPw.equals(getPW))){
				System.out.println("��й�ȣ�� �ٽ� �Է����ּ���.");
			}else if(logId.equals(getID) && logPw.equals(getPW)){
				System.out.println("�α��� �����Ͽ����ϴ�.");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}			
		
		
	}

}
