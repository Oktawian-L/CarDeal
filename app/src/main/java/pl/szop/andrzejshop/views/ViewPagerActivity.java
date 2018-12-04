package pl.szop.andrzejshop.views;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import me.relex.circleindicator.CircleIndicator;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ImageAdapterSlide;

public class ViewPagerActivity extends AppCompatActivity {

        private static ViewPager mPager;
        private static int currentPage = 0;
        private static int NUM_PAGES = 0;
        private static final Integer[] IMAGES= {R.drawable.sample_6,R.drawable.sample_3,R.drawable.sample_5,R.drawable.sample_0};
        private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            init();
        }
        private void init() {
            for(int i=0;i<IMAGES.length;i++)
                ImagesArray.add(IMAGES[i]);

            mPager = (ViewPager) findViewById(R.id.pager);


           // mPager.setAdapter(new ImageAdapterSlide(ViewPagerActivity.this,ImagesArray));


            CirclePageIndicator indicator = (CirclePageIndicator)
                    findViewById(R.id.indicator);
           // /nnul refe
           // indicator.setViewPager(mPager);

            final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
            indicator.setRadius(5 * density);

            NUM_PAGES =IMAGES.length;

            // Auto start of viewpager
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == NUM_PAGES) {
                        currentPage = 0;
                    }
                    mPager.setCurrentItem(currentPage++, true);
                }
            };
            Timer swipeTimer = new Timer();
            swipeTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, 3000, 3000);

            // Pager listener over indicator
            indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;

                }

                @Override
                public void onPageScrolled(int pos, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int pos) {

                }
            });

        }




}
