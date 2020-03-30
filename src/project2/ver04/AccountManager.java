package project2.ver04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class AccountManager {

	Scanner sc = new Scanner(System.in);
	HashSet<Account> acc = new HashSet<Account>(50);
	int stack = 0;

	public AccountManager() {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/project2/ver04/BankingBook.obj"));
			while (true) {
				Account inData = (Account) in.readObject();
				if (inData == null)
					break;
				acc.add(inData);
			}
			in.close();
		} catch (IOException e) {

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

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
					ObjectOutputStream out = new ObjectOutputStream(
							new FileOutputStream("src/project2/ver04/BankingBook.obj"));
					for (Account info : acc) {
						out.writeObject(info);
					}
					System.exit(0);
					;
				}
			} catch (InputMismatchException e) {
				System.out.println("숫자만 입력하세요");
				sc.nextLine();
			} catch (MenuSelectException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("저장 실패 . . .");
			}
		}
	}

	public void adding(Account ac) {
		if (ac instanceof NormalAccount) {
			ac = (NormalAccount) ac;
		}
		if (ac instanceof HighCreditAccount) {
			ac = (HighCreditAccount) ac;
		}
		if (!acc.add(ac)) {
			System.out.println("이름 중복 . . .");
			System.out.println("1.덮어 쓰기, 2.다시 입력");
			int check = sc.nextInt();
			sc.nextLine();
			if (check == 1) {
				acc.remove(ac);
				acc.add(ac);
				System.out.println("데이터 입력이 완료되었습니다.");
				return;
			} else if (check == 2) {
				return;
			}
		}
		System.out.println("데이터 입력이 완료되었습니다.");
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
			adding(new NormalAccount(accNum, name, balance, interest));
		} else if (choice == 2) {
			System.out.print("기본이자%(정수형태로입력):");
			interest = sc.nextInt();
			sc.nextLine();
			System.out.print("신용등급(A,B,C등급):");
			credit = sc.nextLine();
			adding(new HighCreditAccount(accNum, name, balance, interest, credit));

		}
		stack++;

	}

	public void depositMoney() {
		// 입 금
		try {
			int check = 0;
			System.out.println("***입금***");
			System.out.println("계좌번호와 입금할 금액을 입력하세요");
			System.out.print("계좌번호:");
			String accnum = sc.nextLine();
			System.out.print("입금액:");
			int money = sc.nextInt();
			sc.nextLine();
			if (money < 0) {
				System.out.println("음수를 입금할 수 없습니다.");
				return;
			}
			if (money % 500 != 0) {
				System.out.println("500원 단위로 입력하세요.");
				return;
			}
			Iterator<Account> itr = acc.iterator();

			while (itr.hasNext()) {
				Account ac = itr.next();
				if (ac.accNum.contains(accnum)) {
					ac.deposit(money);
					check = 1;
				}
			}

			if (check == 0) {
				NullPointerException ex = new NullPointerException();
				throw ex;
			}
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력하세요");
			sc.nextLine();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("찾는 데이터가 없습니다.");
		}
	}

	public void withdrawMoney() {
		// 출 금
		try {
			int check = 0;
			System.out.println("***출금***");
			System.out.println("계좌번호와 출금할 금액을 입력하세요");
			System.out.print("계좌번호:");
			String accnum = sc.nextLine();
			System.out.print("출금액:");
			int money = sc.nextInt();
			sc.nextLine();
			if (money < 0) {
				System.out.println("음수를 출금할 수 없습니다.");
				return;
			}
			if (money % 1000 != 0) {
				System.out.println("1000원 단위로 입력하세요.");
				return;
			}
			Iterator<Account> itr = acc.iterator();

			while (itr.hasNext()) {
				Account ac = itr.next();
				if (ac.accNum.contains(accnum)) {
					if (ac.balance < money) {
						System.out.println("잔고가 부족합니다. 금액전체를 출금할까요?");
						System.out.println("YES : 금액전체 출금처리");
						System.out.println("NO : 출금요청취소");
						System.out.print("선택:");
						String yesNo = sc.nextLine();
						if (yesNo.equals("YES")) {
							System.out.println(ac.balance + "원 출금하겠습니다.");
							ac.balance = 0;
						} else if (yesNo.equals("NO")) {
							System.out.println("취소됐습니다.");
							return;
						} else {
							System.out.println("똑바로 입력하세요");
							return;
						}
					}
					ac.balance -= money;
					System.out.println("출금완료");
					check = 1;
				}
			}

			if (check == 0) {
				NullPointerException ex = new NullPointerException();
				throw ex;
			}
		} catch (InputMismatchException e) {
			System.out.println("맞게 입력하세요");
			sc.nextLine();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("찾는 데이터가 없습니다.");
		}
	}

	public void showAccInfo() {
		// 전체계좌정보출력
		System.out.println("***계좌정보출력***");
		try {
			for (Account ac : acc) {
				ac.showInfo();
			}
			System.out.println("데이터 출력이 완료되었습니다.");
		} catch (NullPointerException e) {
			System.out.println("데이터가 없습니다.");
		}

	}

}
