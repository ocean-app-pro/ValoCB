package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void test_client() {
        Client c = new Client("C1");
        Assertions.assertTrue(c.getAssets().isEmpty());

        c.addAsset(new Product("P1"), 10);
        Assertions.assertEquals(1, c.getAssets().size());


        Assertions.assertEquals("C1", c.getName());
    }
}