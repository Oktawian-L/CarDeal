package pl.szop.andrzejshop.actions;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Product;
import pl.szop.andrzejshop.views.CartActivity;
import pl.szop.andrzejshop.views.ProductsListFragment;

public class UpdateAmountAction implements Action {
    public static final String NAME = "UPDATE_AMOUNT";

    @Override
    public void execute(Object object, Context context){
        try {
            Long id = null;
            try {
                id = (Long) ((Product)object).getValue("id");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            CartItem cartItem = MyApplication.instance().getDataProvider().getItem(id);
            int qty = cartItem.getAmount();
            double price = cartItem.getBook().getPrice();
            double newPrice = (double) cartItem.getBook().getPrice() +  price;
            int newQty = qty  + 1;
            cartItem.setAmount(newQty);
            newPrice = newPrice * 100;
            newPrice = Math.round(newPrice);
            newPrice = newPrice / 100;
            cartItem.setPrice(newPrice);
            MyApplication.instance().getDataProvider().updateAmount(cartItem);
            EventBus.getDefault().post( new CartActivity.TestEvent());


        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
