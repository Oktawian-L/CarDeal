package pl.szop.cardeal.rules;

import pl.szop.cardeal.models.Product;

public class BoughtRule implements Rule {

    public static final String NAME = "BUYED";
    private boolean mNegative;

    public BoughtRule(boolean negative){
        mNegative = negative;
    }

    @Override
    public boolean check(Object... objects) {
        assert objects.length == 1;
        Product product = (Product) objects[0];

        // TODO dodać sprawdzanie, czy obiekt jest kupiony
        return !mNegative;
    }

    @Override
    public boolean isNegative() {
        return mNegative;
    }
}