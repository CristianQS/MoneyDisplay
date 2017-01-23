package moneycalculator.persistence;

import moneycalculator.model.Currency;

public interface CurrencyListLoader {
    Currency[] currencies();
}
