package pl.szop.andrzejshop.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.GenericModel;

public class GenericModelAdapter extends BaseAdapter implements SpinnerAdapter {

    private List<? extends GenericModel> mItems;
    private Context mContext;
    private int mResource;

    public GenericModelAdapter(List<? extends GenericModel> items, Context context, int resource){
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
        if(mItems.get(position)==null){
            return -1L;
        }
        return mItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent){
        View view = View.inflate(mContext, mResource, null);
        TextView nameView = view.findViewById(R.id.name);
        GenericModel model = mItems.get(position);
        String name;
        if(model != null){
            name = model.toString();
        } else {
            name = mContext.getString(R.string.lack);
        }
        nameView.setText(name);

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        return initView(position, convertView, parent);
    }
}
