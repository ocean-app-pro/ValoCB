package model;

import java.math.BigDecimal;

public record Forex(String currencyCode1, String currencyCode2, BigDecimal exchangeRate) {

}
