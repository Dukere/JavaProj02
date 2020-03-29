package project2.ver01;

import java.util.Scanner;

public class Account {
	
	String accNum;
	String name;
	int balance;
	
	Account[] accArr;
	int stack;
	
	Scanner sc = new Scanner(System.in);
	
	
	public Account(String accNum, String name, int balance) {
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}
	public Account(int num) {
		
		accArr = new Account[num];
		stack = 0;
		
	}
	
	public void showInfo() {
		System.out.println("계좌번호" + accNum);
		System.out.println("고객이름" + name);
		System.out.println("잔고" + balance);
	}
	

	public void showMenu() {
		// 메뉴출력
		while(true) {
		System.out.println("☆덕래은행★");
		System.out.println("1. 계좌개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 전체계좌정보출력");
		System.out.println("5. 프로그램종료");
		System.out.print("선택:");
		
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice) {
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
			System.exit(0);;
		}
		
		}
	}
	
	public void makeAccount() {
		// 계좌개설을 위한 함수

		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호 : ");
		String accNum = sc.nextLine();
		System.out.print("고객이름 : ");
		String name = sc.nextLine();
		System.out.print("잔고 : ");
		int balance = sc.nextInt();
		sc.nextLine();
		accArr[stack] = new Account(accNum, name, balance);
		stack++;
		
	}

	public void depositMoney() {
		// 입 금
		System.out.println("***입금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accnum = sc.nextLine();
		System.out.print("입금액:");
		int balance = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i<stack; i++) {
			if(accArr[i].accNum.equals(accnum)) {
				accArr[i].balance += balance; 
				System.out.println("입금완료");
			}
		}	
	}

	public void withdrawMoney() {
		// 출 금
		System.out.println("***출금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accnum = sc.nextLine();
		System.out.print("출금액:");
		int balance = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i<stack; i++) {
			if(accArr[i].accNum.equals(accnum)) {
				accArr[i].balance -= balance; 
				System.out.println("출금완료");
			}
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
		for(int i = 0; i<stack; i++) {
			accArr[i].showInfo();
			System.out.println("--------------");
		}
		
		
	}
	
	
}

