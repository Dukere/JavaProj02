package project2.ver03;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {

	Scanner sc = new Scanner(System.in);
	Account[] accArr = new Account[50];
	int stack = 0;

	public void showMenu() {
		// 메뉴출력
		while (true) {
			try {
				System.out.println("☆덕래은행★");
				System.out.println("1. 계좌개설");
				System.out.println("2. 입금");
				System.out.println("3. 출금");
				System.out.println("4. 전체계좌정보출력");
				System.out.println("5. 프로그램종료");
				System.out.print("선택:");

				int choice = sc.nextInt();
				sc.nextLine();
				if (choice < 1 || choice > 5) {
					MenuSelectException ex = new MenuSelectException();
					throw ex;
				}
				switch (choice) {
				case MenuChoice.MAKE:
					makeAccount();
					break;
				case MenuChoice.DEPOSIT:
					depositMoney();
					break;
				case MenuChoice.WITHDRAW:
					withdrawMoney();
					break;
				case MenuChoice.INQUIRE:
					showAccInfo();
					break;
				case MenuChoice.EXIT:
					System.out.println("시스템 종료");
					System.exit(0);
					;
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				sc.nextLine();
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void makeAccount() {
		// 계좌개설을 위한 함수
		int interest;
		String credit;

		System.out.println("***신규계좌개설***");
		System.out.println("-----계좌선택-----");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택:");
		int choice = sc.nextInt();
		sc.nextLine();
		System.out.print("계좌번호 : ");
		String accNum = sc.nextLine();
		System.out.print("고객이름 : ");
		String name = sc.nextLine();
		System.out.print("잔고 : ");
		int balance = sc.nextInt();
		sc.nextLine();
		if (choice == 1) {
			System.out.print("기본이자%(정수형태로입력):");
			interest = sc.nextInt();
			sc.nextLine();
			accArr[stack] = new NormalAccount(accNum, name, balance, interest);
		} else if (choice == 2) {
			System.out.print("기본이자%(정수형태로입력):");
			interest = sc.nextInt();
			sc.nextLine();
			System.out.print("신용등급(A,B,C등급):");
			credit = sc.nextLine();
			accArr[stack] = new HighCreditAccount(accNum, name, balance, interest, credit);

		}
		stack++;

	}

	public void depositMoney() {
		// 입 금
		try {
		System.out.println("***입금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accnum = sc.nextLine();
		System.out.print("입금액:");
		int balance = sc.nextInt();
		sc.nextLine();
		if(balance<0) {
			System.out.println("음수를 입금할 수 없습니다.");
			return;
		}
		if(balance%500!=0) {
			System.out.println("500원 단위로 입력하세요.");
			return;
		}
		for (int i = 0; i < stack; i++) {
			if (accArr[i].accNum.equals(accnum)) {
				accArr[i].deposit(balance);
				System.out.println("입금완료");
			}
		}
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력하세요");
			sc.nextLine();
		} 
	}

	public void withdrawMoney() {
		// 출 금
		try {
		System.out.println("***출금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accnum = sc.nextLine();
		System.out.print("출금액:");
		int balance = sc.nextInt();
		sc.nextLine();
		if (balance<0) {
			System.out.println("음수를 출금할 수 없습니다.");
			return;
		}
		if(balance%1000!=0) {
			System.out.println("1000원 단위로 입력하세요.");
			return;
		}
		for (int i = 0; i < stack; i++) {
			if (accArr[i].accNum.equals(accnum)) {
				if(accArr[i].balance<balance) {
					System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
					System.out.println("YES : 금액전체 출금처리");
					System.out.println("NO : 출금요청취소");
					System.out.print("선택:");
					String check = sc.nextLine();
					if(check.equals("YES")) {
						System.out.println(accArr[i].balance+"원 출금하겠습니다.");
						accArr[i].balance = 0;
					} else if (check.equals("NO")) {
						System.out.println("취소됐습니다.");
						return;
					} else {
						System.out.println("똑바로 입력하세요");
					}
				}
				accArr[i].balance -= balance;
				System.out.println("출금완료");
			}
		}
		} catch (InputMismatchException e) {
			System.out.println("맞게 입력하세요");
			sc.nextLine();
		}
	}

	public void showAccInfo() {
		// 전체계좌정보출력
		System.out.println("***계좌정보출력***");
		if (stack == 0) {
			System.out.println("고객이 없습니다.");
			return;
		}
		System.out.println("--------------");
		for (int i = 0; i < stack; i++) {
			accArr[i].showInfo();
			System.out.println("--------------");
		}

	}

}
