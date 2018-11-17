package pl.szop.cardealshop.data.database;

import pl.szop.andrzejshop.models.Book;
import pl.szop.cardealshop.models.BookDetails;
import pl.szop.cardealshop.models.BookDetailsDao;
import pl.szop.andrzejshop.models.DaoSession;

public class ProductDAOSettings {

    public static String[] SEARCH_COLUMN  = {"title", "author"};
    public static Class PRODUCT_CLASS = Book.class;
    public static Class PRODUCT_DETAILS_CLASS = BookDetails.class;

    public static BookDetailsDao getDetailsDao(DaoSession daoSession){
        return daoSession.getBookDetailsDao();
    }
}
