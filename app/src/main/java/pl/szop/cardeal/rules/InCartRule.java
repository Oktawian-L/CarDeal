package pl.szop.cardeal.rules;

public class InCartRule implements Rule{

    public static final String NAME = "IN_CART";

    private boolean mNegative;

    public InCartRule(boolean negative){
        mNegative = negative;
    }

    @Override
    public boolean check(Object... objects) {
        return !mNegative;
    }

    @Override
    public boolean isNegative() {
        return mNegative;
    }
}
