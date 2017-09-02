package pl.com.bottega.photostock.sales.model;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    String mainCurrency;
    Map<String, Double> rates;

    public CurrencyConverter(String mainCurrency, Map<String, Double> rates) {
        this.mainCurrency = mainCurrency;
        this.rates = rates;

    }

    public Money convert(Money amount) {

        Boolean c = rates.containsKey(amount.currency());

        if (amount.currency().equals(mainCurrency)) {
            return amount;
        } if (c.equals(true)) {
            return amount.convert(mainCurrency, exchangeRate(amount.currency()));
        } else
            throw new IllegalArgumentException();
    }


    public Money convert(Money amount, String currency) {

        if (currency.equals(mainCurrency))
            return convert(amount);
        else if ((!currency.equals(mainCurrency)) && amount.currency().equals(mainCurrency))
            return amount.convert(currency, 1 / exchangeRate(currency));
        else {
            containsCurrency(currency);
            containsCurrency(amount.currency());
            return amount.convert(currency, exchangeRate(amount.currency()) / exchangeRate(currency));

        }
    }

    private double exchangeRate(String currency) {
        return rates.get(currency);
    }

    public boolean containsCurrency(String currency) {
        if (!rates.containsKey(currency))
            throw new IllegalArgumentException("Rate is not defined");
        return true;
    }


    }





