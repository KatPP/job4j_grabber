package report.currency;

import report.currency.Currency;

public interface CurrencyConverter {
    double convert(Currency source, double sourceValue, Currency target);
}
