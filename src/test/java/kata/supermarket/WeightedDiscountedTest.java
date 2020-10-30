package kata.supermarket;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class WeightedDiscountedTest {
    static WeighedProduct carrots = new WeighedProduct(new BigDecimal("2.00"));
    static WeighedProduct celery = new WeighedProduct(new BigDecimal("2.00"));

    @Test
    public void testWeightedDiscounter() {
        final Discounter discounter = new Discounter();
        WeighedProduct weighedProduct = new WeighedProduct(new BigDecimal("2.00"));// carrots
        discounter.withOffer(weighedProduct, new HalfPriceWeighted(weighedProduct));

        Basket basket = new Basket(discounter);
        basket.add(new ItemByWeight(weighedProduct, new BigDecimal("1.00")));

        assertEquals(new BigDecimal("1.00"), basket.total());
    }

    @DisplayName("basket provides value for weighted discount")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    public void basketProvidesTotalValue_halfPriceWeighed(String description, String value, Collection<Item> items) {
        Discounter discounter = new Discounter();
        discounter.withOffer(carrots, new HalfPriceWeighted(carrots));
        Basket basket = new Basket(discounter);
        items.forEach(basket::add);

        assertEquals(new BigDecimal(value), basket.total());
    }

    public static Stream<Arguments> basketProvidesTotalValue_halfPriceWeighed() {
        return Stream.of(
            Arguments.arguments("emptyBasket", "0.00", Collections.emptyList()),
            Arguments.arguments("buy500g", "1.00", Collections.singletonList(new ItemByWeight(carrots, new BigDecimal("0.50")))),
            Arguments.arguments("buy500gCarrAnd1KgCelery", "3.00",
                    Arrays.asList(new ItemByWeight(carrots, new BigDecimal("0.50")),
                                  new ItemByWeight(celery, new BigDecimal("1.00"))))
        );
    }

}