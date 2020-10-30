package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class HalfPriceWeighted implements Offer {
    private final WeighedProduct product;

    public HalfPriceWeighted(WeighedProduct weighedProduct) {
        this.product = weighedProduct;
    }

    @Override
    public Discount discount(AbstractProduct product) {
        return new Discount.WeightedDiscount(this);
    }

    @Override
    public BigDecimal calculate(Object state) {
        BigDecimal weight = (BigDecimal) state;

        int nKilos = weight.intValue();
        BigDecimal discount = BigDecimal.valueOf(nKilos).multiply(product.pricePerKilo()).divide(BigDecimal.valueOf(2), RoundingMode.HALF_UP);

        return discount;
    }
}
