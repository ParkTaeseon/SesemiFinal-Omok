package omokClient;

import java.io.InputStream;
import java.net.Socket;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;

//���� �����ϰ� �޽��� �д� ������
public class ClientThreadRead extends Thread{
   TextArea infoArea;
   Socket connSock;
   GameOmokPanClass allPan;
   GraphicsContext stoneGC;
   GameStoneHandle sh;
   
   ClientThreadRead(Socket connSock, TextArea infoArea, GameOmokPanClass allPan, GraphicsContext stoneGC, GameStoneHandle sh){
      this.connSock = connSock;
      this.infoArea = infoArea;
      this.allPan = allPan;
      this.stoneGC= stoneGC;
      this.sh = sh;
   }

   public void run(){
      try {         
         while(true){             
            InputStream receiver = connSock.getInputStream();// ���Ͽ��� �����͸� ���������� �� �ʿ���.
            byte []bt = new byte[256];
            int size = receiver.read(bt);
            String readMsg = new String (bt, 0, size, "UTF-8");            
            
            if(readMsg.startsWith("chat:")){
               readMsg = readMsg.substring(5, readMsg.length());
               infoArea.setText(infoArea.getText()+readMsg+"\n");
            }
            else if(readMsg.startsWith("point:")){
               readMsg = readMsg.substring(6, readMsg.length());
               String [] pointXY = readMsg.split(",");
               int point[] = new int[2];
               for(int i = 0 ; i < 2 ; i++)
                  point [i] = Integer.parseInt(pointXY[i]);               
             
               allPan.stone(point[1]*30+20,point[0]*30+20, stoneGC);
               sh.stone(point);	// �˰���!
            }
         }          
         
      } catch (Exception e) {         
         System.out.println("error : " + e);
      }      
   }   
}