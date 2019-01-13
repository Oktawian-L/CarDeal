package pl.szop.andrzejshop.actions;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.models.GenericModel;

public class RemoveFromCartAction {

    public static final String NAME = "REMOVE_FROM_CART";



    public void execute(Object object, Context context) {
        try {
            Long id = (Long) ((GenericModel)object).getValue("id");
            MyApplication.instance().getDataProvider().removeFromCart(id);

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
