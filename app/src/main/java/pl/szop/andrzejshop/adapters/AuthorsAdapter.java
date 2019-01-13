package pl.szop.andrzejshop.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.Author;

public class AuthorsAdapter extends ArrayAdapter {

    private List<Author> mItems;
    private Context mContext;
    private int mResource;

    public AuthorsAdapter(List<Author> items , Context context, int resource) {
        super(context, resource, items);
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
        return mItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, mResource, null);
        TextView nameView = view.findViewById(R.id.name);
        Author author = mItems.get(position);
        nameView.setText(author.getName());
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return initView(position, convertView, parent);
    }
}
