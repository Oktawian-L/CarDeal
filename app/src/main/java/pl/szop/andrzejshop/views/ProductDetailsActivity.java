package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import org.greenrobot.eventbus.EventBus;

import kotlin.NotImplementedError;
import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.events.NavigationEvent;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;
import pl.szop.andrzejshop.mapper.ViewAdapter;
import pl.szop.andrzejshop.models.BookDetails;
import pl.szop.andrzejshop.models.GenericModel;

public class ProductDetailsActivity extends AppCompatActivity implements ImagesFragment.OnFragmentInteractionListener {

    private ViewAdapter mViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        View view = findViewById(R.id.content);
        Intent intent = getIntent();
        Long productId = intent.getLongExtra("id", 0L);

        BookDetails details = (BookDetails) MyApplication.instance().getDataProvider().getDetails(productId);
        ViewAdapter viewAdapter = new ViewAdapter();
        viewAdapter.addAction(R.id.add_to_cart, (objects, view1) -> addToCart(objects));
        viewAdapter.addCondition(R.id.favorites, (object, view1) -> setWishList(object, view1));
        viewAdapter.bindView(view, details, true);
        mViewAdapter = viewAdapter;
        ImagesFragment imagesFragment = (ImagesFragment) getSupportFragmentManager().findFragmentById(R.id.images_fragment);
        imagesFragment.setImages(details.getId(), null);
    }

    private void buyProduct(Object object){

    }

    private void goToCart(){
        EventBus.getDefault().post(new NavigationEvent(NavigationEvent.View.CART));
    }

    private void setWishList(Object object, View view){
        Long id = ((GenericModel)object).getId();
        boolean isInWishList = MyApplication.instance().getDataProvider().isFavorite(id);
        ((CheckBox)view).setChecked(isInWishList);
    }

    private void addToCart(Object object){
        Long id = ((GenericModel)object).getId();
        // TODO można zbudować własną klasę
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        MyApplication.instance().getDataProvider().addToCart(id);

        // TODO ogarnąć w jaki sposób powinno komunikować się z aktywnością\
        // TODO można to zrobić za pomocą EventBusa

        EventBus.getDefault().post(new UpdateCartSizeEvent());
        // TODO dodać to do zasobów
        builder.setTitle("Dodano")
                .setMessage("Dodano produkt do koszyka")
                .setNegativeButton("Kontynuuj zakupy", (dialog, which) -> dialog.dismiss() )
                .setPositiveButton("Przejdź do koszyka", (dialog, which) -> {
                    finish();
                    goToCart();
                    dialog.dismiss();
                });
        builder.create().show();
    }




    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
