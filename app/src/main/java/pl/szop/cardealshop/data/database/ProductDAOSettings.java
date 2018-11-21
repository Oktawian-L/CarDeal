package pl.szop.cardealshop.data.database;

import pl.szop.cardealshop.models.Car;
import pl.szop.cardealshop.models.CarDetails;
import pl.szop.cardealshop.models.CarDetailsDao;
import pl.szop.cardealshop.models.DaoSession;


public class ProductDAOSettings {

    public static String[] SEARCH_COLUMN  = { "model","marka",};
    public static Class PRODUCT_CLASS = Car.class;
    //definicja klasy bazowej
    public static Class PRODUCT_DETAILS_CLASS = CarDetails.class;

    public static CarDetailsDao getDetailsDao(DaoSession daoSession){
        return daoSession.getCarDetailsDao();
    }
}
