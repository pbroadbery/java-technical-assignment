package kata.supermarket;

import java.math.BigDecimal;

/**
 * An alternative discount.  Buying 2 is full price * 2 as is buying 3.
 * Hard-coded '2' and '1', just for speed of writing
 */
public class Buy2Get1Free implements Offer {
    private final Product product;

    public Buy2Get1Free(Product product) {
        this.product = product;
    }

    @Override
    public Discount discount(AbstractProduct product) {
        return new Discount.ItemDiscount(this);
    }

    @Override
    public BigDecimal calculate(Object state) {
        int nItems = (Integer) state;
        int nInstances = nItems/3;
        return product.pricePerUnit().multiply(BigDecimal.valueOf(nInstances));
    }
}
