package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.ForexService;

import java.math.BigDecimal;

public class AssetTest {
    static ForexService forexService;
    @BeforeAll
    public static void init() {
        forexService = new ForexService();
        forexService.addForex(new Forex("EUR", "GBP", BigDecimal.TEN));
    }

    @Test
    public static void test_asset() {
        Product p = new Product("P1");
        Underlying u = new Underlying("U11", "GBP", BigDecimal.ONE);
        Asset asset = new Asset(p, 10);
        Assertions.assertEquals(asset.getAssetValue(forexService), 100);
        Assertions.assertEquals(p, asset.product());
        Assertions.assertEquals(100, asset.quantity());
    }
}
