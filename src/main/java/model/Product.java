package model;
import service.ForexService;

import java.math.BigDecimal;
import java.util.*;

public class Product {

    private final String name;
    private final Set<Underlying> underlyings;

    public Set<Underlying> getUnderlyings() {
        return underlyings;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public Product(String name){
        this.name = name;
        underlyings = new HashSet<>();
    }
    public BigDecimal getProductValue(ForexService forex) {
        BigDecimal result = BigDecimal.ZERO;
        for (Underlying underlying : underlyings) {
            result = result.add(underlying.originPrice().multiply(forex.getRate(underlying.currencyCode())));
        }
        return result;
    }

    public void addUnderlying(Underlying u) {
        underlyings.add(u);
    }

}
