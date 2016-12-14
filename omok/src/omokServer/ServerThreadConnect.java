package omokServer;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

//Ŭ���̾�Ʈ ������ �������ִ� ������
public class ServerThreadConnect extends Thread{
	//���� �����ϴ� list
	ArrayList<ServerClientInfo> clientInfo = new ArrayList<ServerClientInfo>();
	ServerSocket servSock = null;

	ServerThreadConnect(ServerSocket servSock){
		this.servSock = servSock;
	}

	//start�� �ڵ� ����
	public void run(){
		//��� 2�� ������ ��������..
		int peopleCount = 1;
		
		try {
			while(true){		
				
				if(peopleCount > 2){
					System.out.println("�������� ���մϴ�.");					
				}else{
					Socket cliSock = servSock.accept();//������������ ã�ƿ� ���� �� ���� �������� ���������
					System.out.println("Ŭ���̾�Ʈ"+peopleCount+"���� �����Ͽ����ϴ�.");

					//���� Ŭ���̾�Ʈ �����ϴ� ����Ʈ�� �����Ѵ�.			
					clientInfo.add(new ServerClientInfo(cliSock, peopleCount));				

					//������ ��					
					ServerThreadDB db = new ServerThreadDB();
					db.start();
					
					ServerThreadUser user = new ServerThreadUser(cliSock, clientInfo, db);
					user.start();
					
					//��� ����
					peopleCount++;					
					break;
				}
				
			}				
		} catch (Exception e) {
			System.out.println("error : " + e);
		}		
	}	
}
