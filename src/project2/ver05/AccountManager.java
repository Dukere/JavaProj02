package project2.ver05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AccountManager implements MenuChoice {

	Connection con;
	Statement stmt;
	PreparedStatement psmt;
	ResultSet rs;

	Scanner sc = new Scanner(System.in);

	public AccountManager() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin://@localhost:1521:orcl";
			String userid = "KOSMO";
			String userpw = "1234";
			con = DriverManager.getConnection(url, userid, userpw);

			if (con != null) {
				System.out.println("Oracle DB 연결성공");
			} else {
				System.out.println("연결실패ㅠㅠ");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showMenu() {
		// 메뉴출력
		while (true) {
			System.out.println("☆덕래은행★");
			System.out.println("1. 계좌개설");
			System.out.println("2. 입금");
			System.out.println("3. 출금");
			System.out.println("4. 전체계좌정보출력");
			System.out.println("5. 검색");
			System.out.println("6. 3X3 퍼즐");
			System.out.println("7. 프로그램종료");
			System.out.print("선택:");

			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case MAKE:
				makeAccount();
				break;
			case DEPOSIT:
				depositMoney();
				break;
			case WITHDRAW:
				withdrawMoney();
				break;
			case INQUIRE:
				showAccInfo();
				break;
			case SEARCH:
				searchAcc();
				break;
			case GAME:
				threeByThree();
				break;
			case EXIT:
				System.out.println("시스템 종료");
				System.exit(0);
			}

		}
	}

	public void makeAccount() {
		// 계좌개설을 위한 함수
//		계좌개설 : insert문으로 구현한다.
//		PreparedStatement 클래스 이용

		String sql = "insert into banking_tb values(seq_phonebook.nextval, ?, ?, ?)";

		System.out.println("***신규계좌개설***");
		System.out.print("계좌번호 : ");
		String accNum = sc.nextLine();
		System.out.print("고객이름 : ");
		String name = sc.nextLine();
		System.out.print("잔고 : ");
		int balance = sc.nextInt();
		sc.nextLine();
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, accNum);
			psmt.setString(2, name);
			psmt.setInt(3, balance);
			psmt.executeUpdate();
			System.out.println("데이터 입력이 완료되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void depositMoney() {
		// 입 금
//		이미 생성된 계좌에서 금액이 변동되는 것이므로 update문으로 구현한다.
//		PreparedStatement 클래스 이용

		String sql = "update banking_tb set balance = (balance+?) where accNum =?";

		System.out.println("***입금***");
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accnum = sc.nextLine();
		System.out.print("입금액:");
		int balance = sc.nextInt();
		sc.nextLine();
		try {
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, balance);
			psmt.setString(2, accnum);

			psmt.executeUpdate();
			System.out.println("입금 완료");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void withdrawMoney() {
		// 출 금
//		이미 생성된 계좌에서 금액이 변동되는 것이므로 update문으로 구현한다.
//		PreparedStatement 클래스 이용

		String sql = "update banking_tb set balance = (balance-?) where accNum =?";

		System.out.println("***출금***");
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		System.out.print("계좌번호:");
		String accnum = sc.nextLine();
		System.out.print("출금액:");
		int balance = sc.nextInt();
		sc.nextLine();
		try {
			psmt = con.prepareStatement(sql);
			while (true) {
				psmt.setInt(1, balance);
				psmt.setString(2, accnum);

				psmt.executeUpdate();
				System.out.println("출금 완료");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showAccInfo() {
		// 전체계좌정보출력
//		select문과 like절을 이용하여 구현한다.
//		Statement  클래스 이용

		System.out.println("***계좌정보출력***");
		try {
			stmt = con.createStatement();
			String query = "SELECT accnum,name,balance FROM banking_tb ";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String accNum = rs.getString("accnum");
				String name = rs.getString("name");
				int balance = rs.getInt("balance");
				Account acc = new Account(accNum, name, balance);
				acc.showInfo();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void searchAcc() {
		// 전체계좌정보출력
//		select문과 like절을 이용하여 구현한다.
//		Statement  클래스 이용
		
		System.out.println("***계좌검색***");
		try {
			
			System.out.print("이름:");
			String scName = sc.nextLine();
			stmt = con.createStatement();
			String query = "SELECT accnum,name,balance FROM banking_tb where name like '%"+ scName +"%'";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String accNum = rs.getString("accnum");
				String name = rs.getString("name");
				int balance = rs.getInt("balance");
				Account acc = new Account(accNum, name, balance);
				acc.showInfo();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void threeByThree() {
		
		while(true) {
			puzzle pz = new puzzle();
			pz.game(30);
			System.out.println("재시작하시겠습니까?(y 누르면 재시작, 나머지는 종료)");
			System.out.print("입력:");
			String check = sc.nextLine();
			if(check.equals("y")){
				System.out.println("재시작하겠습니다.");
			}
			else {
				System.out.println("게임을 종료하겠습니다.");
				return;
			}
			}
	}

}
