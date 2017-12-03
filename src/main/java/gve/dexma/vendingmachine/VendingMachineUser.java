package gve.dexma.vendingmachine;

import gve.dexma.exception.MoneyException;
import gve.dexma.pojo.Coin;
import gve.dexma.pojo.CoinInMachine;
import gve.dexma.pojo.Product;

import java.util.Map;

public interface VendingMachineUser {

    public Product getProduct(Product product) throws MoneyException;
    public Map<Coin,Integer> getChange();
    public Coin addMoney(Coin coin);

}
