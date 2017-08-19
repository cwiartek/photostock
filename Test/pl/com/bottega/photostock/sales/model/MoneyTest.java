package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MoneyTest {

    private Money FiftyCredit = Money.valueOf(50);
    private Money SeventyCredit = Money.valueOf(70);
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
        Money m1 = Money.valueOf(50, "USD");
        Money m2 = Money.valueOf(70, "EUR");


        //when
        Money m1Plusm2 = m1.add(m2);

    }

    @Test
    public void shouldSubstractMoney() {

        //when
        Money m1Substract2 = FiftyCredit.substract(SeventyCredit);

        //then
        Money expected = Money.valueOf(-20);
        assertEquals(expected, m1Substract2);

    }



}
