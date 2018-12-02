package pl.szop.cardeal.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pl.szop.cardeal.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.cardeal.actions.RemoveFromCartAction;
import pl.szop.cardeal.adapters.ProductsAdapter;
import pl.szop.cardeal.models.Book;
import pl.szop.cardeal.models.CartItem;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cFavoritesList;
    private ProductsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();
    }

    private void initComponents(){
        cFavoritesList = findViewById(R.id.favorites_list);
        List<CartItem> cartitems = (List<CartItem>) MyApplication.instance().getDataProvider().getCartItems();
        List<Book> products = new ArrayList<>();
        // TODO wymyślić jakiś łatwiejszy sposób na to
        for (CartItem fav : cartitems){
            products.add(fav.getBook());
        }

        ProductsAdapter adapter = new ProductsAdapter(this, R.layout.cartitem_layout, new ArrayList<>(), productId -> {}); // TODO wstawić jakiegoś słuchacza
        adapter.addAction(R.id.remove_button, RemoveFromCartAction.NAME);
        cFavoritesList.setAdapter(adapter);
        Log.i("XXX", Integer.toString(cartitems.get(1).getAmount()));
        setListLayout(cFavoritesList);
        mAdapter = adapter;
        mAdapter.setItems(cartitems);
        // TODO wstawić jakąś akcję, do usuwania elementów z listy
    }

    private void setListLayout(RecyclerView productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        productList.setLayoutManager(layoutManager);
        productList.setItemAnimator(new DefaultItemAnimator());
    }

}
