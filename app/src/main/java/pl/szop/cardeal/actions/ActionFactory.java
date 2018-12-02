package pl.szop.cardeal.actions;

public class ActionFactory {

    public static Action getAction(String actionName){
        switch (actionName){
            case AddToCartAction.NAME:
                return new AddToCartAction();
            case RemoveFromCartAction.NAME:
                return new RemoveFromCartAction();
            case UpdateAmountAction.NAME:
                return new UpdateAmountAction();
            case DecreaseAmountAction.NAME:
                return new DecreaseAmountAction();

                default:
                    throw new IllegalArgumentException();




        }
    }
}
