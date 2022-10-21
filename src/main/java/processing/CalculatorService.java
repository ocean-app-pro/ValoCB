package processing;

import input.DataProvider;
import lombok.extern.log4j.Log4j2;
import model.*;
import service.ForexService;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
public class CalculatorService {

    DataProvider dataProvider;


    public CalculatorService(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    public Map<String, BigDecimal> calculateSumByClient() {
        List<Client> clients = dataProvider.getClients();
        return clients.stream().collect(
                Collectors.toMap(
                        Client::getName,
                        client -> getValueOfAssets(client.getAssets()),
                        BigDecimal::add)
        );
    }

    public Map<String, BigDecimal> calculateSumByPortfolio() {
        Collection<Portfolio> portfolios = dataProvider.getPortfolios();
        return portfolios.stream().collect(
                Collectors.toMap(
                        Portfolio::getName,
                        portfolio -> getValueOfAssets(portfolio.getAssets()),
                        BigDecimal::add )
        );
    }

    public BigDecimal getValueOfAssets(Collection<Asset> assets){
        ForexService forex = dataProvider.getForex();
        return assets.stream()
                .map(asset -> asset.getAssetValue(forex))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
