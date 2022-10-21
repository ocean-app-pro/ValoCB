package service;

import model.Forex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ForexServiceTest {

    @Test
    public void test_is_ok_forex_service_creation(){
        ForexService forexService = new ForexService();
        Assertions.assertEquals(1, forexService.getForexValues().size());
        Assertions.assertTrue(forexService.getForexValues().containsKey("EUR"));
    }
    @Test
    public void test_can_add_forex_ordered() {
        ForexService forexService = new ForexService();
        forexService.addForex(new Forex("EUR", "GBP", BigDecimal.TEN));
        Assertions.assertEquals(2, forexService.getForexValues().size());
        Assertions.assertTrue(forexService.getForexValues().containsKey("GBP"));
        Assertions.assertEquals(BigDecimal.TEN, forexService.getForexValues().get("GBP").exchangeRate());

    }
    @Test
    public void test_can_add_forex_reversed() {
        ForexService forexService = new ForexService();
        forexService.addForex(new Forex("USD", "EUR", BigDecimal.valueOf(5)));
        Assertions.assertEquals(2, forexService.getForexValues().size());
        Assertions.assertTrue(forexService.getForexValues().containsKey("USD"));
        Assertions.assertEquals(BigDecimal.ONE
                .divide(BigDecimal.valueOf(5)), forexService.getForexValues().get("USD")
                .exchangeRate().stripTrailingZeros());

    }
    @Test
    public void test_can_add_forex_without_EUR() {
        ForexService forexService = new ForexService();
        forexService.addForex(new Forex("USD", "GBP", BigDecimal.TEN));
        Assertions.assertEquals(1, forexService.getForexValues().size());


    }

}
