package input;

import model.Client;
import model.Portfolio;
import model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.ForexService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DataProviderTest {
    static DataProvider provider;
    @BeforeAll
    public static void init() {
        provider =
                new DataProviderImpl(new DataReaderMock(), "forex", "ptf", "client");
    }

    @Test
    public void test_get_forex_method() {
        
        ForexService forex = provider.getForex();
        Assertions.assertEquals(2, forex.getForexValues().size());
    }


    @Test
    public void test_get_client_method() {

        List<Client> clients = provider.getClients();
        Assertions.assertEquals(1, clients.size());
        Assertions.assertEquals(2, clients.get(0).getAssets().size());

    }

    @Test
    public void test_get_ptfs_base_case() {
        List<Portfolio> ptfs = provider.getPortfolios();
        Assertions.assertNotNull(ptfs);
        Assertions.assertEquals(1, ptfs.size());

    }

    private static class DataReaderMock implements DataReader {

        @Override
        public List<List<String>> getData(String path, int size, int nbIndex) {
            List<List<String>> base = new ArrayList<>();

            switch (path) {
                case "forex" -> {

                    String[] line1 = {"EUR", "GBP", "15"};


                    base.add(Arrays.asList(line1));


                }
                case "client" -> {

                    String[] line1 = {"P1", "C1", "15"};
                    String[] line2 = {"P2", "C1", "12"};


                    base.add(Arrays.asList(line1));
                    base.add(Arrays.asList(line2));


                }
                case "ptf" -> {
                    String[] ptfLine1 = {"PTF1", "P1", "U11", "GBP", "10"};
                    String[] ptfLine2 = {"PTF1", "P1", "U12", "GBP", "10"};
                    String[] ptfLine3 = {"PTF1", "P2", "X11", "EUR", "10"};

                    base.add(Arrays.asList(ptfLine1));
                    base.add(Arrays.asList(ptfLine2));
                    base.add(Arrays.asList(ptfLine3));

                }
            }
            return base;
        }
    }

}