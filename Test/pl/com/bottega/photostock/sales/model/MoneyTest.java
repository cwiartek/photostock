package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoneyTest {

    private Money FiftyCredit = Money.valueOf(50);
    private Money SeventyCredit = Money.valueOf(70);
    private Money FiftyUSD = Money.valueOf(50, "USD");
    private Money FiftyEur = Money.valueOf(50, "EUR");


    @Test
    public void shouldAddMoney() {


        //when
        Money sum = FiftyCredit.add(SeventyCredit);

        //then
        Money expected = Money.valueOf(120);
        assertEquals(expected, sum);

    }

    @Test(expected = IllegalArgumentException.class )
    public void shouldAddNotMoneyAtDifferentCurrencies() {


        //when
        FiftyEur.add(SeventyCredit);

    }

    @Test
    public void shouldSubstractMoney() {

        //when
        Money dif = FiftyCredit.substract(SeventyCredit);

        //then
        Money expected = Money.valueOf(-20);
        assertEquals(expected, dif);

    }

    @Test

    public void shouldCompareMoney() {
        assertTrue(FiftyCredit.compareTo(SeventyCredit) <0 );
        assertTrue(SeventyCredit.compareTo(FiftyCredit) > 0);
        assertTrue(FiftyCredit.compareTo(FiftyCredit) == 0);

    }

    @Test

    public void shouldCompareMoneyUsingBooleanMethods() {

        assertTrue(FiftyCredit.lt(SeventyCredit));
        assertTrue(FiftyCredit.lte(SeventyCredit));
        assertTrue(SeventyCredit.gt(FiftyCredit));
        assertTrue(SeventyCredit.gte(FiftyCredit));
        assertFalse(FiftyCredit.gt(SeventyCredit));
        assertFalse(FiftyCredit.gte(SeventyCredit));
        assertFalse(SeventyCredit.lt(FiftyCredit));
        assertFalse(SeventyCredit.lte(FiftyCredit));
        assertTrue(FiftyCredit.gte(FiftyCredit));
        assertTrue(FiftyCredit.lte(FiftyCredit));
        assertTrue(FiftyCredit.lte(FiftyCredit));
        assertFalse(FiftyCredit.lt(FiftyCredit));
        assertFalse(FiftyCredit.gt(FiftyCredit));



    }

    @Test (expected = IllegalArgumentException.class)
    public void shouldCompareDiffrentCurrencies() {
        FiftyCredit.compareTo(FiftyEur);
    }

    @Test
    public void shouldCalculatePercent() {

        assertEquals(Money.valueOf(5), FiftyCredit.percent(10));
        assertEquals(Money.valueOf(5.50), FiftyCredit.percent(11));
        assertEquals(Money.valueOf(75),FiftyCredit.percent(150));
        assertEquals(Money.valueOf(0.01),Money.valueOf(0.11).percent(10));
        assertEquals(Money.valueOf(0.01),Money.valueOf(0.19).percent(10));
    }



}
