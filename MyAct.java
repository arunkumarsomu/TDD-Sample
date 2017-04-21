

public class MyAct implements AccountInterface {
	private int balance;
	private int actnum;
	private AccountStatus status;
	
	public MyAct() {
		this.actnum = -1;
	}
	
	public MyAct(int a) {
		this.actnum = a;
	}
	public void setBalance(BankDB b) {
		this.balance = b.getBalance(this.actnum);
		if (this.balance == -1) throw new BankException();
		System.out.println("Balance is "+ this.balance);
	}
	
	public void setStatus(BankDB b) {
		this.status = b.getStatus();
		System.out.println("Status is "+ this.status);
	}
	
	
	@Override
	public int getBalance() {
		return balance;
	}

	@Override
	public AccountStatus getStatus() {
		return status;
//		return null;
	}

	@Override
	public boolean deposit(int amt) {
		if ( this.status != AccountStatus.OPEN ) return false;
		
		balance += amt;
		return true;
	}

	@Override
	public boolean withdraw(int amt) {
		// TODO Auto-generated method stub
		return false;
	}

}
