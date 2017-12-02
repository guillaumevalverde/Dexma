package gve.dexma.pojo;

public class CoinInMachine<T extends Coin> {
    private final T coin;
    private int quantity;

    public CoinInMachine(T coin, int quantity) {
        this.coin = coin;
        this.quantity = quantity;
    }

    public T getCoin() {
        return coin;
    }

    public int getQuantity() {
        return quantity;
    }

    public int addQuantity(int add) {
        quantity = quantity + add;
        return quantity;
    }

    public int removeQuantity(int add) {
        quantity -= add;
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoinInMachine that = (CoinInMachine) o;

        return coin.equals(that.coin);
    }

    @Override
    public int hashCode() {
        return coin.hashCode();
    }

    @Override
    public String toString() {
        return "CoinInMachine(" +
                "coin=" + coin.getRealValue() +
                ", quantity=" + quantity +
                ')';
    }
}
