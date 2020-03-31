package project2.ver04;

import java.io.Serializable;

public class Account implements Serializable {
	
	String accNum;
	String name;
	int balance;
	
	public Account(String accNum, String name, int balance) {
		this.accNum = accNum;
		this.name = name;
		this.balance = balance;
	}
	
	public void showInfo() {
		System.out.println("계좌번호" + accNum);
		System.out.println("고객이름" + name);
		System.out.println("잔고" + balance);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accNum == null) ? 0 : accNum.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		Account compareAccount = (Account) obj;
		if (compareAccount.accNum.equals(this.accNum)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void deposit(int money) {
	}
	
}

