package pl.szop.andrzejshop.actions;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Product;
import pl.szop.andrzejshop.views.CartActivity;

public class RemoveFromCartAction implements Action{

    public static final String NAME = "REMOVE_FROM_CART";


    @Override
    public void execute(Object object, Context context) {
        try {
            Long id = (Long) ((Product)object).getValue("id");
            MyApplication.instance().getDataProvider().removeFromCart(id);
            EventBus.getDefault().post(new CartActivity.TestEvent());

        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
