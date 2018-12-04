package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.szop.andrzejshop.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button btnCriminal = findViewById(R.id.button_metal);

        btnCriminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("Game soundtrack");
            }
        });

        Button btnFantasy = findViewById(R.id.button_indie);

        btnFantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("Indie rock");
            }
        });

        Button btnThriller = findViewById(R.id.button_hard);

        btnThriller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("Hard rock");
            }
        });

        Button btnSciFi = findViewById(R.id.button_metal);

        btnSciFi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("Metal");
            }
        });

    }
    private void startProductsListActivity(String cat) {
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra("categoryType", cat);
        startActivity(intent);
    }
}
