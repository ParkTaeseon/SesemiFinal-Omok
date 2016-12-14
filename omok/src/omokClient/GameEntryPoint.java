package omokClient;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//���� 
public class GameEntryPoint extends Application {
	Socket connSock = null;

	// ���α׷�����
	Task copyWorker;
	double progress = 1.0;

	final String chatStart = "chat:";
	final String pointStart = "point:";
	int[] point = new int[2]; // ���콺 Ŭ�� ��ǥ�� ���� �迭
	GameStoneHandle sh = new GameStoneHandle(); // �� ���� �˰��� ������

	// �����ǰ� ���õ� Ŭ���� ����
	GameOmokPanClass allPan = new GameOmokPanClass();

	// ��� ���� ���õ� Ŭ���� ����
	GamepeopleEnterClass allpeople = new GamepeopleEnterClass();

	// ���� ���� ������
	public GameEntryPoint() {
		System.out.println("GameEntryPoint ������ ��");
	}

	// init �޼ҵ� ȣ��
	@Override
	public void init() {
		System.out.println("init �޼ҵ� ��");
	}

	// start ȣ��
	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("start �޼ҵ� ��");

		// ��. AnchorPane�� �����̳� 8�� �� �ϳ���.
		AnchorPane root = new AnchorPane();
		root.setPrefWidth(830); // ����ũ��
		root.setPrefHeight(660); // ����ũ��

		// ���� ���ؼ� scene�� ������ �Ѵ�.
		Scene scene = new Scene(root);
		stage.setScene(scene);

		// ��
		final Label label = new Label();
		label.setPrefSize(100, 25);
		label.setLayoutX(20);
		label.setLayoutY(20);
		label.setText("���ѽð�30��");

		// �ʼ��� ���α׷��� 30��
		final ProgressBar pb = new ProgressBar(progress);
		pb.setPrefSize(700, 25); // ���α׷��� ��. ����, ����
		pb.setLayoutX(95); // ���α׷��� �� ��ġ x��
		pb.setLayoutY(20); // ���α׷��� �� ��ġ y��

		root.getChildren().addAll(pb, label);

		// ������ �׸� �ø���
		Canvas omokPan = new Canvas(580, 580); // ������ �׸��� ĵ����
		GraphicsContext omokGC = omokPan.getGraphicsContext2D();
		allPan.drawPan(omokGC); // ���� �޼ҵ� ȣ��
		omokPan.setLayoutX(20); // ĵ���� x�� ������ġ
		omokPan.setLayoutY(58); // ĵ���� y�� ������ġ
		root.getChildren().add(omokPan);

		// ���� �� �׸���
		Canvas omokStone = new Canvas(30, 30);
		GraphicsContext stoneGC = omokPan.getGraphicsContext2D();
		root.getChildren().add(omokStone);

		// ��� ���� �ڸ�1
		Canvas peopleEnter1 = new Canvas(190, 100);
		GraphicsContext peopleGC1 = peopleEnter1.getGraphicsContext2D();
		allpeople.peopleEnter1(peopleGC1);
		peopleEnter1.setLayoutX(620); // ���� x��
		peopleEnter1.setLayoutY(58); // ���� y��
		root.getChildren().add(peopleEnter1);

		// ��� ���� �ڸ�2
		Canvas peopleEnter2 = new Canvas(190, 100);
		GraphicsContext peopleGC2 = peopleEnter2.getGraphicsContext2D();
		allpeople.peopleEnter2(peopleGC2);
		peopleEnter2.setLayoutX(620); // ���� x��
		peopleEnter2.setLayoutY(162); // ���� y��
		root.getChildren().add(peopleEnter2);

		// ���� ä��â ������....
		TextArea infoArea = new TextArea();
		infoArea.setPrefSize(190, 270); // ä��â. ����, ����
		infoArea.setLayoutX(620); // â ��ġ x��
		infoArea.setLayoutY(275); // â ��ġ y��
		root.getChildren().add(infoArea);

		// ���� ä�� ���� ĭ....
		TextField chatInput = new TextField();
		chatInput.setPrefSize(190, 30);
		chatInput.setLayoutX(620);
		chatInput.setLayoutY(550);
		root.getChildren().add(chatInput);

		// ���ӽ��� ��ư�� ����� �ش�.
		final Button startBtn = new Button("Start");
		startBtn.setText("���� ����");
		startBtn.setPrefSize(80, 50); // ��ư ũ�� ���ϱ�. ����, ����
		startBtn.setLayoutX(620); // ��ư ��ġ x��
		startBtn.setLayoutY(590); // ��ư ��ġ y��
		root.getChildren().add(startBtn);


