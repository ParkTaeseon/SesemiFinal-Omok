package omokServer;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerClass{	

	public static void main(String[] args){
		try {			
			ServerSocket servSock = new ServerSocket();//������ ���� ����
			System.out.println("���� ������ �����Ͽ����ϴ�.");

			InetSocketAddress servAddr = new InetSocketAddress("127.0.0.1", 1818);//������ IP�ּҿ� ��Ʈ��ȣ�� ����
			servSock.bind(servAddr); //���Ͽ� ������ ������ �ּ�(IP,Port)�� ����Ѵ�.	
			System.out.println("Ŭ���̾�Ʈ ���ε��� �����Ͽ����ϴ�. ������ ��ٸ��ϴ�...");

			// ��� ������ ��		
			ServerThreadDB db = new ServerThreadDB();
			db.start();

			//���� �����ϴ� list
			ArrayList<ServerClientInfo> clientInfo = new ArrayList<ServerClientInfo>();

			//Ŭ���̾�Ʈ �θ����� ���� ����
//			int cnt = 0;
//			boolean peopleTrue = true;

			while(true){
//				if(peopleTrue){
					//������������ ã�ƿ� ���� �� ���� �������� ���������	
					Socket cliSock = servSock.accept();		

//					cnt++;
					//���� Ŭ���̾�Ʈ �����ϴ� ����Ʈ�� �����Ѵ�.			
					clientInfo.add(new ServerClientInfo(cliSock));		
					System.out.println("Ŭ���̾�Ʈ"+clientInfo.size()+"���� �����Ͽ����ϴ�.");				

					//Ŭ���̾�Ʈ ���۸� �а� ������ ������!!!!!!!!
					ServerThreadUser user = new ServerThreadUser(cliSock, clientInfo, db);
					user.start();
					
//					cnt++;
//				}else if(cnt == 2){
//					peopleTrue = false;
//					cnt = 0;
//					break;
//				}

			}

		} catch (Exception e) {
			System.out.println(e+"���� �߻�");
		}
	}

}
