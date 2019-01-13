package pl.szop.andrzejshop.mapper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.szop.andrzejshop.models.GenericModel;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private Context mContext;
    private List<? extends GenericModel> mItems;
    private int mResource;
    private AdapterListener mListener;

    private Map<Integer, Action> mActions;

    private Map<Integer, Condition> mConditions;
    private ViewAdapter mViewAdapter = new ViewAdapter();

    public void addCondition(int elementResource, Condition condition){
        mViewAdapter.addCondition(elementResource, condition);
    }

    public void addAction(int elementResource, Action action){
        mViewAdapter.addAction(elementResource, action);
    }

    public interface AdapterListener{
        void onClick(Long productId);
    }

    public GenericModel getItem(int position){
        return mItems.get(position);
    }

    public ProductsAdapter(@NonNull Context context, int resource, List<? extends GenericModel> items, AdapterListener adapterListener) {
        mContext = context;
        mResource = resource;
        mItems = items;
        mListener = adapterListener;
        mActions = new HashMap<>();
        mConditions = new HashMap<>();
    }

    public void setItems(List<? extends GenericModel> items){
        mItems = items;
        notifyDataSetChanged();
    }

    public void removeItem(GenericModel item){
        mItems.remove(item);
        notifyDataSetChanged();
    }

    public void removeItem(int index){
        mItems.remove(index);
        notifyDataSetChanged();
    }

    public void setResource(int resource){
        mResource = resource;
    }

    private void setValues(ViewHolder viewHolder, GenericModel product) {
        for(String viewName : viewHolder.getFields()) {
            View view = viewHolder.mViews.get(viewName.toLowerCase());
            mViewAdapter.bindView(view, product, true);
            mViewAdapter.checkConditions(view, product);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(mContext).inflate(mResource, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GenericModel product = mItems.get(i);
        viewHolder.getView().setOnClickListener((event)->{
            if(mListener != null){
                try {
                    Long id = (Long) mItems.get(i).getValue("id");
                    mListener.onClick(id);
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        setValues(viewHolder, product);
    }


    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private Map<String, View> mViews = new HashMap<>();
        private View mView;

        ViewHolder(View view){
            super(view);
            mView = view;
            ViewGroup viewGroup = (ViewGroup) view;
            for(int i=0; i<viewGroup.getChildCount(); i++){
                View childView = viewGroup.getChildAt(i);
                // set actions
                setActions(childView);
                String resourceName = getResourceName(childView);
                mViews.put(resourceName.toLowerCase(), childView);
            }
        }

        // TODO spróbować wykorzystac funkcję z ViewAdapter
        private void setActions(View childView) {
            if(isActionComponent(childView) && mActions.containsKey(childView.getId())){
                childView.setOnClickListener(e->{
                    GenericModel product = mItems.get(getAdapterPosition());
                    mActions.get(childView.getId()).execute(product, childView);
                });
            }
        }

        public View getView(){
            return mView;
        }

        private boolean isActionComponent(View childView) {
            return childView instanceof Button || childView instanceof ImageButton;
        }

        public Set<String> getFields(){
            return mViews.keySet();
        }

        private String getResourceName(View view){
            String resourceName = mContext.getResources().getResourceName(view.getId());
            int splashIndex = resourceName.indexOf('/');
            return resourceName.substring(splashIndex+1);
        }
    }
}
