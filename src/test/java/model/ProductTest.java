package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.ForexService;

import java.math.BigDecimal;

public class ProductTest {

    static ForexService forexService;

    @BeforeAll
    public static void init(){
        forexService = new ForexService();
        forexService.addForex(new Forex("EUR", "GBP", BigDecimal.TEN));
        forexService.addForex(new Forex("USD", "EUR", BigDecimal.ONE));
    }

    @Test
    public void test_equals_hashcode_product() {
        Product product1 = new Product("P1");
        Product product2 = new Product("P1");

        Assertions.assertEquals(product1,product2);
        Assertions.assertEquals(product1.hashCode(), product2.hashCode());
        Assertions.assertEquals(product1, product1);
        Assertions.assertNotEquals(product1, null);

        Underlying uu = new Underlying("product", "EUR", BigDecimal.TEN);
        Assertions.assertNotEquals(product1, uu);
    }

    @Test
    public void test_product_creation(){
        Product product1 = new Product("P1");
        Assertions.assertEquals("P1", product1.getName());
        Assertions.assertTrue(product1.getUnderlyings().isEmpty());
    }

    @Test
    public void test_product_worth(){
        Underlying underlying1 = new Underlying("U11", "GBP", BigDecimal.ONE);
        Underlying underlying2 = new Underlying("U12", "USD", BigDecimal.ONE);

        Product product1 = new Product("P1");
        product1.addUnderlying(underlying1);
        product1.addUnderlying(underlying2);
        Assertions.assertEquals(2, product1.getUnderlyings().size());
        Assertions.assertEquals(BigDecimal.valueOf(11), product1.getProductValue(forexService).stripTrailingZeros());
    }
}
