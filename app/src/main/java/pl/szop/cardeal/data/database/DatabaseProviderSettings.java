package pl.szop.cardeal.data.database;

import pl.szop.cardeal.models.Book;
import pl.szop.cardeal.models.BookDetails;
import pl.szop.cardeal.models.BookDetailsDao;
import pl.szop.cardeal.models.DaoSession;

public class DatabaseProviderSettings {

    public static String[] SEARCH_COLUMN  = {"title", "author", "category"};
    public static Class PRODUCT_CLASS = Book.class;
    public static Class PRODUCT_DETAILS_CLASS = BookDetails.class;

    public static BookDetailsDao getDetailsDao(DaoSession daoSession){
        return daoSession.getBookDetailsDao();
    }
}
