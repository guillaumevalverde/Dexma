package gve.dexma.vendingmachine.implementation;

import gve.dexma.ToolUtil;
import gve.dexma.pojo.Coin;
import gve.dexma.pojo.CoinInMachine;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangeRecursiveAlgo  {

    private static Map<Coin, Integer> getChangeRecursive(List<CoinInMachine> coins,
                                                         int change, Map<Coin, Integer> changeToGive) {
        if (change == 0) {
            System.out.println("return change: " + ToolUtil.calculateAmountFromChange(changeToGive));
            return changeToGive;
        }

        if (coins.size() == 0) {
            return null;
        }

        Map<Coin, Integer> resultTemp = null;
        int index = coins.size() - 1;
        while (index >= 0) {

            CoinInMachine<Coin> currentCoinInMachine = coins.get(index);
            int numCoin = getMaxCoinForSpecificCoinInMachine(change, currentCoinInMachine);
            int changeToRemove = currentCoinInMachine.getCoin().getValueForCalcul() * numCoin;

            if (change - changeToRemove == 0) {
                if (numCoin > 0)
                    changeToGive.put(currentCoinInMachine.getCoin(), numCoin);
                return changeToGive;
            }

            if (index - 1 >= 0)
                resultTemp = getChangeRecursive(coins.subList(0, index), change - changeToRemove, changeToGive);

            if (resultTemp != null) {
                if (numCoin>0)
                    changeToGive.put(currentCoinInMachine.getCoin(), numCoin);
                return resultTemp;
            } else {
                index--;
            }
        }
        return null;
    }

    private static int getMaxCoinForSpecificCoinInMachine(int change, CoinInMachine coin) {
        if (change < coin.getCoin().getValueForCalcul())
            return 0;
        int numCoinMax =  change /  coin.getCoin().getValueForCalcul();
        return Math.min(coin.getQuantity(), numCoinMax);
    }

    static Map<Coin,Integer> getChangeRecursive(List<CoinInMachine> coins, int money) {
        return getChangeRecursive(coins, money, new HashMap<>());
    }

    static Map<Coin,Integer> getChangeRecursive(List<CoinInMachine> coins, float money) {
        return getChangeRecursive(coins, (int) (money * 100), new HashMap<>());
    }
}
