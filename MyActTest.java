import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class MyActTest {

	
	@Injectable
	private BankDB testDB;

	@Tested
	private MyAct myAct;

	@Before
	public void setup() {
		this.testDB = new BankDB();
	}
	
	@Test
	public void testFindBalance(@Mocked BankDB testDB) {
		myAct = new MyAct(112);
		new Expectations () {
			{
				{
					testDB.getBalance(112); result = 100;
				}
			}
		};
		myAct.setBalance(testDB);
		assertEquals("Balance",100,myAct.getBalance());
		new Verifications () {
			{
				testDB.getBalance(112);
			}
		};
	}
	
	@Test
	public void testGetStatus(@Mocked BankDB testDB) {
		myAct = new MyAct(112);
		new Expectations () {
			{
				{
					testDB.getStatus(); result = AccountStatus.OPEN;
				}
			}
		};
		myAct.setStatus(testDB);
		assertEquals("Status is ",AccountStatus.OPEN, myAct.getStatus());
		new Verifications () {
			{
				testDB.getStatus();
			}
		};
	}
	
	@Test
	public void testDeposit(@Mocked BankDB testDB) {
		myAct = new MyAct(1010);
		new Expectations () {
			{
				{
					testDB.getBalance(1010); result = 100;
					testDB.getStatus(); result = AccountStatus.OPEN;
				}
			}
		};
		myAct.setBalance(testDB);
		myAct.setStatus(testDB);
		assertEquals("Balance",100,myAct.getBalance());
		
		assertEquals("Status is ",true, myAct.deposit(100));
		
		assertEquals("Balance",200,myAct.getBalance());
		
		new Verifications () {
			{
				testDB.getBalance(1010);
			}
		};
	}
	
	@Test
	public void testDepositForClosedOrInactive(@Mocked BankDB testDB) {
		myAct = new MyAct(1010);
		new Expectations () {
			{
				{
					testDB.getStatus(); result = AccountStatus.CLOSED;
				}
			}
		};
		myAct.setStatus(testDB);
		
		assertEquals("Status is ",false, myAct.deposit(100));
		
		
		new Verifications () {
			{
				testDB.getStatus();
			}
		};
	}
	
	
}
