package kata.supermarket;

import java.math.BigDecimal;

public interface Offer {
    Discount discount(Product product);

    // This assumes a simple discount based on the number of items of a single
    // product
    BigDecimal calculate(int nItems);
}
