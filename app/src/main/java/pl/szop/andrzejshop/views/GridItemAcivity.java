package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import pl.szop.andrzejshop.R;

public class GridItemAcivity extends AppCompatActivity {

    TextView name;
    ImageView image;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_grid_item);

        name = findViewById(R.id.griddata);
        image = findViewById(R.id.imageView);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));

        image.setImageResource(intent.getIntExtra("image",0));

    }
}
