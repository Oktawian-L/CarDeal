package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
        setContentView(R.layout.activity_auto);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbarautopoj);
        setSupportActionBar(toolbar);
        ImageView img23 = (ImageView) findViewById(R.id.jednozdjecie);
        img23.setBackgroundResource(R.drawable.tiburon);
    }
    public void showGaleriePrzewijana(View view)
    {
        Intent intent = new Intent(this,ViewPagerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miHome:
                startActivity(new Intent(getApplicationContext(), CategoryActivity.class));
                return true;
            case R.id.miProfile:
                startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
                return true;
            case R.id.miGrid:
                startActivity(new Intent(getApplicationContext(), GridActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), ViewPagerActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
