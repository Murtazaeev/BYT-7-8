package B_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		Assert.assertEquals("Test get amount regular", new Integer(1000), EUR10.getAmount());
		Assert.assertEquals("Test get amount zero", new Integer(0), EUR0.getAmount());
	}

	@Test
	public void testGetCurrency() {
		Assert.assertEquals("test get currency", EUR, EUR10.getCurrency());
	}

	@Test
	public void testToString() {
		Assert.assertEquals("test toString zero", "0 EUR", EUR0.toString());
		Assert.assertEquals("test toString", "10 EUR", EUR10.toString());
	}

	@Test
	public void testGlobalValue() {
		Assert.assertEquals("test UniversalValue SEK", new Integer(1500), SEK100.universalValue());
		Assert.assertEquals("test UniversalValue EUR", new Integer(3000), EUR20.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		Assert.assertEquals("test equals true", true, EUR10.equals(SEK100));
		Assert.assertEquals("test equals false", false, EUR20.equals(SEK100));
	}

	@Test
	public void testAdd() {
		Assert.assertEquals("test add SEK + SEK", "200 SEK", SEK100.add(SEK100).toString());
		Assert.assertEquals("test add SEK + EUR", "200 SEK", SEK100.add(EUR10).toString());
	}

	@Test
	public void testSub() {
		Assert.assertEquals("test sub negative", "-100 SEK", SEK100.sub(SEK200).toString());
		Assert.assertEquals("test sub zero", "0 SEK", SEK100.sub(SEK100).toString());
	}

	@Test
	public void testIsZero() {
		Assert.assertEquals("test is zero false", false, SEK100.isZero());
		Assert.assertEquals("test is zero true", true, SEK0.isZero());
	}

	@Test
	public void testNegate() {
		Assert.assertEquals("test negate", "-100 SEK", SEK100.negate().toString());
	}

	@Test
	public void testCompareTo() {
		Assert.assertEquals("test compare eq", 0, SEK100.compareTo(SEK100));
		Assert.assertEquals("test compare less", -1, SEK100.compareTo(EUR20));
		Assert.assertEquals("test comapre more", +1, SEK200.compareTo(SEK0));
	}
}
