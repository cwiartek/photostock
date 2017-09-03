package pl.com.bottega.photostock.sales.model;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public abstract class Client {

    private String name;
    private Address address;
    private ClientStatus status;
    public List<Transaction> transactions = new LinkedList<>();
    private String number;

    public Client(String name, Address address, ClientStatus status, Money balance) {
        this.name = name;
        this.address = address;
        this.status = status;
        this.number = UUID.randomUUID().toString();
        if (balance.gt(Money.ZERO))
        transactions.add(new Transaction(balance,"First charge"));

    }

    public Client(String name, Address address) {
        this(name, address, ClientStatus.STANDARD,Money.ZERO);

    }


    public abstract boolean canAfford(Money amount);



    public void charge(Money amount, String reason) {
        if (!canAfford(amount))
            throw new IllegalStateException("Not enough balance");
          transactions.add(new Transaction(amount.neg(),reason));
    }
    public void recharge(Money amount) {
        transactions.add( new Transaction(amount, "Recharge account"));

    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public int discountPercent() {
        return status.discountPercent();
    }


    public Money balance() {

        Money sum = Money.ZERO;

        for(Transaction tx : transactions)

            sum = sum.add(tx.getAmount());

        return sum;


    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }
}
