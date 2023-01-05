package enums;

import java.math.BigDecimal;

public enum TaxiType {
    STANDART(new BigDecimal(100),new BigDecimal(125)),
    COMFORT(new BigDecimal(120),new BigDecimal(300)),
    BUSINESS(new BigDecimal(83),new BigDecimal(100));
    private BigDecimal pricePerKm;
    private BigDecimal priceForLanding;

    TaxiType(BigDecimal pricePerKm, BigDecimal priceForLanding) {
        this.pricePerKm = pricePerKm;
        this.priceForLanding = priceForLanding;}
}
