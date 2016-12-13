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
			Socket cliSock;
			
			while(true){					
				if(peopleCount <= 2){
					cliSock = servSock.accept();//������������ ã�ƿ� ���� �� ���� �������� ���������					
					System.out.println("Ŭ���̾�Ʈ"+peopleCount+"���� �����Ͽ����ϴ�.");
					peopleCount++;

					//���� Ŭ���̾�Ʈ �����ϴ� ����Ʈ�� �����Ѵ�.			
					clientInfo.add(new ServerClientInfo(cliSock, peopleCount));	
					
					//������ ��
					ServerThreadUser user = new ServerThreadUser(cliSock, clientInfo);
					user.start();
				}else if(peopleCount > 2){
					System.out.println("�������� ���մϴ�.");
					break;
				}
				
			}				
		} catch (Exception e) {
			System.out.println("error : " + e);
		}		
	}	
}
