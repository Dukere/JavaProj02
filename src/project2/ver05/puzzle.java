package project2.ver05;

import java.util.Arrays;
import java.util.Scanner;

interface button {
	String ONE = "1", TWO = "2", THREE = "3", FOUR = "4", FIVE = "5", SIX = "6", SEVEN = "7", EIGHT = "8", X = "X";
}

public class puzzle implements button {
	
	boolean exit;

	Scanner sc = new Scanner(System.in);

	String[][] arr = { { ONE, TWO, THREE }, { FOUR, FIVE, SIX }, { SEVEN, EIGHT, X } };
	String[][] answer = { { ONE, TWO, THREE }, { FOUR, FIVE, SIX }, { SEVEN, EIGHT, X } };
	int x = 2, y = 2; // X추적용
	String temp; // 저장용

	public void game(int num) {
		mix(num);
		exit = false;
		while (!check()) {
			show();
			System.out.println("[이동] a:Left d:Right w:Up s:Down");
			System.out.println("[종료] x:Exit");
			System.out.print("키를 입력해주세요 :");
			String key = sc.nextLine();
			switch (key) {
			case "a":
				left();
				break;
			case "d":
				right();
				break;
			case "w":
				up();
				break;
			case "s":
				down();
				break;
			case "x":
				System.out.println("종료하겠습니다.");
				exit = true;
				return;
			default:
				System.out.println("옳바르게 입력해주세요 ..");
				break;
			}
		}
		System.out.println("==^^정답입니다^^==");
	}

	public void mix(int num) {
		int check = 0;
		while (check < num) {
			int random = (int) (Math.random() * 4);
			switch (random) {
			case 0:
				if (x == 0) {
					up();
					break;
				}
				arr[x][y] = arr[--x][y];
				arr[x][y] = X;
				break;
			case 1:
				if (x == 2) {
					down();
					break;
				}
				arr[x][y] = arr[++x][y];
				arr[x][y] = X;
				break;
			case 2:
				if (y == 0) {
					left();
					break;
				}
				arr[x][y] = arr[x][--y];
				arr[x][y] = X;
				break;
			case 3:
				if (y == 2) {
					right();
					break;
				}
				arr[x][y] = arr[x][++y];
				arr[x][y] = X;
				break;
			}
			check++;
		}

	}

	public void down() {
		if (x == 0) {
			System.out.println(error());
			return;
		}
		arr[x][y] = arr[--x][y];
		arr[x][y] = X;
	}

	public void up() {
		if (x == 2) {
			System.out.println(error());
			return;
		}
		arr[x][y] = arr[++x][y];
		arr[x][y] = X;
	}

	public void right() {
		if (y == 0) {
			System.out.println(error());
			return;
		}
		arr[x][y] = arr[x][--y];
		arr[x][y] = X;
	}

	public void left() {
		if (y == 2) {
			System.out.println(error());
			return;
		}
		arr[x][y] = arr[x][++y];
		arr[x][y] = X;
	}

	public String error() {
		return "xxxxxxxxxxxxxxxxxxxxxx\n" + "xxxxxxxx이동불가xxxxxxxxx\n" + "xxxxxxxxxxxxxxxxxxxxxx\n";
	}

	public void show() {
		System.out.println("=====");
		for (int n = 0; n < arr.length; n++) { // 배열의 세로길이
			for (int m = 0; m < arr[n].length; m++) { // 가로길이
				System.out.print(arr[n][m] + " ");
			}
			System.out.println();
		}
		System.out.println("=====");
	}

	public boolean check() {
		return Arrays.deepEquals(arr, answer);
	}

}
