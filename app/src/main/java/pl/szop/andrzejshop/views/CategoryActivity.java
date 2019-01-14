package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import pl.szop.andrzejshop.R;

public class CategoryActivity extends AppCompatActivity {
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbarcateg);
        setSupportActionBar(myToolbar);
        Button btnCriminal = findViewById(R.id.button_kombi);

        btnCriminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("sportowe");
            }
        });

        Button btnFantasy = findViewById(R.id.button_sportowe);

        btnFantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("sportowe");
            }
        });

        Button btnThriller = findViewById(R.id.button_coupe);

        btnThriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("coupe");
            }
        });

        Button btnSciFi = findViewById(R.id.button_sedan);

        btnSciFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("sedan");
            }
        });

    }
    private void startProductsListActivity(String cat) {
        Intent intent = new Intent(this, ProductsActivity.class);
        //intent.putExtra("categoryType", cat);
        startActivity(intent);
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
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button btnCriminal = findViewById(R.id.button_criminal);

        btnCriminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("category");
            }
        });

        Button btnFantasy = findViewById(R.id.button_fantasy);

        btnFantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("fantastyka");
            }
        });

        Button btnThriller = findViewById(R.id.button_thriller);

        btnThriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("thriller");
            }
        });

        Button btnSciFi = findViewById(R.id.button_scifi);

        btnSciFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("Science fiction");
            }
        });

    }
    private void startProductsListActivity(String cat) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra("categoryType", cat);
        startActivity(intent);
    }*/
}
