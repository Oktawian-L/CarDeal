package pl.szop.andrzejshop.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.events.NavigationEvent;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;
import pl.szop.andrzejshop.mapper.ViewAdapter;
import pl.szop.andrzejshop.models.BookDetails;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.utils.ViewsCommon;

public class ProductDetailsActivity extends AppCompatActivity implements ImagesFragment.OnFragmentInteractionListener {

    private ImageButton cCartButton;
    private CheckBox cWishListButton;
    private ImageButton cShareButton;
    private ImageButton cPreviousButton;

    private Long mProductId;
    private BookDetails mDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        View view = findViewById(R.id.content);
        Intent intent = getIntent();
        mProductId = intent.getLongExtra("id", 0L);

        mDetails = (BookDetails) MyApplication.instance().getDataProvider().getDetails(mProductId);
        if(mDetails == null){
            showErrorDialog();
        } else {
            initComponents();

            ViewAdapter viewAdapter = new ViewAdapter();
            viewAdapter.addAction(R.id.add_to_cart, (objects, view1) -> addToCart(objects));
            viewAdapter.addCondition(R.id.favorites, (object, view1) -> setWishList(object, view1));
            viewAdapter.addCondition(R.id.price, (object, view1) -> {
                Map<Long, Promotions> promotions = new HashMap<>();
                Promotions promotion = MyApplication.instance().getDataProvider().getPromotion(mProductId);
                if(promotion != null){
                    promotions.put(mProductId, promotion);
                }
                ViewsCommon.setPriceText(object, view1, promotions);
            });
            viewAdapter.bindView(view, mDetails, true);

            ImagesFragment imagesFragment = (ImagesFragment) getSupportFragmentManager().findFragmentById(R.id.images_fragment);
            imagesFragment.setImages(mDetails.getId(), null);

            // show content
            findViewById(R.id.content).setVisibility(View.VISIBLE);
        }
    }

    private void showErrorDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Błąd");
        // TODO zmienić ten komunikat
        alertDialog.setMessage("Wystąpił błąd. Wrócimy do poprzedniego ekranu");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", (dialog, which) -> finish());
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }

    private void initComponents(){
        cCartButton = findViewById(R.id.add_to_cart);
        cWishListButton = findViewById(R.id.add_to_wish_list);
        cShareButton = findViewById(R.id.share);
        cPreviousButton = findViewById(R.id.previous);

        updateCart(mProductId);
        cWishListButton.setChecked(isInWishList());

        cCartButton.setOnClickListener(v -> addToCart(mDetails));
        cCartButton.setOnLongClickListener(v->{
//            EventBus.getDefault().post(new NavigationEvent(NavigationEvent.View.CART));
            Intent intent = this.getIntent();
            intent.putExtra("navigation", 1);
            this.setResult(RESULT_OK, intent);
            finish();
            return true;
        });
        cWishListButton.setOnClickListener(v -> MyApplication.instance().getDataProvider().setFavorite(mProductId, cWishListButton.isChecked()));
        cShareButton.setOnClickListener(v->share());
        cPreviousButton.setOnClickListener(v->finish());
    }

    private void share(){
        try{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "Księgarnia");
            intent.putExtra(Intent.EXTRA_TEXT, "https://księgarnia.pl/książka1");
            startActivity(Intent.createChooser(intent, "Udostępnij link"));
        } catch (Exception ignored){

        }
    }

    private boolean isInWishList(){
        return MyApplication.instance().getDataProvider().isFavorite(mProductId);
    }

    private void goToCart(){
        EventBus.getDefault().post(new NavigationEvent(NavigationEvent.View.CART));
    }

    private void setWishList(Object object, View view){
        Long id = ((GenericModel)object).getId();
        boolean isInWishList = MyApplication.instance().getDataProvider().isFavorite(id);
        ((CheckBox)view).setChecked(isInWishList);
    }

    private void addToCart(Object object) {
        Long id = ((GenericModel) object).getId();
        if(MyApplication.instance().getDataProvider().isInCart(id)){
            MyApplication.instance().getDataProvider().removeFromCart(id);
            Toast.makeText(this, "Usunięto z koszyka", Toast.LENGTH_SHORT).show();
        } else {
            MyApplication.instance().getDataProvider().addToCart(id);
            Toast.makeText(this, "Dodano do koszyka", Toast.LENGTH_SHORT).show();
        }
        // TODO to jest chyba niepotrzebne
        updateCart(id);
        EventBus.getDefault().post(new UpdateCartSizeEvent());

//        createAddToCartDialog(id);
    }

    private void createAddToCartDialog(Long id) {
        new AddedToCartDialog(
                (dialog, which) -> goToCart(),
                (dialog, which) -> updateCart(id))
                .makeDialog(this);
    }

    private void addToCart(Long id) {
        MyApplication.instance().getDataProvider().addToCart(id);
        EventBus.getDefault().post(new UpdateCartSizeEvent());
    }

    private void updateCart(Long id){
        if(MyApplication.instance().getDataProvider().isInCart(id)){
            cCartButton.setColorFilter(Color.GREEN);
        } else {
            cCartButton.setColorFilter(Color.BLUE);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
