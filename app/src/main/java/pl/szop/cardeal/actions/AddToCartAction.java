package pl.szop.cardeal.actions;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import pl.szop.cardeal.models.Product;
import pl.szop.cardeal.views.ProductsListFragment;

public class AddToCartAction implements Action{

    public static final String NAME = "ADD_TO_CART";

    @Override
    public void execute(Object product, Context context){
        Product prod = (Product) product;
        EventBus.getDefault().post(new ProductsListFragment.TestEvent());
//        try {
//            Toast.makeText(context, (String)prod.getValue("title"), Toast.LENGTH_SHORT).show();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

    }
}
