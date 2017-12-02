package gve.dexma.vendingmachine;

import gve.dexma.exception.MoneyException;
import gve.dexma.pojo.CoinInMachine;
import gve.dexma.pojo.Euro;
import gve.dexma.pojo.Product;

import java.util.*;

import static gve.dexma.vendingmachine.ChangeRecursiveAlgo.getChangeRecursive;

public class VendingMachine {

    private final List<CoinInMachine> coins;
    private final Map<Product, Integer> productInMachine;


    private int moneyGiven = 0;

    public VendingMachine(Map<Euro, Integer> coinAccepted,
                          Map<Product, Integer>  productInMachine) {
        this.productInMachine = productInMachine;
        this.coins = new ArrayList<>();
        for (Map.Entry<Euro, Integer> coin : coinAccepted.entrySet()) {
            coins.add(new CoinInMachine(coin.getKey(), coin.getValue()));
        }
        coins.sort((coin1, coin2) -> coin1.getEuro().getValueForCalcul() < coin2.getEuro().getValueForCalcul()
                ? -1 : coin1.getEuro().getValueForCalcul() < coin2.getEuro().getValueForCalcul() ? 0 : 1);
    }

    public List<CoinInMachine> getCoins() {
        return coins;
    }

    public Map<Product, Integer> getProductInMachine() {
        return productInMachine;
    }

    public int getMoneyGiven() {
        return moneyGiven;
    }

    public void addProductBySupplier(Product product, int quantityToAdd) {
        Integer currentQuantity;
        int quantity = (currentQuantity = productInMachine.get(product)) == null ? 0 : currentQuantity;
        productInMachine.put(product, quantity + quantityToAdd);
    }

    public void addEuroSupplierRequest(Euro euro, int num) {
        int index;
        if ( (index = coins.indexOf(new CoinInMachine(euro, 0))) > -1) {
            coins.get(index).addQuantity(num);
        }
    }

    public Product getProductUserRequest(Product product) throws MoneyException {
        checkProductInMachinePrecondition(product);

        if (moneyGiven >= product.getPriceInt()) {
            moneyGiven -= product.getPriceInt();
            addProductBySupplier(product, -1);
            return product;
        } else {
            throw new MoneyException("not Enough Money");
        }
    }

    public Map<Euro,Integer> getChangeUserRequest() {
        Map<Euro,Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, moneyGiven);
        applyChangeOnListCoinInMachine(change);
        return change;
    }

    public Euro addMoneyInMachineUserRequest(Euro euro) {
        int index;
        if ((index = coins.indexOf(new CoinInMachine(euro, 0))) >= 0) {
            moneyGiven += euro.getValueForCalcul();
            coins.get(index).addQuantity(1);
            return null;
        } else {
            return euro;
        }
    }

    public float calculateAmountInMachineSupplier( ) {
        int amount = 0;
        for (CoinInMachine coinInMachine: coins)
        {
            amount +=  coinInMachine.getQuantity() * coinInMachine.getEuro().getValueForCalcul();
        }
        return  amount / 100;
    }

    public float getMoneyGivenSupplier() {
        return ((float) moneyGiven) / 100;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Vending Machine\n\n");
        str.append("Products in machine: \n");
        for (Map.Entry<Product, Integer> product : productInMachine.entrySet()) {
            str.append(product.getKey().getName());
            str.append(" -> ");
            str.append(product.getValue());
            str.append("\n");
        }

        str.append("\nCoins in machine \n");
        for (CoinInMachine c : coins) {
           str.append(coins.toString()).append("\n");
        }
        return str.toString();
    }

    public CoinInMachine getCoin(int i) {
        checkIntegrityIndex(i, coins.size());
        return coins.get(i);
    }

    private void checkProductInMachinePrecondition(Product product) {
        if (!productInMachine.containsKey(product)) {
            throw new IllegalArgumentException("product Selected is not in the Machine");
        }
    }

    private boolean checkIntegrityIndex(int indexProduct, int size) {
        return (indexProduct >= 0 && indexProduct < size);
    }

    private void applyChangeOnListCoinInMachine(Map<Euro, Integer> change) {
        for (Map.Entry<Euro, Integer> coinChange : change.entrySet())
        {
            addEuroSupplierRequest(coinChange.getKey(), -coinChange.getValue());
        }
    }

}
