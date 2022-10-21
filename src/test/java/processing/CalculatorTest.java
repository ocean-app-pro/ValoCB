package processing;

import input.DataProvider;
import model.Client;
import model.Portfolio;
import model.Product;
import model.Underlying;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.ForexService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CalculatorTest {
    static CalculatorService calculatorService;

    @BeforeAll
    public static void init() {
        calculatorService = new CalculatorService(new DataProviderTestImpl(), null);
    }

    @Test
    public void test_calculate_sum_by_client() {
        Map<String, BigDecimal> res = calculatorService.calculateSumByClient();
        Assertions.assertEquals(2, res.size());
        Assertions.assertTrue(res.containsKey("C1"));
        Assertions.assertTrue(res.containsKey("C2"));
        Assertions.assertEquals(BigDecimal.valueOf(55), res.get("C1"));
        Assertions.assertEquals(BigDecimal.valueOf(10), res.get("C2"));
    }

    @Test
    public void test_calculate_sum_by_ptf() {
        Map<String, BigDecimal> res = calculatorService.calculateSumByPortfolio();
        Assertions.assertEquals(1, res.size());
        Assertions.assertTrue(res.containsKey("PTF1"));
        Assertions.assertEquals(BigDecimal.valueOf(65), res.get("PTF1"));
    }
    private static class DataProviderTestImpl implements DataProvider {

        @Override
        public ForexService getForex() {
            return new ForexService();
        }

        @Override
        public List<Client> getClients() {
            Underlying u1 = new Underlying("U1", "EUR", BigDecimal.TEN);
            Underlying u2 = new Underlying("U2", "EUR", BigDecimal.ONE);
            Product p1 = new Product("P1");
            p1.addUnderlying(u1);
            p1.addUnderlying(u2);
            Client c1 = new Client("C1");
            c1.addAsset(p1, 5);

            Underlying u3 = new Underlying("U3", "EUR", BigDecimal.TEN);
            Product p2 = new Product("P2");
            p2.addUnderlying(u3);
            Client c2 = new Client("C2");
            c2.addAsset(p2, 1);
            return Arrays.asList(c1, c2);
        }

        @Override
        public List<Portfolio> getPortfolios() {
            Portfolio p1 = new Portfolio("PTF1");

            Underlying u1 = new Underlying("U1", "EUR", BigDecimal.TEN);
            Underlying u2 = new Underlying("U2", "EUR", BigDecimal.ONE);
            Product product = new Product("P1");
            product.addUnderlying(u1);
            product.addUnderlying(u2);
            p1.addProduct(product, 5);

            Underlying u3 = new Underlying("U3", "EUR", BigDecimal.TEN);
            Product product2 = new Product("P2");
            product2.addUnderlying(u3);

            p1.addProduct(product2, 1);

            return List.of(p1);
        }
    }
}
