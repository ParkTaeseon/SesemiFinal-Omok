package com.omok.game;

import java.util.Scanner;

public class StoneHandle {

	static final int[] UP = { -1, 0 };
	static final int[] RIGHT_UP = { -1, 1 };
	static final int[] RIGHT = { 0, 1 };
	static final int[] RIGHT_DOWN = { 1, 1 };
	static final int[] DOWN = { 1, 0 };
	static final int[] LEFT_DOWN = { 1, -1 };
	static final int[] LEFT = { 0, -1 };
	static final int[] LEFT_UP = { -1, -1 };
	static boolean pointCheck = true;	// Ž���Ҷ� ������ ���ϴ��� �ľ��ϴ� ����
	static boolean winlose = true;
	public static void main(String[] args) {

		int[][] board = new int[19][19]; // �ٵ��� ����  

		int[] point = new int[2];
		int[] checkPoint = new int[2];
		int[] cnt = { 1 };// �� ī��Ʈ

		while (winlose) {
			stone(point, board, cnt);
			compareStone(point, checkPoint, board);
		}
		if(turn(cnt) == 1)
			System.out.println("�鵹 �¸�");
		else
			System.out.println("�浹 �¸�");
		
	}

	public static void printGame(int[][] arr) { // �ٵ��� ���
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void stone(int[] point, int[][] board, int[] cnt) { // ��ǥ���Է¹޾Ƶ��� �����ִ¸޼ҵ�
		Scanner input = new Scanner(System.in);
		point[0] = input.nextInt();
		point[1] = input.nextInt();

		if (isCheck(point, board) == 0 && turn(cnt) == 1)
			board[point[0]][point[1]] = 1;
		else if (turn(cnt) == 0)
			board[point[0]][point[1]] = 2;
		// else
		printGame(board);
		cnt[0]++; // �� ����
		
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

	public static int turn(int[] cnt) { // ¦������ Ȧ������ ����

		if (cnt[0] % 2 == 0)
			return cnt[0] % 2; // �� ����
		else
			return cnt[0] % 2; // �� ����
	}
	public static void clonePoint(int[] point, int[] checkPoint, int[][] board) {	// ����Ʈ ��ǥ�� üũ ����Ʈ�� ����

		for (int i = 0; i < 2; i++) {
			checkPoint[i] = point[i];
		}
	}

	public static void compareUpDown(int[] point, int[] checkPoint, int[][] board) {

		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointUP(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ���� ���� ������ üũ

		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointDOWN(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �Ʒ��� ���� ������ üũ
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			winlose = false;
		else
			cnt = 1;
	}



	public static boolean pointUP(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += UP[i];
		}
		return true;
	}
	public static boolean pointDOWN(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 18)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += DOWN[i];
		}
		return true;
	}

	public static void compareRightLeft(int[] point, int[] checkPoint, int[][] board) {

		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointRight(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ��� ���� ������ üũ

		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointLeft(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �·� ���� ������ üũ
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			winlose = false;
		else
			cnt = 1;
	}


	public static boolean pointRight(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[1] == 18)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT[i];
		}
		return true;
	}
	public static boolean pointLeft(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[1] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT[i];
		}
		return true;
	}
	
	public static void compareDiagonalR(int[] point, int[] checkPoint, int[][] board) {

		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointRightUp(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ������ ���� ������ üũ

		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointLeftDown(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �¾Ʒ��� ���� ������ üũ
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			winlose = false;
		else
			cnt = 1;
	}


	public static boolean pointRightUp(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 0||checkPoint[1] == 18){
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT_UP[i];
		}
		return true;
	}
	public static boolean pointLeftDown(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 18||checkPoint[1] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT_DOWN[i];
		}
		return true;
	}
	
	public static void compareDiagonalL(int[] point, int[] checkPoint, int[][] board) {

		int cnt = 1;

		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointRightDown(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // ������ ���� ������ üũ

		pointCheck = true;
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		while(pointCheck){
			pointCheck = pointLeftUp(point, checkPoint, board);
			if (board[checkPoint[0]][checkPoint[1]] == board[point[0]][point[1]] && pointCheck) {
				cnt++;
			}
			else 
				break;
		} // �¾Ʒ��� ���� ������ üũ
		clonePoint(point, checkPoint, board);// üũ����Ʈ ����
		if (cnt == 5)
			winlose = false;
		else
			cnt = 1;
	}


	public static boolean pointRightDown(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 18||checkPoint[1] == 18){
			return false;
		}
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += RIGHT_DOWN[i];
		}
		return true;
	}
	public static boolean pointLeftUp(int[] point, int[] checkPoint, int[][] board) {

		if(checkPoint[0] == 0||checkPoint[1] == 0)
			return false;
		for (int i = 0; i < 2; i++) {
			checkPoint[i] += LEFT_UP[i];
		}
		return true;
	}
	public static void compareStone(int[] point, int[] checkPoint, int[][] board) {
		compareUpDown(point, checkPoint, board);
		compareRightLeft(point, checkPoint, board);
		compareDiagonalR(point, checkPoint, board);
		compareDiagonalL(point, checkPoint, board);
	}
}
