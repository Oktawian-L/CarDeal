package pl.szop.andrzejshop.actions;

public class ActionFactory {

    public static Action getAction(String actionName){
        switch (actionName){
            case RemoveFromFavoritesAction.NAME:
                return new RemoveFromFavoritesAction();
            case AddToCartAction.NAME:
                return new AddToCartAction();
            case RemoveFromCartAction.NAME:
                return new RemoveFromCartAction();
            case UpdateAmountAction.NAME:
                return new UpdateAmountAction();
            case DecreaseAmountAction.NAME:
                return new DecreaseAmountAction();
            case AddToFavoritesAction.NAME:
                return new AddToFavoritesAction();
                default:
                    throw new IllegalArgumentException();
        }
    }
}
