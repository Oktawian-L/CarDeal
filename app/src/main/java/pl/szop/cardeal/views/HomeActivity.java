package pl.szop.cardeal.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.szop.cardeal.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // TODO: remove this. Temporary. Start another activity
        //startProductsListActivity();
        //starting browse all actions
        startButtonsActivity();
    }

    private void startProductsListActivity() {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }
    //starting browse all actions
    private void startButtonsActivity() {
        Intent intent = new Intent(this, ButtonsActivity.class);
        startActivity(intent);
    }
}
