package model;

import java.math.BigDecimal;

public record Underlying(String name, String currencyCode, BigDecimal originPrice) {}
