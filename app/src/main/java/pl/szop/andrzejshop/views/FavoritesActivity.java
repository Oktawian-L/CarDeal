package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.actions.UsunZUlubionych;
import pl.szop.andrzejshop.adapters.ProductsAdapter;
import pl.szop.andrzejshop.models.Auto;
import pl.szop.andrzejshop.models.Favorites;

public class FavoritesActivity extends AppCompatActivity {

    private RecyclerView cFavoritesList;
    private ProductsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarfav);
        setSupportActionBar(toolbar);
        initComponents();
    }

   private void initComponents(){
        cFavoritesList = findViewById(R.id.favorites_list);
        List<Favorites> favorites = (List<Favorites>) MyApplication.instance().getDataProvider().getFavorites();
        List<Auto> products = new ArrayList<>();
        for (Favorites fav : favorites){
           products.add(fav.getAuto());
        }
        ProductsAdapter adapter = new ProductsAdapter(this, R.layout.item_favourites, new ArrayList<>(), productId -> {});
        adapter.addAction(R.id.remove_button, UsunZUlubionych.NAME);
        cFavoritesList.setAdapter(adapter);
        setListLayout(cFavoritesList);
        mAdapter = adapter;
        mAdapter.setItems(products);
    }

    private void setListLayout(RecyclerView productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setItemAnimator(new DefaultItemAnimator());
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
