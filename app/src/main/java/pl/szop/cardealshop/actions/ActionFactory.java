package pl.szop.cardealshop.actions;

public class ActionFactory {

    public static pl.szop.andrzejshop.actions.Action getAction(String actionName){
        switch (actionName){
            case AddToCartAction.NAME:
                return new AddToCartAction();
                default:
                    throw new IllegalArgumentException();
        }
    }
}
