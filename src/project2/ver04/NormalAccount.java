package project2.ver04;

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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public void deposit(int money) {
		balance = balance + (balance*interest/100)+money;
	}
	
}
