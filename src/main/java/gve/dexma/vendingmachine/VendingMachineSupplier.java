package gve.dexma.vendingmachine;

import gve.dexma.pojo.Coin;
import gve.dexma.pojo.CoinInMachine;
import gve.dexma.pojo.Product;

import java.util.Map;

public interface VendingMachineSupplier {

    Map<Product, Integer> getAllProductsList();
    CoinInMachine getCoin(int index);
    void addProduct(Product product, int quantityToAdd);
    void addCoins(Coin coin, int num);
    float calculateAmountOfCoins( );
    float getCurrentAmountOfMoneyGivenByUser();
}




