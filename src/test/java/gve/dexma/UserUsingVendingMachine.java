package gve.dexma;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gve.dexma.exception.MoneyException;
import gve.dexma.pojo.Coin;
import gve.dexma.pojo.Euro;
import gve.dexma.pojo.Product;
import gve.dexma.vendingmachine.FactoryVendingMachine;
import gve.dexma.vendingmachine.VendingMachine;
import gve.dexma.vendingmachine.VendingMachineSupplier;
import gve.dexma.vendingmachine.VendingMachineUser;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/** Functionality to test
 public Product getProduct(Product product) throws MoneyException;
 public Map<Coin,Integer> getChange();
 public Coin addMoney(Coin coin);
 */
public class UserUsingVendingMachine extends BaseTest {
    VendingMachineUser vendingMachineUser;
    VendingMachineSupplier vendingMachineSupplier;
    VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new FactoryVendingMachine().getMachineInitialized();
        vendingMachineSupplier = vendingMachine;
        vendingMachineUser = vendingMachine;
     }

    @Test
    public void addMoneyInMachineWithAcceptedEuro() {
        Coin change1 = vendingMachineUser.addMoney(new Euro(0.05f));
        Coin change2 = vendingMachineUser.addMoney(new Euro(0.1f));
        Coin change3 = vendingMachineUser.addMoney(new Euro(0.2f));
        Coin change4 = vendingMachineUser.addMoney(new Euro(0.5f));
        Coin change5 = vendingMachineUser.addMoney(new Euro(1f));
        Coin change6 = vendingMachineUser.addMoney(new Euro(2f));

        assertTrue(change1 == null);
        assertTrue(change2 == null);
        assertTrue(change3 == null);
        assertTrue(change4 == null);
        assertTrue(change5 == null);
        assertTrue(change6 == null);
        assertEquals(11, vendingMachineSupplier.getCoin(0).getQuantity());
        assertEquals(11, vendingMachineSupplier.getCoin(1).getQuantity());
        assertEquals(11, vendingMachineSupplier.getCoin(2).getQuantity());
        assertEquals(11, vendingMachineSupplier.getCoin(3).getQuantity());
        assertEquals(11, vendingMachineSupplier.getCoin(4).getQuantity());
        assertEquals(11, vendingMachineSupplier.getCoin(5).getQuantity());
        assertEquals(3.85f, vendingMachineSupplier.getCurrentAmountOfMoneyGivenByUser(),0.001);
    }

    @Test
    public void addMoneyInMachineWithNonAcceptedEuro() {
        //0.01f, 0.02f, 0.05f, 0.1f, 0.2f, 0.5f, 1f, 2f
        Coin change1 = vendingMachineUser.addMoney(new Euro(0.01f));
        Coin change2 = vendingMachineUser.addMoney(new Euro(0.02f));
        assertEquals(new Euro(0.01f), change1);
        assertEquals(new Euro(0.02f), change2);
        assertEquals(0f, vendingMachineSupplier.getCurrentAmountOfMoneyGivenByUser(),0.001);
    }

    @Test
    public void addMoneyAndCancelBeforeChoosingProductRecursive() {
        vendingMachineUser.addMoney(new Euro(0.1f));
        vendingMachineUser.addMoney(new Euro(0.2f));
        vendingMachineUser.addMoney(new Euro(0.5f));

        Map<Coin, Integer> change = vendingMachineUser.getChange();

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        assertEquals(0.8, amountForChange, 0.001);
    }

    @Test
    public void assertStateOfMachinAfterChangeGiven() {
        float moneyInMachineBefore = vendingMachineSupplier.calculateAmountOfCoins();
        vendingMachineUser.addMoney(new Euro(0.1f));
        vendingMachineUser.addMoney(new Euro(0.2f));
        vendingMachineUser.addMoney(new Euro(0.5f));

        System.out.println(vendingMachineUser.toString());

        Map<Coin, Integer> change = vendingMachineUser.getChange();
        float moneyInMachineAfter = vendingMachineSupplier.calculateAmountOfCoins();

        assertEquals(moneyInMachineBefore, moneyInMachineAfter, 0.001);
    }

    @Test
    public void getProductUserRequest() {
        Product coca = new Product("coca", 1.5f);
        vendingMachineSupplier.calculateAmountOfCoins();
        vendingMachineUser.addMoney(new Euro(0.1f));
        vendingMachineUser.addMoney(new Euro(0.2f));
        vendingMachineUser.addMoney(new Euro(0.5f));
        Product p = null;
        try {
             p = vendingMachineUser.getProduct(coca);
        } catch (MoneyException e) {
            e.printStackTrace();
        }
        assertTrue(p == null);
    }

    @Test
    public void getFullUserExperienceRequest() {
        Product coca = new Product("coca", 1.5f);
        vendingMachineSupplier.calculateAmountOfCoins();
        vendingMachineUser.addMoney(new Euro(0.1f));
        vendingMachineUser.addMoney(new Euro(0.2f));
        vendingMachineUser.addMoney(new Euro(0.5f));
        vendingMachineUser.addMoney(new Euro(1f));
        Product p = null;
        try {
            p = vendingMachineUser.getProduct(coca);
        } catch (MoneyException e) {
            e.printStackTrace();
        }
        Map<Coin, Integer> change = vendingMachineUser.getChange();
        float changeAmount = vendingMachineSupplier.calculateAmountOfCoins();
        assertEquals(0.3f, ToolUtil.calculateAmountFromChange(change), 0.001);
    }
}
