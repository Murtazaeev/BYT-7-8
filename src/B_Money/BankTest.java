package B_Money;

import static org.junit.Assert.*;

import B_Money.AccountDoesNotExistException;
import B_Money.AccountExistsException;
import B_Money.Bank;
import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception, AccountExistsException {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Thomas");
		SweBank.openAccount("Alfie");
		Nordea.openAccount("John");
		DanskeBank.openAccount("Micheal");
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("TODO", "SweBank",  SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		Assert.assertEquals("TODO", "SEK" ,  SweBank.getCurrency().getName());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		DanskeBank.openAccount("Temur");
		Assert.assertEquals("TODO", "Temur" ,  DanskeBank.getAccountFromAccountlist("Temur").getAccountName());
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Thomas", new Money(10000, SEK));
		SweBank.deposit("Thomas", new Money(10000, SEK));
		Assert.assertEquals("TODO", new Integer(20000) ,  SweBank.getBalance("Thomas"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.deposit("Thomas", new Money(10000, SEK));
		SweBank.withdraw("Thomas", new Money(10000, SEK));
		Assert.assertEquals("TODO", new Integer(0) ,  SweBank.getBalance("Thomas"));
		SweBank.withdraw("Thomas", new Money(20000, SEK));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		Assert.assertEquals("TODO", new Integer(0) ,  SweBank.getBalance("Thomas"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Thomas", new Money(10000, SEK));
		SweBank.transfer("Thomas", "Bob", new Money(10000, SEK));
		Assert.assertEquals("Transfer fromAccount balance", new Integer(0) ,  SweBank.getBalance("Thomas"));
		Assert.assertEquals("Transfer toAccount balance", new Integer(10000) ,  SweBank.getBalance("Alfie"));
		SweBank.transfer("Thomas", "Alfie", new Money(10000, SEK));
		Assert.assertEquals("Transfer fromAccount balance", new Integer(0) ,  SweBank.getBalance("Thomas"));
		Assert.assertEquals("Transfer toAccount balance", new Integer(10000) ,  SweBank.getBalance("Alfie"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {

		SweBank.addTimedPayment("Thomas", "1", new Integer(10), new Integer(5), new Money(100, SEK), SweBank, "Alfie");
		Assert.assertEquals("TODO", true ,  SweBank.getAccountFromAccountlist("Thomas").timedPaymentExists("1"));
		SweBank.removeTimedPayment("Thomas", "1");
		Assert.assertEquals("TODO", false ,  SweBank.getAccountFromAccountlist("Thomas").timedPaymentExists("1"));

		SweBank.deposit("Thomas", new Money(1000, SEK));
		SweBank.addTimedPayment("Thomas", "1", new Integer(0), new Integer(0), new Money(100, SEK), SweBank, "Alfie");
		SweBank.tick();
		SweBank.tick();
		Assert.assertEquals("Timed payment bank", new Integer(800), SweBank.getBalance("Thomas"));
	}
}
