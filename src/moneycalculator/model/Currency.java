package moneycalculator.model;
public class Currency {

    private final String code;
    private final String name;
    private final String symbol;

    public Currency(String code, String country, String symbol) {
        this.code = code;
        this.name = country;
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return code;
    }
   
}
