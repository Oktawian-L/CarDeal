package pl.szop.cardeal.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.szop.cardeal.R;

public class ButtonsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_buttons);
    }

    private void startButtonsActivity(String cat) {
        Intent intent = new Intent(this, ButtonsActivity.class);
        startActivity(intent);
    }
}
