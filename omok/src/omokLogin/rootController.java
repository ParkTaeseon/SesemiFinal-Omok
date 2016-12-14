package omokLogin;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class rootController implements Initializable{
	@FXML private Button btn1;
	@FXML private Button btn2;
	@FXML private TextField logId;
	@FXML private PasswordField logPw;
	@FXML private HBox loginPU;
	@FXML private ImageView imgMessage;
	
	Socket connSock = null;
	
	
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
		
		
		//������ ������ �� ����� ���� ����		
		try {
			connSock = new Socket(); 
			System.out.println("Ŭ���̾�Ʈ ������ �����Ͽ����ϴ�.");

			InetSocketAddress connAddr = new InetSocketAddress("127.0.0.1", 1818);
			connSock.connect(connAddr); // ������ ������ �ּҷ� ���� 
			System.out.println("������ �����Ͽ����ϴ�.");
			
			//
			ClientThreadLoginResult loginResult = new ClientThreadLoginResult(connSock);
			loginResult.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}					
		
		//�α��� ��ư -> ������ �Ѱ���� �Ѵ�.
		btn2.setOnAction(new EventHandler<ActionEvent>() {	
			
			@Override
			public void handle(ActionEvent event) {		
				try {
					//�α���â���� �� id�� pw�� �޴´�.
					String strLogId = logId.getText();
					String strLogPw = logPw.getText();
					
					//�޾Ƴ� string�� login�� �ٿ��� logID, logPW�� �ѱ��.
					String loginIDPW = "login:"+strLogId+","+strLogPw;
					byte []bt = loginIDPW.getBytes("UTF-8");
					OutputStream sender = connSock.getOutputStream();
					sender.write(bt);
										
				} catch (Exception e) {
					System.out.println("���ܹ߻�"+e);
				}
				
			}
		});	
		
	}
}
