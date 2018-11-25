package pl.szop.cardeal.views;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.ServiceConnection;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.EditText;
import android.widget.Toast;

import pl.szop.cardeal.R;
import pl.szop.cardeal.models.Product;

public class ButtonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_buttons);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    private void startButtonsActivity(String cat) {
        Intent intent = new Intent(this, ButtonsActivity.class);
        startActivity(intent);
    }

    public void showCategory(View view)
    {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
    public void showProductList(View view)
    {
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
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1500);
        view.startAnimation(anim);
        view.setVisibility(View.VISIBLE);
    }
}
