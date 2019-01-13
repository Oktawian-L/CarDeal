package pl.szop.andrzejshop.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ImageAdapterSlide;
import pl.szop.andrzejshop.models.Category;
import pl.szop.andrzejshop.models.Favorites;
import pl.szop.andrzejshop.models.Product;

public class ButtonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buttons);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        ImageView img23 = (ImageView) findViewById(R.id.splash);
        img23.setBackgroundResource(R.drawable.camaro);
        setSupportActionBar(myToolbar);
    }


    private void startButtonsActivity(String cat) {
        Intent intent = new Intent(this, ButtonsActivity.class);
        startActivity(intent);
    }

    public void showCategory(View view)
    {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
    public void showProductList(View view)
    {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }
    public void showBasicList(View view)
    {
        Intent intent = new Intent(this, Dialogsorting.class);
        startActivity(intent);
    }
    public void showFavourites(View view)
    {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }
    public void showAuto(View view)
    {
        Intent intent = new Intent(this, AutoActivity.class);
        startActivity(intent);
    }
    public void showGridList(View view)
    {
        Log.v("1","odpalam view");
        Intent intent = new Intent(this,GridActivity.class);
        startActivity(intent);
        //setContentView(R.layout.activity_main);

    }
    public void showGallery(View view)
    {
        Intent intent = new Intent(this,ViewPagerActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;

    }
    // Respond to menu item clicks
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
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), GridActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fadeIn(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1500);
        view.startAnimation(anim);
        view.setVisibility(View.VISIBLE);
    }
    public void imageClick(View view) {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }
}
