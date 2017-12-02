package gve.dexma.pojo;

public class Product {

    private final String name;
    private final int price;

    public Product(String name, float price) {
        this.name = name;
        this.price = (int) (price * 100);
    }

    public String getName() {
        return name;
    }

    public int getPriceInt() {
        return price;
    }

    public float getRealPrice() {
        return ((float)price) / 100;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (price != product.price) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
        return result;
    }
}
