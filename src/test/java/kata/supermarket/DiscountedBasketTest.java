package kata.supermarket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountedBasketTest {


    @DisplayName("basket provides value for percent off offer...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue_PercentOff(String description, String value, Product product, double discount, Collection<Item> items) {
        final Discounter discounter = new Discounter();
        discounter.withOffer(product, new PercentOff(product, discount));
        Basket basket = new Basket(discounter);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(value), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue_PercentOff() {
        Product milk_99p = bottleOfMilk_99p();
        Product cheese_200p = new Product("cheese", new BigDecimal("2.00"));
        return Stream.of(
                Arguments.arguments("nothing", "0.00", milk_99p, 0.5, Collections.emptyList()),
                Arguments.arguments("one", "0.49", milk_99p, 0.5, Collections.singleton(milk_99p.oneOf())),
                Arguments.arguments("two", "0.98", milk_99p, 0.5, Arrays.asList(milk_99p.oneOf(), milk_99p.oneOf())),
                Arguments.arguments("two+", "2.98", milk_99p, 0.5, Arrays.asList(milk_99p.oneOf(), milk_99p.oneOf(), cheese_200p.oneOf())));
    }

    @DisplayName("basket provides its total value for b2g1f offer...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void basketProvidesTotalValue_B2G1F(String description, String value, Product product, Collection<Item> items) {
        final Discounter discounter = new Discounter();
        discounter.withOffer(product, new Buy2Get1Free(product));
        Basket basket = new Basket(discounter);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(value), basket.total());
    }

    static Stream<Arguments> basketProvidesTotalValue_B2G1F() {
        Product bread_L1 = bread_L1();
        List<Item> breadItems = IntStream.range(0, 5).mapToObj(n -> bread_L1.oneOf()).collect(Collectors.toList());
        return Stream.of(
                Arguments.arguments("nothing", "0.00", bread_L1, Collections.emptyList()),
                Arguments.arguments("one", "1.00", bread_L1, Collections.singleton(bread_L1.oneOf())),
                Arguments.arguments("two", "2.00", bread_L1, breadItems.subList(0, 2)),
                Arguments.arguments("three", "2.00", bread_L1, breadItems.subList(0, 3)),
                Arguments.arguments("four", "3.00", bread_L1, breadItems.subList(0, 4)));
    }

    static Product bottleOfMilk_99p() {
        return new Product("Milk", new BigDecimal("0.99"));
    }

    static Product bread_L1() {
        return new Product("Bread", new BigDecimal("1.00"));
    }

}
