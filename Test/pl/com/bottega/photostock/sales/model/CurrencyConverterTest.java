package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CurrencyConverterTest {

    @Test
            public void ShouldConvertToPLN() {

        Map<String, Double> rates = new HashMap<>();

        rates.put("USD", 3.7020);
        rates.put("EUR", 4.0345);
        rates.put("GBP", 4.6400);
        rates.put("CHF", 3.7326);

        CurrencyConverter c = new CurrencyConverter("PLN", rates);

        assertEquals(Money.valueOf(3.70, "PLN"), c.convert(Money.valueOf(1, "USD")));
        assertEquals(Money.valueOf(4.03, "PLN"), c.convert(Money.valueOf(1, "EUR")));
        assertEquals(Money.valueOf(4.64, "PLN"), c.convert(Money.valueOf(1, "GBP")));
        assertEquals(Money.valueOf(3.73, "PLN"), c.convert(Money.valueOf(1, "CHF")));
    }

    @Test(expected = IllegalArgumentException.class)
    public void ShouldThrowExceptionWhenRateIsNotDefined() {
        Map<String, Double> rates = new HashMap<>();

        rates.put("USD", 3.7020);
        rates.put("EUR", 4.0345);
        rates.put("GBP", 4.6400);
        rates.put("CHF", 3.7326);

        CurrencyConverter c = new CurrencyConverter("PLN", rates);

        c.convert(Money.valueOf(100, "E"));
        c.convert(Money.valueOf(100, "US"));
    }

    @Test
    public void shouldConvertMoneyAmount(){
        Map<String, Double> rates = new HashMap<>();

        rates.put("USD", 3.7020);
        rates.put("EUR", 4.0345);
        rates.put("GBP", 4.6400);
        rates.put("CHF", 3.7326);

        CurrencyConverter c = new CurrencyConverter("PLN", rates);

        assertEquals(c.convert(Money.valueOf(10, "PLN") , "PLN"), Money.valueOf(10, "PLN"));
        assertEquals(c.convert(Money.valueOf(2, "PLN") , "USD"), Money.valueOf(2 / 3.7020, "USD"));
        assertEquals(c.convert(Money.valueOf(1, "EUR") , "USD"), Money.valueOf(1 * 4.0345 / 3.7020, "USD"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAnyRateIsNotDefined() {
        Map<String, Double> rates = new HashMap<>();

        rates.put("USD", 3.7020);
        rates.put("EUR", 4.0345);
        rates.put("GBP", 4.6400);
        rates.put("CHF", 3.7326);

        CurrencyConverter c = new CurrencyConverter("PLN", rates);

        assertEquals( c.convert(Money.valueOf(1, "E"), "USD"), Money.valueOf(1 * 4.0345 / 3.7020, "PLN"));
        assertEquals( c.convert(Money.valueOf(1, "EUR"), "D"), Money.valueOf(1 * 4.0345 / 3.7020, "PLN"));
        assertEquals( c.convert(Money.valueOf(1, "ER"), "D"), Money.valueOf(1 * 4.0345 / 3.7020, "PLN"));


    }
}
