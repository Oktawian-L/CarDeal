package pl.szop.andrzejshop.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.Category;
import pl.szop.andrzejshop.utils.ImageUtils;
import pl.szop.andrzejshop.utils.ResourceUtils;

public class CategoryAdapter extends BaseAdapter implements SpinnerAdapter {

    private List<Category> mItems;
    private Context mContext;
    private int mResource;

    public CategoryAdapter(List<Category> items, Context context, int resource){
        mItems = items;
        mContext = context;
        mResource = resource;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        if(mItems.get(position) == null){
            return -1L;
        }
        return mItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, mResource, null);
        TextView nameView = view.findViewById(R.id.name);
        ImageView imageView = view.findViewById(R.id.icon);
        Category category = mItems.get(position);
        String name;
        Bitmap bitmap;
        if(category != null){
            name = ResourceUtils.getStringByName(mItems.get(position).getName(), mContext);
            bitmap = BitmapFactory.decodeByteArray(category.getImage(), 0, category.getImage().length);
            imageView.setImageBitmap(bitmap);
        } else {
            name = mContext.getString(R.string.lack);
        }
        nameView.setText(name);


        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

}
