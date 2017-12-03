package gve.dexma;

import gve.dexma.pojo.Euro;
import gve.dexma.pojo.Product;
import gve.dexma.vendingmachine.implementation.CoinForTest;
import gve.dexma.vendingmachine.FactoryVendingMachine;
import gve.dexma.vendingmachine.VendingMachineSupplier;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Functionality To Test:
 Map<Product, Integer> getAllProductsList(); /
 CoinInMachine getCoin(int index); /
 void addProduct(Product product, int quantityToAdd);/
 void addCoins(Coin coin, int num); /
 float calculateAmountOfCoins( );

 */
public class SupplierUsingVendingMachine extends BaseTest {
    VendingMachineSupplier vendingMachine;

    @Before
    public void setup() {
        vendingMachine = new FactoryVendingMachine().getMachineInitialized();
    }

    @Test
    public void calculateAmountInMachineSupplierTest() {
        assertEquals(38.5, vendingMachine.calculateAmountOfCoins(), 0.001);
    }

    @Test
    public void addMoneyAndCancel() {
        vendingMachine.addCoins(new Euro(2f), 2);
        assertEquals(12, vendingMachine.getCoin(5).getQuantity());
    }

    @Test
    public void addTwoTypeOfMoney() {
        vendingMachine.addCoins(new Euro(2f), 2);
        try {
            vendingMachine.addCoins(new CoinForTest(2f), 2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void addNewProductBySupplier() {
        vendingMachine.addProduct(new Product("fanta", 1.4f), 4);
        assertEquals(4, vendingMachine.getAllProductsList().size());
    }

    @Test
    public void addMoreProductBySupplier() {
        Product coca = new Product("coca", 1.5f);
        vendingMachine.addProduct(coca, 4);
        assertEquals(14, vendingMachine.getAllProductsList().get(coca).intValue());
    }
}
