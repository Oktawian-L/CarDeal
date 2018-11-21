package pl.szop.cardealshop.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.szop.cardealshop.MyApplication;
import pl.szop.cardealshop.R;
import pl.szop.cardealshop.adapters.ViewAdapter;
import pl.szop.cardealshop.models.CarDetails;
import pl.szop.cardealshop.models.CarDetails;
import pl.szop.cardealshop.models.Product;

public class ProductDetailsActivity extends AppCompatActivity implements ImagesFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        View view = findViewById(R.id.content);
        Intent intent = getIntent();
        Long productId = intent.getLongExtra("id", 0L);

        CarDetails details = (CarDetails) MyApplication.instance().getDataProvider().getDetails(productId);
        ViewAdapter.bindView(view, details);
        ImagesFragment imagesFragment = (ImagesFragment) getSupportFragmentManager().findFragmentById(R.id.images_fragment);
        imagesFragment.setImages(details.getId(), null);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
