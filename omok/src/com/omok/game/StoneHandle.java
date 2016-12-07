package com.omok.game;

import java.util.Scanner;

public class StoneHandle {
	
	static boolean winlose = false;	// ���и� �Ǵ��ϴ� ����
	
	static int[][] board = new int[19][19]; // �ٵ��� ����  

	static int[] point = new int[2];		// �ٵϵ��� ���� ��ǥ�� �����ϴ� �迭
	static int[] checkPoint = new int[2];	// �ֺ����� ��ǥ�� �־��ִ� �迭
	static int[] turnCnt = { 1 };// �� ī��Ʈ
	
	
	public static void gameStart() {

//		int[][] board = new int[19][19]; // �ٵ��� ����  
//
//		int[] point = new int[2];		// �ٵϵ��� ���� ��ǥ�� �����ϴ� �迭
//		int[] checkPoint = new int[2];	// �ֺ����� ��ǥ�� �־��ִ� �迭
//		int[] turnCnt = { 1 };// �� ī��Ʈ
//
//		WinAlg wa = new WinAlg(point, checkPoint, board);
//		
//		while (winlose==false) {
//			stone(point, board,  turnCnt);
//			winlose = wa.compareStone(point, checkPoint, board);
//		}
//		
//		if(turn(turnCnt) == 1)
//			System.out.println("�鵹 �¸�");
//		else
//			System.out.println("�浹 �¸�");
	}
	
	
	
	
	
	

	public static void printGame(int[][] arr) { // �ٵ��� ���
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
	public void stone(int[] point) { // ��ǥ���Է¹޾Ƶ��� �����ִ¸޼ҵ�
		stone(point, board,  turnCnt);
	}

	public static void stone(int[] point, int[][] board, int[] cnt) { // ��ǥ���Է¹޾Ƶ��� �����ִ¸޼ҵ�
		Scanner input = new Scanner(System.in);
//		point[0] = input.nextInt();
//		point[1] = input.nextInt();

		if (isCheck(point, board) == 0 && turn(cnt) == 1)
			board[point[0]][point[1]] = 1;
		else if (turn(cnt) == 0)
			board[point[0]][point[1]] = 2;
		// else
		printGame(board);
		
		cnt[0]++; // �� ����
		
		WinAlg wa = new WinAlg(point, checkPoint, board);
		
	
		winlose = wa.compareStone(point, checkPoint, board);
		
		if (winlose)
		 System.out.println("�¸�!!");
//		if(turn(turnCnt) == 1)
//			System.out.println("�鵹 �¸�");
//		else
//			System.out.println("�浹 �¸�");
		
	}

	public static int isCheck(int[] point, int[][] board) {

		switch (board[point[0]][point[1]]) {

		case 1:
			return 1; // �浹
		case 2:
			return 2; // �鵹

		default:
			return 0;
		}
	}

	public static int turn(int[] turnCnt) { // ¦������ Ȧ������ ����

		if (turnCnt[0] % 2 == 0)
			return turnCnt[0] % 2; // �� ����
		else
			return turnCnt[0] % 2; // �� ����
	}
}
