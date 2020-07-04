package tdd.part1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MoneyTest {

  @Test
  public void testCurrency() {
    assertEquals("USD", Money.dollar(1).currency());
    assertEquals("CHF", Money.franc(1).currency());
  }

  @Test
  public void testMultiplication() {
    Money five = Money.dollar(5);
    assertEquals(Money.dollar(10), five.times(2));
    assertEquals(Money.dollar(15), five.times(3));
  }

  @Test
  public void testEquality() {
    assertTrue(Money.dollar(3).equals(Money.dollar(3)));
    assertFalse(Money.dollar(5).equals(Money.dollar(3)));
    assertTrue(Money.franc(3).equals(Money.franc(3)));
    assertFalse(Money.franc(5).equals(Money.franc(3)));
    assertFalse(Money.dollar(5).equals(Money.franc(5)));
  }

  @Test
  public void testSimpleAddition() {
    Money fiveDollar = Money.dollar(5);
    Expression sum = fiveDollar.plus(fiveDollar);
    Bank bank = new Bank();
    Money reduced = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(10), reduced);
  }

  @Test
  public void testPlusReturnsSum() {
    Money fiveDollar = Money.dollar(5);
    Expression result = fiveDollar.plus(fiveDollar);
    Sum sum = (Sum) result;
    assertEquals(fiveDollar, sum.augend);
    assertEquals(fiveDollar, sum.addend);
  }

  @Test
  public void testReduceSum() {
    Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
    Bank bank = new Bank();
    Money result = bank.reduce(sum, "USD");
    assertEquals(Money.dollar(7), result);
  }

  @Test
  public void testReduceMoney() {
    Bank bank = new Bank();
    Money reducedMoney = bank.reduce(Money.dollar(1), "USD");
    assertEquals(Money.dollar(1), reducedMoney);
  }
}
