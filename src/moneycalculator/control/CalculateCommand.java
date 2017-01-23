package moneycalculator.control;

import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.persistence.ExchangeRateLoader;
import moneycalculator.ui.MoneyDialog;
import moneycalculator.ui.MoneyDisplay;


public class CalculateCommand implements Command{

    private final MoneyDisplay moneyDisplay;
    private final MoneyDialog moneyDialog;
    private final ExchangeRateLoader loader;
    private Currency eur = new Currency("EUR", "Euro", "€");

    public CalculateCommand(MoneyDisplay moneyDisplay, MoneyDialog moneyDialog, ExchangeRateLoader loader) {
        this.moneyDisplay = moneyDisplay;
        this.moneyDialog = moneyDialog;
        this.loader = loader;
    }
    
    @Override
    public String name() {
        return "calculate";
    }
    
    @Override
    public void execute() {
        moneyDisplay.display(exchange(moneyDialog.get()));
    }

    private Money exchange(Money money) {
        return new Money(money.getAmount()*rateOf(money.getCurrency()),eur);
    }

    private double rateOf(Currency currency) {
        return loader.load(currency, eur).getAmount();
    }

}
