package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountedBasketTest {


    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue(String description, String value, Product product, double discount, Collection<Item> items) {
        final Discounter discounter = new Discounter();
        discounter.withOffer(product, new PercentOff(product, discount));
        Basket basket = new Basket(discounter);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(value), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        Product milk_99p = bottleOfMilk_99p();
        Product cheese_200p = new Product("cheese", new BigDecimal("2.00"));
        return Stream.of(
                Arguments.arguments("nothing", "0.00", milk_99p, 0.5, Collections.emptyList()),
                Arguments.arguments("one", "0.49", milk_99p, 0.5, Collections.singleton(milk_99p.oneOf())),
                Arguments.arguments("two", "0.98", milk_99p, 0.5, Arrays.asList(milk_99p.oneOf(), milk_99p.oneOf())),
                Arguments.arguments("two+", "2.98", milk_99p, 0.5, Arrays.asList(milk_99p.oneOf(), milk_99p.oneOf(), cheese_200p.oneOf())));
    }

    static Product bottleOfMilk_99p() {
        return new Product("Milk", new BigDecimal("0.99"));
    }

}
