package pl.szop.andrzejshop.actions;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Product;
import pl.szop.andrzejshop.views.ProductsListFragment;

public class DecreaseAmountAction implements Action {
    public static final String NAME = "DECREASE_AMOUNT";

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
            Button buyButton = (Button) ((Activity) context).findViewById(R.id.CartButton);
            //Button subtractButton = (Button) ((Activity) context).findViewById(R.id.minus);
            CartItem cartItem = MyApplication.instance().getDataProvider().getItem(id);
            int qty = cartItem.getAmount();
            double price = cartItem.getBook().getPrice();
            double newPrice = (double) qty * price;
            cartItem.setPrice(newPrice);
            cartItem.setAmount(qty - 1);
           /* if ((qty -1) < 1) {
                subtractButton.setEnabled(false);
                Toast.makeText(context, "You removed all items", Toast.LENGTH_SHORT).show();
            } else {
                subtractButton.setEnabled(true);
                Toast.makeText(context, "You removed 1 items", Toast.LENGTH_SHORT).show();
            }*/

            MyApplication.instance().getDataProvider().updateAmount(cartItem);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
