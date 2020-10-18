package kata.supermarket;

import java.math.BigDecimal;

public class Discount {
    private final Offer offer;
    private int nItems;

    public Discount(Offer offer) {
        this.offer = offer;
    }

    void addItem() {
        nItems++;
    }

    BigDecimal calculate() {
        return offer.calculate(nItems);
    }
}
