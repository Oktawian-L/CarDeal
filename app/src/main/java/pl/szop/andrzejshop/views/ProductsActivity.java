package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.adapters.SortingAdapter;
import pl.szop.andrzejshop.data.Filter;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.data.Sort;
import pl.szop.andrzejshop.models.CartItem;

public class ProductsActivity extends AppCompatActivity implements ProductsListFragment.OnFragmentInteractionListener {

    private Button cFilterButton;
    private Button cSortButton;
    private ImageView cChangeViewButton;

    private Toolbar cToolbar;

    private EditText cSearchEditText;
    private ImageButton cSearchButton;

    private ProductsListFragment mFragment;

    private Button btnCat;
    private Button btnCart;
    private String category = "";

    private Filter mCurrentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mCurrentFilter = new Filter();
        setContentView(R.layout.activity_products_list);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
//        createActionBar();
        Bundle b = getIntent().getExtras();
        String cat = null;
        if (b != null) {
            cat = b.getString("categoryType");
        }
        TextView myButton = (TextView) findViewById(R.id.categoryText);
        if (cat != null) {
            category = cat;
            myButton.setVisibility(View.VISIBLE);
            myButton.setText("Category: " + cat);
        } else {
            myButton.setVisibility(View.GONE);
        }

        btnCat  = findViewById(R.id.cat_button);
        btnCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotToCategoryActivity();
            }
        });
        btnCart = findViewById(R.id.CartButton);
        List<CartItem> cartitems = (List<CartItem>) MyApplication.instance().getDataProvider().getCartItems();
        if (cartitems.isEmpty()) {
            btnCart.setEnabled(false);
       }

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCartActivity();
            }
        });

        initComponents();

        Bundle bundle = new Bundle();
        bundle.putString("categoryName", category);
        ProductsListFragment prodList = new ProductsListFragment();
        prodList.setArguments(bundle);
        loadFragment(prodList);

    }
    private void gotToCategoryActivity() {
        Intent intent = new Intent(ProductsActivity.this, CategoryActivity.class);
        startActivity(intent);
    }
    private void startCartActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }

    private void createActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setCustomView(R.layout.action_bar);
        cSearchEditText = actionBar.getCustomView().findViewById(R.id.search_edit_text);
        cSearchButton = actionBar.getCustomView().findViewById(R.id.search_button);
    }

    private void initComponents(){
        cFilterButton = findViewById(R.id.filter_button);
        cSortButton = findViewById(R.id.sort_button);
        cChangeViewButton = findViewById(R.id.change_view_button);


        cSortButton.setOnClickListener(v -> openSortingDialog());
        cChangeViewButton.setOnClickListener(v -> mFragment.changeListLayout());
        // TODO add action to the buttons
    }

    private void openSortingDialog(){
        List<Sort> sortingOptions = getSortingOptions();
        int currentSortingOptions = getCurrentSortingOptions(sortingOptions, mCurrentFilter);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.sorting));
        builder.setSingleChoiceItems(new SortingAdapter(this, sortingOptions, android.R.layout.select_dialog_singlechoice), currentSortingOptions, (dialog, which) -> {
            Filter filter = mCurrentFilter;
            filter.setSorting(sortingOptions.get(which));
            mFragment.loadProducts(filter);
            dialog.dismiss();
        });
        builder.create().show();
    }
    public void showGalleryfromProductList(View view)
    {
        Intent intent = new Intent(this,ViewPagerActivity.class);
        startActivity(intent);
    }
    private int getCurrentSortingOptions(List<Sort> sortingOptions, Filter currentFilter){
        Sort currentSort = currentFilter.getSort();
        Sort sort;
        for(int i=0; i<sortingOptions.size(); i++){
            sort = sortingOptions.get(i);
            if(currentSort == null && sort == null){
                return i;
            } else if (currentSort == null || sort == null){
                continue;
            }
            if(currentSort.equals(sort)){
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    @NonNull
    private List<Sort> getSortingOptions() {
        List<Sort> sortingOptions = new ArrayList<>();
        sortingOptions.add(null);
        sortingOptions.add(new Sort("title", false));
        sortingOptions.add(new Sort("title", true));
        sortingOptions.add(new Sort("author", false));
        sortingOptions.add(new Sort("author", true));
        sortingOptions.add(new Sort("price", false));
        sortingOptions.add(new Sort("price", true));
        return sortingOptions;
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.products_container, fragment);
        fragmentTransaction.commit();
        mFragment = (ProductsListFragment) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        // TODO dodać wyszukiwanie. Może zrobić jeszcze jakąś liste z podpowiedziami
        MenuItem searchItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Filter filter = mCurrentFilter;
                filter.setText(query);
                mFragment.loadProducts(filter);
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
    public void onFragmentInteraction(Uri uri) {
        // TODO
    }
    private void startButtonsActivity(String cat) {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }

    // Respond to menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.miHome:
                Toast.makeText(
                        getApplicationContext(),
                        "You clicked the compose button.",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.miCompose:
                Toast.makeText(
                        getApplicationContext(),
                        "You clicked the compose button.",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.miProfile:
                Toast.makeText(
                        getApplicationContext(),
                        "You clicked the profile button.",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.action_settings:
                Toast.makeText(
                        getApplicationContext(),
                        "You clicked the settings button.",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.action_login:
                startActivity(new Intent(getApplicationContext(), ProductsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fadeIn(View view) {
        // Create an AlphaAnimation variable
        // 0.0f makes the view invisible
        // 1.0f makes the view fully visible
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        // Set out long you want the animation to be. * Measured in milliseconds *
        // 1000 milliseconds = 1 second
        anim.setDuration(1500);
        // Start the animation on our passed in view
        view.startAnimation(anim);
        /*  After the animation is complete we want to make sure we set the visibility of the view
            to VISIBLE. Otherwise it will go back to being INVISIBLE due to our previous lines
            that set the view to INVISIBLE */
        view.setVisibility(View.VISIBLE);
    }
}
