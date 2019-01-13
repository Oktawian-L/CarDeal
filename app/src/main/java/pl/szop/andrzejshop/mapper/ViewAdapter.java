package pl.szop.andrzejshop.mapper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import pl.szop.andrzejshop.models.GenericModel;

public class ViewAdapter {

    private Map<Integer, Action> mActions = new HashMap<>();
    private Map<Integer, Condition> mConditions = new HashMap<>();

    public void addAction(int elementResource, Action action) {
        mActions.put(elementResource, action);
    }

    public void addCondition(int elementResource, Condition condition){
        mConditions.put(elementResource, condition);
    }

    public void bindView(View view, GenericModel product) {
        bindView(view, product, true);
    }

    public void bindView(View view, GenericModel product, boolean recursive){

        if(recursive && hasChildrenViews(view)){
            ViewGroup viewGroup = (ViewGroup) view;
//            setActions(view, product);
            View childView;
            for(int i =0; i <viewGroup.getChildCount(); i++){
                childView = viewGroup.getChildAt(i);
                bindView(childView, product);
            }
        } else {
            setViewValue(view, product);
            checkConditions(view, product);
            setAction(view, product);
        }
    }

    private void setViewValue(View view, GenericModel product) {
        String viewName = getResourceName(view);
        try{
            Object value = isCustomObject(viewName) ? getValueFromCustomObject(viewName, product) :
                    product.getValue(viewName);
            setValue(view, value);
        } catch (Exception ignored){
            // method getValue can generate exception when not found correct method in object
        }
    }

    private boolean isCustomObject(String view) {
        return view.contains(".");
    }

    private Object getValueFromCustomObject(String viewId, GenericModel product) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] fields = viewId.split("\\.");
        GenericModel lastProduct = product;
        for(String field : fields){
            Object object = lastProduct.getValue(field);
            if(object instanceof GenericModel){
                lastProduct = (GenericModel) object;
            } else {
                return object;
            }
        }
        return null;
    }

    public void checkConditions(View view, GenericModel product){
        if(mConditions.containsKey(view.getId())){
            mConditions.get(view.getId()).execute(product, view);
        }
    }

    public void setAction(View view, GenericModel product){
        if(isActionComponent(view) && mActions.containsKey(view.getId())){
            view.setOnClickListener(e->{
                mActions.get(view.getId()).execute(product, view);
            });
        }
    }

    private boolean isActionComponent(View childView) {
        return childView instanceof Button || childView instanceof ImageButton;
    }

    private boolean hasChildrenViews(View view) {
        return view instanceof ViewGroup;
    }

    private void setValue(View view, Object value){
        if(view == null){
            return;
        }

        if (view instanceof TextView){
            setText(value, (TextView) view);
        } else if (view instanceof ImageView){
            setImage(value, (ImageView) view);
        } else if (view instanceof ViewPager){
//            ImageAdapter adapter = new ImageAdapter(view.getContext(), (List<Image>) value);
//            ((ViewPager)view).setAdapter(adapter);
        } else {
            System.out.println();
        }
        // TODO serve another types
    }

    private void setImage(Object value, ImageView view) {
        if(value instanceof byte[]){
            byte[] data = (byte[]) value;
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            view.setImageBitmap(bitmap);
        }else if(value instanceof Integer){
            view.setImageResource((Integer) value);
        } else if (value instanceof Bitmap){
            view.setImageBitmap((Bitmap) value);
        }
    }

    private void setText(Object value, TextView view) {
        if(value instanceof String){
            view.setText((String)value);
        } else if(value instanceof Number) {
            view.setText(String.valueOf(value));
        }
    }

    private String getResourceName(View view){
        return (String) view.getTag();
    }
}
