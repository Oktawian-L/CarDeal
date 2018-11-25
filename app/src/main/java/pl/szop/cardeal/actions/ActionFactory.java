package pl.szop.cardeal.actions;

public class ActionFactory {

    public static Action getAction(String actionName){
        switch (actionName){
            case AddToCartAction.NAME:
                return new AddToCartAction();
                default:
                    throw new IllegalArgumentException();
        }
    }
}