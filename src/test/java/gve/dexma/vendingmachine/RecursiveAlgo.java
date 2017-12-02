package gve.dexma.vendingmachine;

import gve.dexma.BaseTest;
import gve.dexma.ToolUtil;
import gve.dexma.pojo.Coin;
import gve.dexma.pojo.CoinInMachine;
import gve.dexma.pojo.Euro;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecursiveAlgo  extends BaseTest {


    @Test
    public void putOneCoinGetItBackTest() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(0.5f),4));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 0.5f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(0.5, amountForChange, 0.001);
    }

    @Test
    public void putTwoCoinGetItBackTest() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(1f),1));
        coins.add(new CoinInMachine<>(new Euro(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 3f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(3, amountForChange, 0.001);
    }

    @Test
    public void testSimple3Test() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(0.5f),1));
        coins.add(new CoinInMachine<>(new Euro(1f),1));
        coins.add(new CoinInMachine<>(new Euro(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 3f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(3, amountForChange, 0.001);
    }

    @Test
    public void testSimple4Test() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(0.5f),1));
        coins.add(new CoinInMachine<>(new Euro(1f),1));
        coins.add(new CoinInMachine<>(new Euro(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 2.5f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(2.5, amountForChange, 0.001);
    }


    @Test
    public void testSimple5Test() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(0.5f),3));
        coins.add(new CoinInMachine<>(new Euro(1f),1));
        coins.add(new CoinInMachine<>(new Euro(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 1.5f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(1.5, amountForChange, 0.001);
    }

    @Test
    public void testSimple6Test() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(0.5f),3));
        coins.add(new CoinInMachine<>(new Euro(1f),1));
        coins.add(new CoinInMachine<>(new Euro(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 1.5f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(1.5, amountForChange, 0.001);
    }

    @Test
    public void testSimple7Test() {
        List<CoinInMachine> coins = new ArrayList<>();

        coins.add(new CoinInMachine<>(new Euro(1f),1));
        coins.add(new CoinInMachine<>(new Euro(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 1.5f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertTrue(change == null);
    }

    @Test
    public void recursiveAlgoTest() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(0.2f),4));
        coins.add(new CoinInMachine<>(new Euro(0.5f),4));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 0.8f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(0.8, amountForChange, 0.001);
    }

    @Test
    public void recursiveAlgo2Test() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new Euro(0.2f),2));
        coins.add(new CoinInMachine<>(new Euro(0.5f),1));
        coins.add(new CoinInMachine<>(new Euro(1f),1));
        coins.add(new CoinInMachine<>(new Euro(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 2.9f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(2.9, amountForChange, 0.001);
    }


    @Test
    public void recursiveAlgo3Test() {
        List<CoinInMachine> coins = new ArrayList<>();
        coins.add(new CoinInMachine<>(new CoinForTest(0.6f),2));
        coins.add(new CoinInMachine<>(new CoinForTest(1f),1));
        coins.add(new CoinInMachine<>(new CoinForTest(2f),1));

        Map<Coin, Integer> change = ChangeRecursiveAlgo.getChangeRecursive(coins, 3.2f);

        float amountForChange = ToolUtil.calculateAmountFromChange(change);
        System.out.println(ToolUtil.getStringFromChange(change));
        assertEquals(3.2, amountForChange, 0.001);
    }

}
