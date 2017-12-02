package gve.dexma.pojo;

public abstract class Coin {

    private final int value;

    protected Coin(float value) {
        checkValuePrecondition(value);
        this.value = (int) (value * 100);
    }

    public int getValueForCalcul() {
        return value;
    }

    public float getRealValue() {
        return ((float)value) / 100;
    }

    protected abstract void checkValuePrecondition(float value);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        return value == coin.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
