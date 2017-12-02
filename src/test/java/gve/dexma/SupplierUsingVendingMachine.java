package gve.dexma;

import gve.dexma.pojo.Euro;
import gve.dexma.pojo.Product;
import gve.dexma.vendingmachine.CoinForTest;
import gve.dexma.vendingmachine.FactoryVendingMachine;
import gve.dexma.vendingmachine.VendingMachine;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SupplierUsingVendingMachine extends BaseTest {
    VendingMachine vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new FactoryVendingMachine().getMachineInitialized();
    }

    @Test
    public void addMoneyAndCancel() {
        vendingMachine.addCoinSupplierRequest(new Euro(2f), 2);
        assertEquals(12, vendingMachine.getCoin(5).getQuantity());
    }

    @Test
    public void addTwoTypeOfMoney() {
        vendingMachine.addCoinSupplierRequest(new Euro(2f), 2);
        try {
            vendingMachine.addCoinSupplierRequest(new CoinForTest(2f), 2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addNewProductBySupplier() {
        vendingMachine.addProductBySupplier(new Product("fanta", 1.4f), 4);
        assertEquals(4, vendingMachine.getProductInMachine().size());
    }

    @Test
    public void addMoreProductBySupplier() {
        Product coca = new Product("coca", 1.5f);
        vendingMachine.addProductBySupplier(coca, 4);
        assertEquals(14, vendingMachine.getProductInMachine().get(coca).intValue());
    }
}