		//��� ��ư 
		final Button cancleBtn = new Button("calcle");
		cancleBtn.setText("���");
		cancleBtn.setPrefSize(80, 50);
		cancleBtn.setLayoutX(730); // ��ư ��ġ x��
		cancleBtn.setLayoutY(590); // ��ư ��ġ y��
		root.getChildren().add(cancleBtn);


		// ���� ��ư ������ ���α׷����� �پ��. ���ѽð� 30��.
		startBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				startBtn.setDisable(true);
				pb.setProgress(progress); //�⺻����
				cancleBtn.setDisable(false);
				copyWorker = createWorker();
				pb.progressProperty().unbind();
				pb.progressProperty().bind(copyWorker.progressProperty());
				copyWorker.messageProperty().addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> observable, String oldValue,
							String newValue) {
						System.out.println(newValue);
					}
				});
				new Thread(copyWorker).start();
			}
		});


		//��� ��ư ������ ���α׷����� ����.
		cancleBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				startBtn.setDisable(false);
				cancleBtn.setDisable(true);
				copyWorker.cancel(true);
				pb.progressProperty().unbind();
				pb.setProgress(progress); //���α׷��� �⺻���� 1.0
				System.out.println("cancelled.");
			}
		});


		// ���콺 �̺�Ʈ �ڵ鷯
		omokPan.setOnMouseClicked((event) -> {
			System.out.println(event.getX() + ", " + event.getY());

			// �� ����
			omokStone.setLayoutX(event.getX() - 15); // ĵ���� x�� ������ġ
			omokStone.setLayoutY(event.getY() - 15); // ĵ���� y�� ������ġ

			// ���� �� ���� ���ʿ��� ���� �������� �����־�� �Ѵ�.
			if (!(event.getX() < 8 || event.getX() > 572 || event.getY() < 8 || event.getY() > 572)) {

				point[0] = allPan.nearPointArrayValue(event.getY());
				point[1] = allPan.nearPointArrayValue(event.getX());

				if (sh.isCheck(point[0], point[1]) == 0) {
					try {
						String xyPoint = "point:" + String.valueOf(point[0]) + "," + String.valueOf(point[1]);
						byte[] bt = xyPoint.getBytes("UTF-8");
						OutputStream sender = connSock.getOutputStream();
						sender.write(bt);

					} catch (Exception e) {
						System.out.println(e);
					}

//					if (allPan.blackBool) { // ó���� ������ �浹 ���� //
//						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC); //
//					} else if (allPan.whiteBool) { // �� �׸��� �޼ҵ�!
//						allPan.stone(allPan.nearPoint(event.getX()), allPan.nearPoint(event.getY()), stoneGC); //
//					}
//					sh.stone(point); // �ܼ�â�� �������� �����͸� �Է����ִ� �޼ҵ�
				}
			}
		});

		// --------------------------------------------
		// ���� ���� ���� ����
		try {
			//Ŭ���̾�Ʈ ä�� �д� ������ ��
			ClientThreadRead read = new ClientThreadRead(connSock, infoArea, allPan,  stoneGC, sh);
			read.start();

		} catch (Exception e) {
			System.out.println(e);
		}	

		//ä��â ����ġ��~
		chatInput.setOnKeyPressed((event) -> {
			//������ ������ ��.
			if(event.getCode()==KeyCode.ENTER){
				try {				
					byte []bt = chatStart.concat(chatInput.getText()).getBytes("UTF-8");
					OutputStream sender = connSock.getOutputStream();
					sender.write(bt);
					System.out.println("���� ��� ġ���ֵ�.");

				} catch (Exception e) {
					System.out.println(e);
				}

				chatInput.clear();				
			}
		});


		// ȭ�� ���� ����
		stage.setTitle("�������");
		stage.show();
	}

	//���α׷����� ���� ��Ű�� Task 
	public Task createWorker() {
		return new Task() {
			@Override
			protected Object call() throws Exception {
				for (int i = 1; i <= 30; i++) {
					Thread.sleep(1000);
					updateMessage("���ѽð� 30�ʷκ��� :" + (30-i) +"�� ����" );
					updateProgress(30-i, 30);
				}
				return true;
			}
		};
	}
	

	@Override
	public void stop() {
		System.out.println("stop �޼ҵ� ��");
	}

	public void gameMain(Socket connSock) {
		this.connSock = connSock;
		
		// ���� �Լ����� �ڹ�fx�� ����.
		System.out.println("GUI�� �����ϴ�.");
		launch();

		// StoneHandle.gameStart();
	}

}