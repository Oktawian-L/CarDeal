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

import pl.szop.cardeal.R;

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
    @Override
   public boolean onCreateOptionsMenu(Menu menu)
   {
       getMenuInflater().inflate(R.menu.toolbar_menu,menu);
       return true;

   }
   /* @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        private void fadeIn(View view);
    }*/
}
