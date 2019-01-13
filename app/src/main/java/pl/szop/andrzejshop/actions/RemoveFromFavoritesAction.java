package pl.szop.andrzejshop.actions;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.InvocationTargetException;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.events.RefreshFavoritesEvent;
import pl.szop.andrzejshop.models.GenericModel;

public class RemoveFromFavoritesAction {

    public static final String NAME = "REMOVE_FROM_FAVORITES";



    public void execute(Object object, Context context) {
        try {
            Long id = (Long) ((GenericModel)object).getValue("id");
            MyApplication.instance().getDataProvider().setFavorite(id, false);
            EventBus.getDefault().post(new RefreshFavoritesEvent((GenericModel) object));
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
