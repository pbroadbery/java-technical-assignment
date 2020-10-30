package kata.supermarket;

import java.math.BigDecimal;
import java.util.Objects;

public class Product implements AbstractProduct {
    private final String id;
    private final BigDecimal pricePerUnit;

    public Product(String id, final BigDecimal pricePerUnit) {
        this.id = id;
        this.pricePerUnit = pricePerUnit;
    }

    BigDecimal pricePerUnit() {
        return pricePerUnit;
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
