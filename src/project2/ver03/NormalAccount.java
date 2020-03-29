package project2.ver03;

public class NormalAccount extends Account {

	int interest;

	public NormalAccount(String accNum, String name, int balance, int interest) {
		super(accNum, name, balance);
		this.interest = interest;
	}
	
	@Override
	public void showInfo() {
		System.out.println("계좌번호>" + accNum);
		System.out.println("고객이름>" + name);
		System.out.println("잔고>" + balance);
		System.out.println("기본이자>" + interest +"%");
	}
	
	@Override
	public void deposit(int money) {
		balance = balance + (balance*interest/100)+money;
	}
	
}
