package pl.szop.andrzejshop.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import pl.szop.andrzejshop.R;

public class ImageAdapterSlide extends PagerAdapter {

    private Context mContext;
    private int []mImageIds = new int[] {R.drawable.audi2,R.drawable.audi2,R.drawable.audi2,R.drawable.audi2};
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return false;
    }
}
