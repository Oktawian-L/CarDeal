package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ViewAdapter;
import pl.szop.andrzejshop.models.AutoDetails;

public class ProductDetailsActivity extends AppCompatActivity implements ImagesFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        View view = findViewById(R.id.content);
        Intent intent = getIntent();
        Long productId = intent.getLongExtra("id", 0L);

        AutoDetails details = (AutoDetails) MyApplication.instance().getDataProvider().getDetails(productId);
        ViewAdapter.bindView(view, details);
        ImagesFragment imagesFragment = (ImagesFragment) getSupportFragmentManager().findFragmentById(R.id.images_fragment);
        imagesFragment.setImages(details.getId(), null);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
