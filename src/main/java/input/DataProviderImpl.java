package input;

import lombok.extern.log4j.Log4j2;
import model.*;
import service.ForexService;

import java.math.BigDecimal;
import java.util.*;

@Log4j2
public class DataProviderImpl implements DataProvider {

    final private List<Client> clients = new ArrayList<>();
    final private List<Portfolio> portfolios = new ArrayList<>();
    private ForexService forexService = null;

    private final DataReader dataReader;
    private final String forexPath;
    private final String ptfPath;
    private final String clientPath;

    public  DataProviderImpl(DataReader dataReader, String forexPath, String ptfPath, String clientPath) {
        this.dataReader = dataReader;
        this.forexPath = forexPath;
        this.ptfPath = ptfPath;
        this.clientPath = clientPath;
    }

    @Override
    public ForexService getForex() {
        if (this.forexService == null) {
            final ForexService service = new ForexService();
            this.dataReader.getData(forexPath, 3, 2).stream()
                    .map(x-> new Forex(x.get(0), x.get(1), new BigDecimal(x.get(2))))
                    .forEach(service::addForex);
            this.forexService = service;
        }

        return this.forexService;
    }

    private void processFilesContent(){
        final Map<String, Product> productMap = new HashMap<>();
        final Map<String, Integer> quantityByProductMap = new HashMap<>();
        // file product.csv
        var productsFileContent = this.dataReader.getData(clientPath, 3, 2);

        Map<String, Client> clientMap = new HashMap<>();
        // for each client in client file
        for (var clientRecord: productsFileContent){
            var productName = clientRecord.get(0);
            var clientName = clientRecord.get(1);
            var quantity = Integer.parseInt(clientRecord.get(2));

            Client client = clientMap.computeIfAbsent(clientName, Client::new);
            var product = productMap.computeIfAbsent(productName, Product::new);
            // update total qty of product in circulation
            var oldQuantity = quantityByProductMap.getOrDefault(productName, 0);
            quantityByProductMap.put(productName, oldQuantity + quantity);
            client.addAsset(product, quantity);
        }
        this.clients.addAll(clientMap.values().stream().toList());
        // prices.csv file content
        var pricesFileContent = dataReader.getData(ptfPath, 5, 4);

        Map<String, Set<Product>> productsByPtf = new HashMap<>();
        for (var ptfRecord: pricesFileContent){
            var portfolio = ptfRecord.get(0);
            var productName = ptfRecord.get(1);
            var underlying = ptfRecord.get(2);
            var currency = ptfRecord.get(3);
            var price = new BigDecimal(ptfRecord.get(4));

            Product product = productMap.computeIfAbsent(productName, Product::new);
            product.addUnderlying(new Underlying(underlying, currency, price));
            // create set associated to portfolio map and extract reference
            var portfolioSet = productsByPtf.computeIfAbsent(portfolio, p-> new HashSet<>());
            portfolioSet.add(product);
        }

        for (String ptfByProduct : productsByPtf.keySet()){
            var ptf = new Portfolio(ptfByProduct);
            // for each value in ptf set of product get qty from quantityByProductMap computed before
            for (Product product : productsByPtf.get(ptfByProduct) )
                ptf.addProduct(product, quantityByProductMap.get(product.getName()));
            this.portfolios.add(ptf);
        }
    }
    @Override
    public List<Client> getClients() {
        if (clients.isEmpty()|| portfolios.isEmpty()){
            processFilesContent();
        }
        return clients;
    }

    @Override
    public List<Portfolio> getPortfolios() {
        if (clients.isEmpty()|| portfolios.isEmpty()){
            processFilesContent();
        }
        return portfolios;
    }

}
