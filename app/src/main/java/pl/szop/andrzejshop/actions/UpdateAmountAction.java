package pl.szop.andrzejshop.actions;

import android.content.Context;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Product;
import pl.szop.andrzejshop.views.ProductsListFragment;

public class UpdateAmountAction implements Action {
    public static final String NAME = "UPDATE_AMOUNT";

    @Override
    public void execute(Object object, Context context){
        EventBus.getDefault().post(new ProductsListFragment.TestEvent());
        try {
            Long id = null;
            try {
                id = (Long) ((Product)object).getValue("id");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            CartItem cartItem = MyApplication.instance().getDataProvider().getItem(id);
            int qty = cartItem.getAmount();
            double price = cartItem.getAuto().getPrice();
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
