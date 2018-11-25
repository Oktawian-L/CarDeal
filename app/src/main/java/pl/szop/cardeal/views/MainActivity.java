package pl.szop.cardeal.views;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import pl.szop.cardeal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @SuppressLint("ResourceType") Toolbar myToolbar = (Toolbar) findViewById(R.menu.toolbar_menu);
        setSupportActionBar(myToolbar);
    }

}
