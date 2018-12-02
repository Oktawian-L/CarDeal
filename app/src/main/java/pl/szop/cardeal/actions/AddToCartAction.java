package pl.szop.cardeal.actions;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import pl.szop.cardeal.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.cardeal.models.CartItem;
import pl.szop.cardeal.models.Product;
import pl.szop.cardeal.views.ProductsListFragment;

public class AddToCartAction implements Action{

    public static final String NAME = "ADD_TO_CART";

    @Override
    public void execute(Object object, Context context){
        EventBus.getDefault().post(new ProductsListFragment.TestEvent());
        ImageButton buyButton = (ImageButton) ((Activity) context).findViewById(R.id.buy_button);
        Button cartButton = (Button) ((Activity) context).findViewById(R.id.CartButton);

         try {
             Long id = null;
             try {
                 id = (Long) ((Product)object).getValue("id");
             } catch (InvocationTargetException e) {
                 e.printStackTrace();
             }
             CartItem ci = new CartItem(id, 1);
             CartItem checkItem = MyApplication.instance().getDataProvider().getItem(id);
             if (checkItem == null) {
                 MyApplication.instance().getDataProvider().addToCart(ci);
                 cartButton.setEnabled(true);
             } else {
                 Toast.makeText(context, "This item is in cart already", Toast.LENGTH_SHORT).show();
             }

             buyButton.setEnabled(false);
         } catch (NoSuchMethodException | IllegalAccessException e) {
                    e.printStackTrace();
                    }
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
