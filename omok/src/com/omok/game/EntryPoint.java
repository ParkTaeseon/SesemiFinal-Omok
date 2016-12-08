package com.omok.game;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.*;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

//���� 
public class EntryPoint extends Application{

	int [] point = new int[2];	// ���콺 Ŭ�� ��ǥ�� ���� �迭

	StoneHandle sh = new StoneHandle();	//�� ���� �˰��� ������

	//�����ǰ� ���õ� Ŭ���� ����
	allOmokPanClass allPan = new allOmokPanClass();

	//��� ���� ���õ� Ŭ���� ����
	peopleEnterClass allpeople = new peopleEnterClass();

	//���� ���� ������
	public EntryPoint(){
		System.out.println("AppMain ������ ��");
	}

	//init �޼ҵ� ȣ��
	@Override
	public void init(){
		System.out.println("init �޼ҵ� ��");
	}	 

	//start ȣ��
	@Override
	public void start(Stage stage) throws Exception{
		System.out.println("start �޼ҵ� ��");

		short secondCount = 0;

		//��. AnchorPane�� �����̳� 8�� �� �ϳ���.
		AnchorPane root = new AnchorPane();
		root.setPrefWidth(830); //����ũ��
		root.setPrefHeight(660); //����ũ��

		//���� ���ؼ� scene�� ������ �Ѵ�.
		Scene scene = new Scene(root);
		stage.setScene(scene);

		//�ʼ��� ���α׷��� 30��
		ProgressBar minuteCount = new ProgressBar();
		minuteCount.setPrefSize(740, 25); //���α׷��� ��. ����, ����
		minuteCount.setLayoutX(70); //���α׷��� �� ��ġ x��
		minuteCount.setLayoutY(20); //���α׷��� �� ��ġ y��
		minuteCount.setProgress(1.0d); //���α׷��� ���� ��		
		root.getChildren().add(minuteCount);

		//������ �׸� �ø���
		Canvas omokPan = new Canvas(580, 580); //������ �׸��� ĵ����
		GraphicsContext omokGC = omokPan.getGraphicsContext2D();
		allPan.drawPan(omokGC); //���� �޼ҵ� ȣ��
		omokPan.setLayoutX(20); //ĵ���� x�� ������ġ
		omokPan.setLayoutY(58); //ĵ���� y�� ������ġ
		root.getChildren().add(omokPan);

		//���� �� �׸���
		Canvas omokStone = new Canvas(30, 30);
		GraphicsContext stoneGC = omokPan.getGraphicsContext2D();   
		root.getChildren().add(omokStone);

		//��� ���� �ڸ�
		Canvas peopleEnter = new Canvas(190, 200);
		GraphicsContext peopleGC = peopleEnter.getGraphicsContext2D();
		allpeople.peopleEnter(peopleGC);
		peopleEnter.setLayoutX(620); //���� x��
		peopleEnter.setLayoutY(58); //���� y��
		root.getChildren().add(peopleEnter);

		//���� ä��â ������....
		TextArea infoArea = new TextArea();
		infoArea.setPrefSize(190, 300); //ä��â. ����, ����
		infoArea.setLayoutX(620); //â ��ġ x��
		infoArea.setLayoutY(275); //â ��ġ y��
		root.getChildren().add(infoArea);   
		infoArea.setText("�������� �����ϼ̽��ϴ�.");

		//���ӽ��� ��ư�� ����� �ش�.         
		Button startBtn = new Button();
		startBtn.setText("���� ����");
		startBtn.setPrefSize(190, 50); //��ư ũ�� ���ϱ�. ����, ����
		startBtn.setLayoutX(620); //��ư ��ġ x��
		startBtn.setLayoutY(590); //��ư ��ġ y��
		root.getChildren().add(startBtn);   

		//��ư ���� �̺�Ʈ ����
		startBtn.setOnAction((event) -> {
			if (startBtn.getText().equals("���� ����")) {
				startBtn.setText("���� ���");
			}
		});	

		//���콺 �̺�Ʈ �ڵ鷯
		omokPan.setOnMouseClicked((event) -> {         
			System.out.println(event.getX()+ ", "+event.getY());  

			//�� ����
			omokStone.setLayoutX(event.getX()-15); //ĵ���� x�� ������ġ
			omokStone.setLayoutY(event.getY()-15); //ĵ���� y�� ������ġ

			//���� �� ���� ���ʿ��� ���� �������� �����־�� �Ѵ�.
			if(!(event.getX() < 8 || event.getX() > 572 || event.getY() < 8 || event.getY() > 572)){

				point[0] = allPan.nearPointArrayValue(event.getY());
				point[1] = allPan.nearPointArrayValue(event.getX());

				if(sh.isCheck(point[0], point[1]) == 0){
					if(allPan.blackBool){ //ó���� ������ �浹 ����													//
						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC);  //
					}else if(allPan.whiteBool){																	//   �� �׸��� �޼ҵ�!
						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC);  //
					}
					sh.stone(point); // �ܼ�â�� �������� �����͸� �Է����ִ� �޼ҵ�
				}
			}                  
		});	

		//ȭ�� ���� ����
		stage.setTitle("�������");
		stage.show();
	}


	@Override
	public void stop(){
		System.out.println("stop �޼ҵ� ��");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//���� �Լ����� �ڹ�fx�� ����.
		launch();      
		//StoneHandle.gameStart();
	}

}