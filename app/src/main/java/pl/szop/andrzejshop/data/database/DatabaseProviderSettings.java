package pl.szop.andrzejshop.data.database;

import pl.szop.andrzejshop.models.Auto;
import pl.szop.andrzejshop.models.AutoDetails;
import pl.szop.andrzejshop.models.AutoDetailsDao;
import pl.szop.andrzejshop.models.DaoSession;

public class DatabaseProviderSettings {

    public static String[] SEARCH_COLUMN  = {"title", "author", "category"};
    public static Class PRODUCT_CLASS = Auto.class;
    public static Class PRODUCT_DETAILS_CLASS = AutoDetails.class;

    public static AutoDetailsDao getDetailsDao(DaoSession daoSession){
        return daoSession.getAutoDetailsDao();
    }
}
