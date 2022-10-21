package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PortfolioTest {

    @Test
    public void check_equals_hashcode_based_on_name() {
        Portfolio p1 = new Portfolio("ptf1");
        Portfolio p2 = new Portfolio("ptf1");


        Assertions.assertEquals(p1, p2);
        Assertions.assertEquals(p1.hashCode(), p2.hashCode());

        Portfolio p3 = new Portfolio("ptf");
        Portfolio p4 = new Portfolio("ptf1");

        Assertions.assertNotEquals(p3, p4);
        Assertions.assertNotEquals(p3.hashCode(), p4.hashCode());

        Assertions.assertEquals(p3, p3);
        Assertions.assertNotEquals(p3, null);


        Underlying uu = new Underlying("ptf1", "EUR", BigDecimal.TEN);
        Assertions.assertNotEquals(p3, uu);

 }
    @Test
    public void test_get_name() {
        Portfolio p = new Portfolio("ptf");
        Assertions.assertEquals("ptf", p.getName());
    }
    @Test
    public void test_on_product_value() {


    }
}