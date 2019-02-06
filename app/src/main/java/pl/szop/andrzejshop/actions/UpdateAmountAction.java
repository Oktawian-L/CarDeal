package pl.szop.andrzejshop.actions;

import android.content.Context;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.views.ProductsListFragment;

public class UpdateAmountAction {
    public static final String NAME = "UPDATE_AMOUNT";


    public void execute(Object object, Context context){
        try {
            Long id = null;
            try {
                id = (Long) ((GenericModel)object).getValue("id");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            CartItem cartItem = MyApplication.instance().getDataProvider().getItem(id);
            int qty = cartItem.getAmount();
            double price = cartItem.getBook().getPrice();
            double newPrice = (double) qty * price;
            cartItem.setAmount(qty + 1);
            cartItem.setPrice(newPrice);
            MyApplication.instance().getDataProvider().updateAmount(cartItem);
            Toast.makeText(context, "You added another item to cart", Toast.LENGTH_SHORT).show();
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
