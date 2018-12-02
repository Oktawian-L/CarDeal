package pl.szop.andrzejshop.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.ImageAdapterSlide;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        //definicja zamiast nadpisywania
       /* private Context mContext;
        private int[] mImageIds = new int[]{R.drawable.audi2, R.drawable.audi2, R.drawable.audi2, R.drawable.audi2};
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView,0);*/

        //incjalizacja gallery
        ViewPager viewPager = findViewById(R.id.view_pager2);
        ImageAdapterSlide adapterslide = new ImageAdapterSlide(this);
        viewPager.setAdapter(adapterslide);

        Toast.makeText(
                getApplicationContext(),
                "Witaj w galerii",
                Toast.LENGTH_SHORT)
                .show();

    }
}
