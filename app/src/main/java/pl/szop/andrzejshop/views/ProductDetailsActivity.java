package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ViewAdapter;
import pl.szop.andrzejshop.models.AutoDetails;

public class ProductDetailsActivity extends AppCompatActivity implements ImagesFragment.OnFragmentInteractionListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageView img23 = (ImageView) findViewById(R.id.jednozdjecie);
        img23.setBackgroundResource(R.drawable.camaro3);
    }
    public void showGaleriePrzewijana(View view)
    {
        Intent intent = new Intent(this,ViewPagerActivity.class);
        startActivity(intent);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
