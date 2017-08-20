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
        Money m1Plusm2 = FiftyCredit.add(SeventyCredit);

        //then
        Money expected = Money.valueOf(120);
        assertEquals(expected, m1Plusm2);

    }

    @Test(expected = IllegalArgumentException.class )
    public void shouldAddNotMoneyAtDifferentCurrencies() {

        //given
        Money m1 = FiftyUSD; //Money.valueOf(50, "USD");
        Money m2 = FiftyEur;// Money.valueOf(70, "EUR");


        //when
        Money m1Plusm2 = FiftyUSD.add(FiftyEur);

    }

    @Test
    public void shouldSubstractMoney() {

        //when
        Money m1Substract2 = FiftyCredit.substract(SeventyCredit);

        //then
        Money expected = Money.valueOf(-20);
        assertEquals(expected, m1Substract2);

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



}
