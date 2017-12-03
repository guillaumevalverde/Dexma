package gve.dexma.vendingmachine.implementation;

import gve.dexma.pojo.Coin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CoinForTest extends Coin {

    private static final Set<Integer> coinAccepted =
            new HashSet<>(Arrays.asList(1, 2, 5, 6, 10, 20, 50, 60, 100, 200, 400, 500, 600, 700, 800, 900, 1000));

    public CoinForTest(float value) {
        super(value);
    }

    protected void checkValuePrecondition(float value) {
        if (!coinAccepted.contains((int) (value * 100))) {
            throw new IllegalArgumentException("product Selected does not exist: " + value);
        }
    }

    @Override
    public String getType() {
        return "testCoin";
    }

    @Override
    public Class getTypeClass() {
        return this.getClass();
    }

}
