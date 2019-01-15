package pl.szop.andrzejshop.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ImageAdapter;
import pl.szop.andrzejshop.adapters.ImageAdapterGrid;

public class GridActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapterGrid(this));
        
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbargridyy);
        setSupportActionBar(myToolbar);

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(GridActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
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


