package pl.szop.cardeal.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import pl.szop.cardeal.R;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Button btnCriminal = findViewById(R.id.button_kombi);

        btnCriminal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProductsListActivity("category");
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
        intent.putExtra("categoryType", cat);
        startActivity(intent);
    }
}
