package pl.szop.andrzejshop.views;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ImageAdapterSlide;

public class ViewPagerActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.view_pager);

            ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPageAndroid);
            ImageAdapterSlide adapterView = new ImageAdapterSlide(this);
            mViewPager.setAdapter(adapterView);
        }

}
