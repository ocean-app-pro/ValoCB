package service;

import lombok.extern.log4j.Log4j2;
import model.Forex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
 @Log4j2
public class ForexService {

    public Map<String, Forex> forexValues;

    public Map<String, Forex> getForexValues() {
        return forexValues;
    }
    private static final String BASE_CURRENCY = "EUR";
    public ForexService() {
        forexValues = new HashMap<>();
        forexValues.put(BASE_CURRENCY,
                new Forex(BASE_CURRENCY, BASE_CURRENCY, BigDecimal.ONE));
    }
    public void addForex(Forex f) {
        if (f.currencyCode1().equals(BASE_CURRENCY)) {
            forexValues.put(f.currencyCode2(), f);
        } else if (f.currencyCode2().equals(BASE_CURRENCY)) {
            forexValues.put(f.currencyCode1(),
                    new Forex(f.currencyCode2(), f.currencyCode1(), BigDecimal.ONE.divide(f.exchangeRate(), 4, RoundingMode.CEILING)));
        } else {
            log.warn("Could not convert to/from {}, skipping record {}", BASE_CURRENCY, f.toString());
        }
    }
    public BigDecimal getRate(String currencyCode){
        return forexValues.get(currencyCode).exchangeRate();
    }


}
