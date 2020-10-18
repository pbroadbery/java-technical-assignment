package kata.supermarket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class Discounter {
    Map<Product, Offer> offerForProduct = new HashMap<>();
    Map<Product, Discount> discountForProduct = new HashMap<>();

    public void withOffer(Product product, Offer offer) {
        if (offerForProduct.containsKey(product)) {
            throw new IllegalArgumentException("Added 2 offers for the same product");
        }
        offerForProduct.put(product, offer);
    }

    void addItem(Item item) {
        if (item instanceof ItemByUnit) {
            addItem((ItemByUnit) item);
        } else if (item instanceof ItemByWeight) {
            addItem((ItemByWeight) item);
        }
    }

    void addItem(ItemByWeight item) {
    }

    void addItem(ItemByUnit item) {
        final Discount discount;
        if (discountForProduct.containsKey(item.product())) {
            discount = discountForProduct.get(item.product());
        }
        else if (offerForProduct.containsKey(item.product())) {
            discount = offerForProduct.get(item.product()).discount(item.product());
            discountForProduct.put(item.product(), discount);
        }
        else {
            discount = null;
        }
        if (discount != null) {
            discount.addItem();
        }
    }

    public BigDecimal calculate() {
        // We expect that most offer types will round correctly, but we will round at this level
        // to guarantee behaviour
        return discountForProduct.values().stream()
                .map(discount -> discount.calculate().setScale(2, RoundingMode.HALF_UP))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
