package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ForexTest {


    @Test
    public void assert_can_create_forex() {
        Forex forex = new Forex("EUR", "GBP", BigDecimal.valueOf(12));
        Assertions.assertEquals(forex.currencyCode2(), "GBP");
        Assertions.assertEquals(forex.exchangeRate(), BigDecimal.valueOf(12));
    }
    @Test
    public void assert_can_create_in_reversed_order() {
        Forex forex = new Forex("GBP", "EUR", BigDecimal.valueOf(10));
        Assertions.assertEquals(forex.currencyCode1(), "GBP");
        Assertions.assertEquals(forex.exchangeRate(), BigDecimal.valueOf(10));
    }
}
