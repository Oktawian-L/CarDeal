package pl.szop.andrzejshop.actions;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.events.RefreshFavoritesEvent;
import pl.szop.andrzejshop.models.GenericModel;

public class AddToFavoritesAction {

    public static final String NAME = "ADD_TO_FAVORITES";


    public void execute(Object object, Context context) {
        Long id = ((GenericModel)object).getId();
        // TODO spróbować to zrobić bez pobierania
        boolean favorites = MyApplication.instance().getDataProvider().isFavorite(id);
        MyApplication.instance().getDataProvider().setFavorite(id, !favorites);
        EventBus.getDefault().post(new RefreshFavoritesEvent((GenericModel) object));
    }
}
