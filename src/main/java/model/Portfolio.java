package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Portfolio {
    private final String name;
    private final List<Asset> assets;

    public Portfolio(String name)  {
        this.name = name;
        this.assets = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public List<Asset> getAssets() {
        return assets;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portfolio portfolio = (Portfolio) o;
        return name.equals(portfolio.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void addProduct(Product product, int quantity) {
        this.assets.add( new Asset(product, quantity));
    }
}
