package pl.szop.andrzejshop.views;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.actions.DecreaseAmountAction;
import pl.szop.andrzejshop.actions.RemoveFromCartAction;
import pl.szop.andrzejshop.actions.UpdateAmountAction;
import pl.szop.andrzejshop.adapters.ProductsAdapter;
import pl.szop.andrzejshop.models.Book;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Product;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cFavoritesList;
    private ProductsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initComponents();
    }
    // TODO: delete this
    @Subscribe
    public void testEventBus(CartActivity.TestEvent testEvent){
      //  Toast.makeText(this, "Testowanie EventBus", Toast.LENGTH_SHORT).show();
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
        adapter.addAction(R.id.plus, UpdateAmountAction.NAME);
        adapter.addAction(R.id.minus, DecreaseAmountAction.NAME);
        cFavoritesList.setAdapter(adapter);
        cFavoritesList.setAdapter(adapter);
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




    public static class TestEvent{

    }

}
