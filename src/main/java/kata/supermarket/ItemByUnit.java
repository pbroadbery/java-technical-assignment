package kata.supermarket;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final Product product;

    ItemByUnit(final Product product) {
        this.product = product;
    }

    @Override
    public BigDecimal price() {
        return product.pricePerUnit();
    }

    @Override
    public Product product() {
        return product;
    }
}
