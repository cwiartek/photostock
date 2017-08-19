package pl.com.bottega.photostock.sales.model;

public class Client {

    private String name;
    private Adress adress;
    private ClientStatus status;
    private Money balance;
    private Money creditLimit;

    public Client(String name, Adress adress, ClientStatus status, Money balance, Money creditLimit) {
        this.name = name;
        this.adress = adress;
        this.status = status;
        this.balance = balance;
        this.creditLimit = creditLimit;
    }

    public Client(String name, Adress adress) {
        this(name, adress, ClientStatus.STANDARD, new Money(),new Money());

    }

    public boolean canAfford(Money money) {
        return false;
    }

    public void charge(Money amount, String reason) {

    }
    public void recharge(Money amount) {

    }
}
