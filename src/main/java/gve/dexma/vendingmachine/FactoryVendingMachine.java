package gve.dexma.vendingmachine;

import gve.dexma.pojo.Euro;
import gve.dexma.pojo.Product;

import java.util.HashMap;
import java.util.Map;

public class FactoryVendingMachine {

    public final Map<Euro, Integer> getInitializedCoin() {
        Map<Euro, Integer> coins = new HashMap<>();
        coins.put(new Euro(0.05f), 10);
        coins.put(new Euro(0.1f), 10);
        coins.put(new Euro(0.2f), 10);
        coins.put(new Euro(0.5f), 10);
        coins.put(new Euro(1f), 10);
        coins.put(new Euro(2f), 10);
        return coins;
    }

    public final Map<Product, Integer> getInitializedProduct() {
        Map<Product, Integer> product = new HashMap<>();
        product.put(new Product("coca", 1.5f), 10);
        product.put(new Product("Sprite", 1.45f), 10);
        product.put(new Product("water", 0.9f), 10);
        return product;
    }

    public VendingMachine getMachineInitialized() {
        return new VendingMachine(getInitializedCoin(), getInitializedProduct());
    }

}
