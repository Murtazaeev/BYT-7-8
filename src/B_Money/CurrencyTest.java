package B_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("Test Name 1", "SEK", SEK.getName());
		Assert.assertEquals("Test Name 2", "DKK", DKK.getName());
		Assert.assertEquals("Test Name 3", "EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		Assert.assertEquals("Test rate 1", new Double(0.15), SEK.getRate());
		Assert.assertEquals("Test rate 2", new Double(0.20), DKK.getRate());
		Assert.assertEquals("Test rate 3", new Double(1.5), EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.50);
		Assert.assertEquals("Test set rate 1", new Double(0.50), SEK.getRate());
	}
	
	@Test
	public void testGlobalValue() {
		Assert.assertEquals("Test universal 1", new Integer(15), SEK.universalValue(100));
		Assert.assertEquals("Test universal 2", new Integer(150), EUR.universalValue(100));
		Assert.assertEquals("Test universal 3", new Integer(20), DKK.universalValue(100));
	}
	
	@Test
	public void testValueInThisCurrency() {
		Assert.assertEquals("test rate 1", new Integer(10), SEK.valueInThisCurrency(100, EUR));
		Assert.assertEquals("test rate 1", new Integer(10), SEK.valueInThisCurrency(100, EUR));
	}

}
