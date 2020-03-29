package project2.ver03;

public class HighCreditAccount extends Account{
	
	int interest;
	String credit;
	
	public HighCreditAccount(String accNum, String name, int balance, int interest, String credit) {
		super(accNum, name, balance);
		this.interest = interest;
		this.credit = credit;
	}
	
	@Override
	public void showInfo() {
		System.out.println("계좌번호>" + accNum);
		System.out.println("고객이름>" + name);
		System.out.println("잔고>" + balance);
		System.out.println("기본이자>" + interest +"%");
		System.out.println("신용등급>" + credit);
	}
	
	@Override
	public void deposit(int money) {
		int credit=0;
		switch(this.credit) {
		case "A":
			credit = CustomSpecialRate.A;
			break;
		case "B":
			credit = CustomSpecialRate.B;
			break;
		case "C":
			credit = CustomSpecialRate.C;
			break;
		}
		balance = balance + (balance*interest/100)
				+(balance*credit/100)+money;
	}
	

}