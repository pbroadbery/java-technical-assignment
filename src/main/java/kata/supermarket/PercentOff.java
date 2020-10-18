package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PercentOff implements Offer {
    private final Product product;
    private BigDecimal reduction;

    public PercentOff(Product product, double reduction) {
        this.reduction = BigDecimal.valueOf(reduction);
        this.product = product;
    }

    @Override
    public Discount discount(Product product) {
        return new Discount(this);
    }

    @Override
    public BigDecimal calculate(int nItems) {
        BigDecimal discount = product.pricePerUnit()
                .multiply(reduction)
                .setScale(2, RoundingMode.HALF_UP);
        return discount.multiply(BigDecimal.valueOf(nItems));
    }

}
