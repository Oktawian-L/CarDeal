package pl.szop.andrzejshop.views;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ImageAdapterSlide;

public class ViewPagerActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_pager);
            //toolbar start
            Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbarviewpager);
            setSupportActionBar(myToolbar);
            //toolbar end
          //  setSupportActionBar(myToolbar);
            ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
            ImageAdapterSlide adapterView = new ImageAdapterSlide(this);
            mViewPager.setAdapter(adapterView);



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
