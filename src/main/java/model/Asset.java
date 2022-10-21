package model;

import service.ForexService;

import java.math.BigDecimal;

public record Asset(Product product, int quantity) {

    public BigDecimal getAssetValue(ForexService forex){
        var productValue = product.getProductValue(forex);
        return productValue.multiply(BigDecimal.valueOf(quantity));
    }

}
