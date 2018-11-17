package pl.szop.cardealshop.rules;

public interface Rule {

    boolean check(Object... objects);
    boolean isNegative();
}
