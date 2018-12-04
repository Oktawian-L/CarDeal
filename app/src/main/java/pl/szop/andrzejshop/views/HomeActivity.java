package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;

import pl.szop.andrzejshop.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
     //   setSupportActionBar(myToolbar);
        // TODO: remove this. Temporary. Start another activity
        startProductsListActivity();
    }

    private void startProductsListActivity() {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }
    private void startButtonsActivity(String cat) {
        Intent intent = new Intent(this, ProductsActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return true;

    }
    // Respond to menu item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {

            case R.id.miCompose:
                Toast.makeText(
                        getApplicationContext(),
                        "Compose func",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            case R.id.miProfile:
                Toast.makeText(
                        getApplicationContext(),
                        "Profile func",
                        Toast.LENGTH_SHORT)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void fadeIn(View view) {
        //animacje fadeIN
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1500);
        // Start the animation on our passed in view
        view.startAnimation(anim);
       view.setVisibility(View.VISIBLE);
    }
}
