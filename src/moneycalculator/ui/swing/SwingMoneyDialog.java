
package moneycalculator.ui.swing;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import moneycalculator.model.Currency;
import moneycalculator.model.Money;
import moneycalculator.ui.MoneyDialog;

public class SwingMoneyDialog extends JPanel implements MoneyDialog {
    private final Currency[] currencies;
    private Currency currency;
    private String amount;

    public SwingMoneyDialog(Currency[] currencies) {        
        this.currencies = currencies;
        this.add(amount());
        this.add(currency());
    }

    
    @Override
    public Money get() {
        return new Money(Double.parseDouble(amount),currency);
    }
    
    private Component amount() {
        JTextField field = new JTextField("100");
        field.setColumns(10);
        field.getDocument().addDocumentListener(amountListener());
        amount = field.getText();
        return field;
    }

    private Component currency() {
        JComboBox combo = new JComboBox(currencies);
        combo.addItemListener(currencyListener());
        currency = (Currency) combo.getSelectedItem();
        return combo;
    }

    private DocumentListener amountListener() {
        return new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateAmount(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateAmount(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateAmount(e);
            }

            private void updateAmount(DocumentEvent e) {
                try {
                    amount = e.getDocument().getText(0, e.getDocument().getLength());
                } catch (BadLocationException ex) {
                    
                }            
            }
        };
    }

    private ItemListener currencyListener() {
        return new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    currency = (Currency)e.getItem();
                }
            }
        };
    }
    
}
