package gve.dexma.vendingmachine.implementation;

import gve.dexma.exception.MoneyException;
import gve.dexma.pojo.Coin;
import gve.dexma.pojo.CoinInMachine;
import gve.dexma.pojo.Product;
import gve.dexma.vendingmachine.VendingMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

    private final List<CoinInMachine> coins;
    private final Map<Product, Integer> productInMachine;


    private int moneyGiven = 0;

    public VendingMachineImpl(Map<? extends Coin, Integer> coinAccepted,
                              Map<Product, Integer>  productInMachine) {
        this.productInMachine = productInMachine;
        this.coins = new ArrayList<>();
        for (Map.Entry<? extends Coin, Integer> coin : coinAccepted.entrySet()) {
            coins.add(new CoinInMachine<>(coin.getKey(), coin.getValue()));
        }
        coins.sort((coin1, coin2) -> coin1.getCoin().getValueForCalcul() < coin2.getCoin().getValueForCalcul()
                ? -1 : coin1.getCoin().getValueForCalcul() < coin2.getCoin().getValueForCalcul() ? 0 : 1);
    }

    public Map<Product, Integer> getAllProductsList() {
        return productInMachine;
    }

    public void addProduct(Product product, int quantityToAdd) {
        Integer currentQuantity;
        int quantity = (currentQuantity = productInMachine.get(product)) == null ? 0 : currentQuantity;
        productInMachine.put(product, quantity + quantityToAdd);
    }

    public void addCoins(Coin coin, int num) {
        checkTypeOfCoinPrecondition(coin);
        int index;
        if ( (index = coins.indexOf(new CoinInMachine<>(coin, 0))) > -1) {
            coins.get(index).addQuantity(num);
        }
    }

    public Product getProduct(Product product) throws MoneyException {
        checkProductInMachinePrecondition(product);

        if (moneyGiven >= product.getPriceInt()) {
            moneyGiven -= product.getPriceInt();
            addProduct(product, -1);
            return product;
        } else {
            throw new MoneyException("not Enough Money");
        }
    }

    public Map<Coin,Integer> getChange() {
        Map<Coin,Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, moneyGiven);
        applyChangeOnListCoinInMachine(change);
        return change;
    }

    public Coin addMoney(Coin coin) {
        checkTypeOfCoinPrecondition(coin);
        int index;
        if ((index = coins.indexOf(new CoinInMachine<>(coin, 0))) >= 0) {
            moneyGiven += coin.getValueForCalcul();
            coins.get(index).addQuantity(1);
            return null;
        } else {
            return coin;
        }
    }

    public float calculateAmountOfCoins( ) {
        float amount = 0;
        for (CoinInMachine coinInMachine: coins)
        {
            amount +=  coinInMachine.getQuantity() * coinInMachine.getCoin().getValueForCalcul();
        }
        return  amount / 100;
    }

    public float getCurrentAmountOfMoneyGivenByUser() {
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

    private void checkTypeOfCoinPrecondition(Coin coin) {
        if (!coins.isEmpty() && coins.get(0).getCoin().getTypeClass() != coin.getTypeClass()) {
            throw new IllegalArgumentException(coin.getType() + " is not accepted by the Machine");
        }
    }

    private boolean checkIntegrityIndex(int indexProduct, int size) {
        return (indexProduct >= 0 && indexProduct < size);
    }

    private void applyChangeOnListCoinInMachine(Map<Coin, Integer> change) {
        for (Map.Entry<Coin, Integer> coinChange : change.entrySet())
        {
            addCoins(coinChange.getKey(), -coinChange.getValue());
        }
    }

}
