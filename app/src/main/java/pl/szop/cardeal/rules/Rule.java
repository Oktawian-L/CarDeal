package pl.szop.cardeal.rules;

public interface Rule {

    boolean check(Object... objects);
    boolean isNegative();
}
