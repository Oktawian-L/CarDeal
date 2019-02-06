package pl.szop.andrzejshop.views;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.events.NavigationEvent;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;

// TODO refactor
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ProductsListFragment.OnFragmentInteractionListener,
        CartFragment.OnFragmentInteractionListener,
        CategoriesFragment.OnFragmentInteractionListener,
        WishListFragment.OnFragmentInteractionListener,
        HomeFragment.OnFragmentInteractionListener,
        BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView cBottomNavigationView;
    private BottomNavigationItemView cCartView;

    private Criteria mCurrentCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        cBottomNavigationView = findViewById(R.id.bottom_navigation);
        cBottomNavigationView.setOnNavigationItemSelectedListener(this);


        mCurrentCriteria = new Criteria(); // TODO sprawdzić, czy w tym miejscu nie będzie się resetować
        loadFragment(new HomeFragment());
        cBottomNavigationView.setSelectedItemId(R.id.navigation_main);

        EventBus.getDefault().register(this); // TODO obsłużyć wyrejestrowywanie
    }

    @Override
    public void onResume(){
        super.onResume();
        updateCartSize(null);
    }

    @Subscribe
    public void updateCartSize(UpdateCartSizeEvent event){

        cCartView = cBottomNavigationView.findViewById(R.id.navigation_cart);

        int cartSize = MyApplication.instance().getDataProvider().getCartSize();
        if(cCartView.getChildCount() > 2){
            cCartView.removeView(cCartView.getChildAt(2)); // remove previous badge
        }
        if(cartSize > 0){
            View badge = LayoutInflater.from(this).inflate(R.layout.badge_cart, null, false);
            TextView numberTextView = badge.findViewById(R.id.number);
            numberTextView.setText(String.valueOf(cartSize));
            cCartView.addView(badge);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mCurrentCriteria.setText(query);
                cBottomNavigationView.setSelectedItemId(R.id.navigation_search);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_action) {

            // TODO dorobić obsługę wyszukiwania
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        // TODO sprawdzić, czy nie będzie konieczne dodanie currentCriteria
        switch (item.getItemId()){
            // BOTTOM NAVIGATION EVENTS ----------------------------
            case R.id.navigation_categories:
                fragment = new CategoriesFragment();
                break;
            case R.id.navigation_wish_list:
                fragment = new WishListFragment();
                break;
            case R.id.navigation_main:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_search:
                ProductsListFragment productsFragment = new ProductsListFragment();
                productsFragment.setCriteria(mCurrentCriteria);
                fragment =productsFragment;
                break;
            case R.id.navigation_cart:
                fragment = new CartFragment();
                break;
            // DRAWER EVENTS -----------------------------------------
            // this events run bottom navigation menu events
            case R.id.nav_categories:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_categories);
                break;
            case R.id.nav_wish_list:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_wish_list);
                break;
            case R.id.nav_main:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_main);
                break;
            case R.id.nav_all_products:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_search);
                break;
            case R.id.nav_cart:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_cart);
                break;
                // TODO dorobić resztę
        }
        if(fragment != null){
            loadFragment(fragment);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Subscribe
    public void changeView(NavigationEvent event) {
        if(event.getCriteria() != null){
            mCurrentCriteria = event.getCriteria();
        }
        switch (event.getView()) {
            case CART:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_cart);
                break;
            case PRODUCTS:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_search);
                break;
            case CATEGORIES:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_categories);
                break;
            case MAIN:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_main);
                break;
            case WISH_LIST:
                cBottomNavigationView.setSelectedItemId(R.id.navigation_wish_list);
                break;
                default:
                    throw new IllegalArgumentException("Unknown fragment type");
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if(fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        // TODO ogarnąć jak to działa
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
