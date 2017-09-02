package pl.com.bottega.photostock.sales.model;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static pl.com.bottega.photostock.sales.model.ClientStatus.VIP;

public class ClientTest {

    private Address address = new Address("ul. Północna 11", "Poland", "Lublin", "20-001");

    private Client clientWithCredit = new VIPClient("Jan Nowak" , address , VIP, Money.valueOf(100), Money.valueOf(100));

    private Client clientWithoutMoney  = new VIPClient("Jan Nowak" , address );
    @Test
    public void shouldCheckIfClientAfford() {

        //when

        clientWithoutMoney.recharge(Money.valueOf(100));

        //then

        assertTrue(clientWithoutMoney.canAfford(Money.valueOf(100)));
        assertTrue(clientWithoutMoney.canAfford(Money.valueOf(50)));
        assertFalse(clientWithoutMoney.canAfford(Money.valueOf(101)));

    }

    @Test
    public void shouldCheckIfClientAffordWithCredit() {

        //then

        assertTrue(clientWithCredit.canAfford(Money.valueOf(200)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(201)));

    }

    @Test
    public void shouldChargeAndRechargeClient() {


        //when

        clientWithCredit.charge(Money.valueOf(200), "Testowy zakup");
        clientWithCredit.recharge(Money.valueOf(100));

        //then

        assertTrue(clientWithCredit.canAfford(Money.valueOf(100)));
        assertFalse(clientWithCredit.canAfford(Money.valueOf(101)));
    }

    @Test (expected = IllegalStateException.class)
    public void shouldNotAllowedToChargeMoreThanCanAfford() {
        clientWithCredit.charge(Money.valueOf(50), "Testowy zakup");
        clientWithCredit.charge(Money.valueOf(100), "Testowy zakup");
        clientWithCredit.charge(Money.valueOf(100), "Testowy zakup");
    }
}
