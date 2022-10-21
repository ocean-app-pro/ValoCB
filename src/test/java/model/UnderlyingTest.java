package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.ForexService;

import java.math.BigDecimal;

public class UnderlyingTest {
    static ForexService forexService;

    @BeforeAll
    public static void init(){
        forexService = new ForexService();
        forexService.addForex(new Forex("EUR", "GBP", BigDecimal.TEN));
        forexService.addForex(new Forex("USD", "EUR", BigDecimal.ONE));
    }
    @Test
    public void test_create_underlying() {
        Underlying underlying = new Underlying("U11", "GBP", BigDecimal.ONE);
        Assertions.assertEquals("U11", underlying.name());
        Assertions.assertEquals(BigDecimal.ONE, underlying.originPrice());
    }
    @Test
    public void test_equals_underlying() {
        Underlying underlying = new Underlying("U11", "GBP", BigDecimal.ONE);
        Underlying otherUnderlying = new Underlying("U11", "GBP", BigDecimal.ONE);
        Assertions.assertEquals(underlying, otherUnderlying);
        Assertions.assertNotEquals(underlying, null);
        Assertions.assertEquals(underlying, underlying);



    }
}
