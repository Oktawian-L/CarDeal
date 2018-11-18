package pl.szop.cardealshop.data.database;

import pl.szop.cardealshop.models.Book;
import pl.szop.cardealshop.models.CarDetails;
import pl.szop.cardealshop.models.CarDetailsDao;
import pl.szop.cardealshop.models.DaoSession;
import pl.szop.cardealshop.models.CarDetailsDao;

public class ProductDAOSettings {

    public static String[] SEARCH_COLUMN  = {"title", "author"};
    public static Class PRODUCT_CLASS = Book.class;
    //definicja klasy bazowej
    public static Class PRODUCT_DETAILS_CLASS = CarDetails.class;

    public static CarDetailsDao getDetailsDao(DaoSession daoSession){
        return daoSession.getCarDetailsDao();
    }
}
