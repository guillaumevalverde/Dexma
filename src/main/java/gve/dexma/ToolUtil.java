package gve.dexma;

import gve.dexma.pojo.Coin;
import gve.dexma.pojo.CoinInMachine;
import gve.dexma.pojo.Euro;
import java.util.List;
import java.util.Map;

public class ToolUtil {

    public static float calculateAmountFromChange(Map<? extends Coin, Integer> change) {
        if (change == null) {
            return 0f;
        }
        float amount = 0;
        for (Map.Entry<? extends Coin, Integer> entry : change.entrySet())
        {
            amount +=  entry.getKey().getValueForCalcul() * entry.getValue();
        }
        return  amount / 100;
    }

    public static int calculateAmountFromChangeInt(Map<Euro, Integer> change) {
        if (change == null) {
            return 0;
        }
        int amount = 0;
        for (Map.Entry<Euro, Integer> entry : change.entrySet())
        {
            amount +=  entry.getKey().getValueForCalcul() * entry.getValue();
        }
        return amount;
    }

    public static boolean checkChangeIsRightMap(Map<Euro, Integer> change, int changeToget) {
        return calculateAmountFromChangeInt(change) == changeToget;

    }

    public static String getStringFromChange(Map<Coin, Integer> change) {
        if (change == null || change.size() == 0) {
            return "No change sorry!!!";
        }
        StringBuilder str = new StringBuilder();
        str.append("\nFrom change:\n");
        for (Map.Entry<Coin, Integer> entry : change.entrySet())
        {
            str.append("(").append(entry.getKey().getRealValue()).append(entry.getKey().getType()).append(", ").append(entry.getValue()).append(")\n");
        }
        return str.toString();
    }

    public static void printListCoinInMachine(List<CoinInMachine> coins) {
        System.out.println("coin in Machine");
        StringBuilder str = new StringBuilder();
        for (CoinInMachine c : coins) {
            str.append(coins.toString()).append("\n");
        }
        System.out.println(str.toString());
    }
}
