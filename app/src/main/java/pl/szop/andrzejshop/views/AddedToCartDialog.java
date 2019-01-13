package pl.szop.andrzejshop.views;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class AddedToCartDialog  {

    private DialogInterface.OnClickListener mGoToCartListener;
    private DialogInterface.OnClickListener mContinueShoppingListener;

    public AddedToCartDialog(DialogInterface.OnClickListener goToCartListener, DialogInterface.OnClickListener continueShoppingListener){
        mGoToCartListener = goToCartListener;
        mContinueShoppingListener = continueShoppingListener;
    }


    public void makeDialog(Context context){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Dodano")
                .setMessage("Dodano produkt do koszyka")
                .setNegativeButton("Kontynuuj zakupy", (dialog, which) -> mContinueShoppingListener.onClick(dialog, which))
                .setPositiveButton("Przejdź do koszyka", (dialog, which) -> mGoToCartListener.onClick(dialog, which));
        builder.create().show();
    }
}
