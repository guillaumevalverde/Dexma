package gve.dexma.pojo;

public class CoinInMachine {
    private final Euro euro;
    private int quantity;

    public CoinInMachine(Euro euro, int quantity) {
        this.euro = euro;
        this.quantity = quantity;
    }

    public Euro getEuro() {
        return euro;
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

        return euro.equals(that.euro);
    }

    @Override
    public int hashCode() {
        return euro.hashCode();
    }

    @Override
    public String toString() {
        return "CoinInMachine(" +
                "euro=" + euro.getRealValue() +
                ", quantity=" + quantity +
                ')';
    }
}
