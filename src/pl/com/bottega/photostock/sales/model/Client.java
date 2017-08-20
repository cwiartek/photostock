package pl.com.bottega.photostock.sales.model;

import java.util.LinkedList;
import java.util.List;

public class Client {

    private String name;
    private Adress adress;
    private ClientStatus status;
    private Money balance;
    private Money creditLimit;
    private List<Transaction> transactions = new LinkedList<>();

    public Client(String name, Adress adress, ClientStatus status, Money balance, Money creditLimit) {
        this.name = name;
        this.adress = adress;
        this.status = status;
        this.balance = balance;
        this.creditLimit = creditLimit;
        if (balance.gt(Money.ZERO))
        transactions.add(new Transaction(balance,"First charge"));

    }

    public Client(String name, Adress adress) {
        this(name, adress, ClientStatus.STANDARD, Money.ZERO, Money.ZERO);

    }

    public boolean canAfford(Money amount) {

        return amount.lte(balance.add(creditLimit));
    }

    public void charge(Money amount, String reason) {
        if (!canAfford(amount))
            throw new IllegalStateException("Not enough balance");
        balance = balance.substract(amount);
        transactions.add(new Transaction(amount.neg(),reason));

    }
    public void recharge(Money amount) {
        balance = balance.add(amount);
        transactions.add( new Transaction(amount, "Recharge account"));

    }
}
