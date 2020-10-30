package kata.supermarket;

import java.math.BigDecimal;

public abstract class Discount {
    protected final Offer offer;

    protected Discount(Offer offer) {
        this.offer = offer;
    }


    abstract BigDecimal calculate();

    abstract void addItem(Item item);

    static class ItemDiscount extends Discount {
        int nItems;

        public ItemDiscount(Offer offer) {
            super(offer);
        }

        @Override
        void addItem(Item item) {
            nItems += 1;
        }

        @Override
        BigDecimal calculate() {
            return offer.calculate(nItems);
        }
    }

    static class WeightedDiscount extends Discount {
        protected BigDecimal weight = BigDecimal.ZERO;

        public WeightedDiscount(Offer offer) {
            super(offer);
        }

        @Override
        BigDecimal calculate() {
            return offer.calculate(weight);
        }

        @Override
        void addItem(Item item) {
            weight = weight.add(((ItemByWeight) item).weightInKilos());
        }
    }
}
