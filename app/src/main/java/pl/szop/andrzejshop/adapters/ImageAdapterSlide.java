package pl.szop.andrzejshop.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import pl.szop.andrzejshop.R;

public class ImageAdapterSlide extends PagerAdapter {

    private Context mContext;
    private int[] mImageIds = new int[]{R.drawable.audi2, R.drawable.audi2, R.drawable.audi2, R.drawable.audi2};

    public ImageAdapterSlide(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    //press ctril o for instantia

    @NonNull
    @Override
    //public Object instantiateItem(@NonNull ViewGroup container, int position) {
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //return a image
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mImageIds[position]);
        container.addView(imageView,0);
        return imageView;

    }
    //press ctril o for destryitem, for overdrive

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}


