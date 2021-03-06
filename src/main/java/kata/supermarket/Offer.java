package kata.supermarket;

import java.math.BigDecimal;

public interface Offer extends AbstractOffer {
    Discount discount(AbstractProduct product);

    // This assumes a simple discount based on the number of items of a single
    // product
    BigDecimal calculate(Object state);
}
