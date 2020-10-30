package kata.supermarket;

import java.math.BigDecimal;

public interface WeightedOffer extends AbstractOffer {
    Discount discount(WeighedProduct product);

    // This assumes a simple discount based on the number of items of a single
    // product
    BigDecimal calculate(BigDecimal total);
}
