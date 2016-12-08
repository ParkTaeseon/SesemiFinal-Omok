package omokLogin;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class rootController implements Initializable{
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private TextField logId;
	@FXML private PasswordField logPw;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//ȸ������ ��ư
		btn1.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try{
					Parent joinus = FXMLLoader.load(getClass().getResource("joinUs.fxml"));
					Scene scene = new Scene(joinus);
					Stage primaryStage = (Stage)btn1.getScene().getWindow();
					primaryStage.setScene(scene);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});	
		
		//�α��� ��ư
		btn2.setOnAction(new EventHandler<ActionEvent>() {			
			@Override
			public void handle(ActionEvent event) {	
				
				//db�� id�� �н������ �г��� �Ѱ��ֱ�
				String strLogId = logId.getText();
				String strLogPw = logPw.getText();
				
				//db���� ���� ȣ���ϱ�
				DBLoginCheck dbLogin = new DBLoginCheck();
				dbLogin.DBlogin(strLogId, strLogPw);								
				
				System.out.println("�α��� �����Ͽ����ϴ�.");
			}
		});	
		
		
	}
}
