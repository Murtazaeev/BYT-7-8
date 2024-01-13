package B_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    Currency SEK, DKK;
    Bank Nordea;
    Bank DanskeBank;
    Bank SweBank;
    Account testAccount;

    @Before
    public void setUp() throws Exception, AccountExistsException, AccountDoesNotExistException {
        SEK = new Currency("SEK", 0.15);
        SweBank = new Bank("SweBank", SEK);
        SweBank.openAccount("Bobur");
        testAccount = new Account("Aziz", SEK);
        testAccount.deposit(new Money(10000000, SEK));

        SweBank.deposit("Alice", new Money(1000000, SEK));
    }

    @Test
    public void testAddRemoveTimedPayment() {
        testAccount.addTimedPayment("1", new Integer(10), new Integer(10), new Money(100, SEK), SweBank, "Bobur");
        testAccount.addTimedPayment("1", new Integer(10), new Integer(10), new Money(100, SEK), SweBank, "Bobur");
        Assert.assertEquals("test added payment exists", true, testAccount.timedPaymentExists("1"));
        testAccount.removeTimedPayment("1");
        Assert.assertEquals("test removed payment don't exist", false, testAccount.timedPaymentExists("1"));
        testAccount.removeTimedPayment("1");
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        ftestAccount.addTimedPayment("1", new Integer(2), new Integer(0), new Money(10000, SEK), SweBank, "Bobur");
        for (int i = 0; i < 10; i++) {
            testAccount.tick();
        }
        Assert.assertEquals("timed payment account", new Integer(9960000), testAccount.getBalance());

    }

    @Test
    public void testAddWithdraw() {
        testAccount.withdraw(new Money(5000000, SEK));
        Assert.assertEquals("test withdraw", new Integer(5000000), testAccount.getBalance());
        testAccount.withdraw(new Money(990000000, SEK));
        Assert.assertEquals("test withdraw", new Integer(5000000), testAccount.getBalance());
    }

    @Test
    public void testGetBalance() {
        Assert.assertEquals("TODO", new Integer(10000000), testAccount.getBalance());
    }
}
