package pl.szop.cardeal.actions;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;

import pl.szop.cardeal.MyApplication;
import pl.szop.cardeal.models.Product;

public class RemoveFromCartAction implements Action{

    public static final String NAME = "REMOVE_FROM_FAVORITES";


    @Override
    public void execute(Object object, Context context) {
        try {
            Long id = (Long) ((Product)object).getValue("id");
            MyApplication.instance().getDataProvider().removeFromCart(id);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
