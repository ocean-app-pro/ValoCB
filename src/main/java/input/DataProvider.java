package input;


import model.Client;
import model.Portfolio;
import service.ForexService;

import java.util.List;

public interface DataProvider {
     ForexService getForex();
     List<Client> getClients();
     List<Portfolio> getPortfolios();

}
