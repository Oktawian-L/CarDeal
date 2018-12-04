package pl.szop.andrzejshop.actions;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Product;
import pl.szop.andrzejshop.views.ProductsListFragment;

public class AddToCartAction implements Action{

    public static final String NAME = "ADD_TO_CART";

    @Override
    public void execute(Object object, Context context){
        ImageButton buyButton = (ImageButton) ((Activity) context).findViewById(R.id.buy_button);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

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
                 builder.setMessage("You have added item to the cart!")
                         .setCancelable(false)
                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {

                             }
                         });
                 AlertDialog alert = builder.create();
                 alert.show();
             } else {
                 builder.setMessage("This item is already in your cart!")
                         .setCancelable(false)
                         .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int id) {
                             }
                         });
                 AlertDialog alert = builder.create();
                 alert.show();
             }

             buyButton.setEnabled(false);
         } catch (NoSuchMethodException | IllegalAccessException e) {
                    e.printStackTrace();
                    }

    }
}
