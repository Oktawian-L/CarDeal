package pl.szop.andrzejshop.utils;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Promotions;

public class ViewsCommon {

    public static void setPriceText(Object object, View view, Map<Long, Promotions> promotions) {
        Long id = ((GenericModel)object).getId();
        if (promotions.containsKey(id)){
            TextView priceTextView = (TextView)view;
            priceTextView.setTextColor(Color.BLUE);
            priceTextView.setText(NumberUtils.priceFormat(promotions.get(id).getValue()) + " zł");
        }
    }

    public static Map<Long, Promotions> getPromotions(List<? extends GenericModel> items){
        Map<Long, Promotions> promotions = new HashMap<>();
        for(GenericModel item : items){
            Promotions promotion = MyApplication.instance().getDataProvider().getPromotion(item.getId());
            if(promotion != null){
                promotions.put(promotion.getId(), promotion);
            }
        }
        return promotions;
    }

    public static void setAddToCartVisibility(Object object, View view, boolean showWhenInCart, Set<Long> cart){
        int visibility;
        Long id = ((GenericModel)object).getId();
        if(cart.contains(id)){
            visibility = showWhenInCart ? View.VISIBLE : View.INVISIBLE;
        } else {
            visibility = showWhenInCart ? View.INVISIBLE : View.VISIBLE;
        }
        view.setVisibility(visibility);
    }

    public static Set<Long> getCart(List<? extends GenericModel> items){
        Set<Long> cart = new HashSet<>();
        for(GenericModel model : items){
            if(MyApplication.instance().getDataProvider().isInCart(model.getId())){
                cart.add(model.getId());
            }
        }
        return cart;
    }

    public static Set<Long> getWishList(List<? extends GenericModel> items){
        Set<Long> wishList = new HashSet<>();
        for (GenericModel model : items){
            if(MyApplication.instance().getDataProvider().isFavorite(model.getId())){
                wishList.add(model.getId());
            }
        }
        return wishList;
    }
}
