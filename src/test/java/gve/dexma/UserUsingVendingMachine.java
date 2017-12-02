package gve.dexma;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gve.dexma.exception.MoneyException;
import gve.dexma.pojo.Euro;
import gve.dexma.pojo.Product;
import gve.dexma.vendingmachine.FactoryVendingMachine;
import gve.dexma.vendingmachine.VendingMachine;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class UserUsingVendingMachine extends BaseTest {
    VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new FactoryVendingMachine().getMachineInitialized();
     }

    @Test
    public void addMoneyInMachineWithAcceptedEuro() {
        Euro change1 = vendingMachine.addMoneyInMachineUserRequest(new Euro(0.05f));
        Euro change2 = vendingMachine.addMoneyInMachineUserRequest(new Euro(0.1f));
        Euro change3 = vendingMachine.addMoneyInMachineUserRequest(new Euro(0.2f));
        Euro change4 = vendingMachine.addMoneyInMachineUserRequest(new Euro(0.5f));
        Euro change5 = vendingMachine.addMoneyInMachineUserRequest(new Euro(1f));
        Euro change6 = vendingMachine.addMoneyInMachineUserRequest(new Euro(2f));

        assertTrue(change1 == null);
        assertTrue(change2 == null);
        assertTrue(change3 == null);
        assertTrue(change4 == null);
        assertTrue(change5 == null);
        assertTrue(change6 == null);
        assertEquals(11, vendingMachine.getCoin(0).getQuantity());
        assertEquals(11, vendingMachine.getCoin(1).getQuantity());
        assertEquals(11, vendingMachine.getCoin(2).getQuantity());
        assertEquals(11, vendingMachine.getCoin(3).getQuantity());
        assertEquals(11, vendingMachine.getCoin(4).getQuantity());
        assertEquals(11, vendingMachine.getCoin(5).getQuantity());
        assertEquals(3.85f, vendingMachine.getMoneyGivenSupplier(),0.001);
    }

    @Test
    public void addMoneyInMachineWithNonAcceptedEuro() {
        //0.01f, 0.02f, 0.05f, 0.1f, 0.2f, 0.5f, 1f, 2f
        Euro change1 = vendingMachine.addMoneyInMachineUserRequest(new Euro(0.01f));
        Euro change2 = vendingMachine.addMoneyInMachineUserRequest(new Euro(0.02f));
        assertEquals(new Euro(0.01f), change1);
        assertEquals(new Euro(0.02f), change2);
        assertEquals(0f, vendingMachine.getMoneyGivenSupplier(),0.001);
    }

    @Test
    public void addMoneyAndCancelBeforeChoosingProductRecursive() {
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.1f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.2f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.5f));

        Map<Euro, Integer> change = vendingMachine.getChangeUserRequest();

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        assertEquals(0.8, amountForChange, 0.001);
    }

    @Test
    public void assertStateOfMachinAfterChangeGiven() {
        float moneyInMachineBefore = vendingMachine.calculateAmountInMachineSupplier();
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.1f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.2f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.5f));

        System.out.println(vendingMachine.toString());

        Map<Euro, Integer> change = vendingMachine.getChangeUserRequest();
        float moneyInMachineAfter = vendingMachine.calculateAmountInMachineSupplier();

        assertEquals(moneyInMachineBefore, moneyInMachineAfter, 0.001);
    }

    @Test
    public void getProductUserRequest() {
        Product coca = new Product("coca", 1.5f);
        vendingMachine.calculateAmountInMachineSupplier();
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.1f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.2f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.5f));
        Product p = null;
        try {
             p = vendingMachine.getProductUserRequest(coca);
        } catch (MoneyException e) {
            e.printStackTrace();
        }
        assertTrue(p == null);
    }

    @Test
    public void getFullUserExperienceRequest() {
        Product coca = new Product("coca", 1.5f);
        vendingMachine.calculateAmountInMachineSupplier();
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.1f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.2f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(0.5f));
        vendingMachine.addMoneyInMachineUserRequest(new Euro(1f));
        Product p = null;
        try {
            p = vendingMachine.getProductUserRequest(coca);
        } catch (MoneyException e) {
            e.printStackTrace();
        }
        Map<Euro, Integer> change = vendingMachine.getChangeUserRequest();
        float changeAmount = vendingMachine.calculateAmountInMachineSupplier();
        assertEquals(0.3f, ToolUtil.calculateAmountFromChange(change), 0.001);
    }



}
