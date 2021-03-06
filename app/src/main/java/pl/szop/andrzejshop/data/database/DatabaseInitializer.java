package pl.szop.andrzejshop.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.Author;
import pl.szop.andrzejshop.models.Book;
import pl.szop.andrzejshop.models.BookDetails;
import pl.szop.andrzejshop.models.Category;
import pl.szop.andrzejshop.models.DaoSession;
import pl.szop.andrzejshop.models.Favorites;
import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.utils.DateUtils;
import pl.szop.andrzejshop.utils.ImageUtils;

public class DatabaseInitializer {

    public static void init(DaoSession daoSession, Context context){

        Author author1 = new Author(1L,"Marka", "Audi");
        Author author2 = new Author(2L, "Marka", "Nissan");
        daoSession.getAuthorDao().insert(author1);
        daoSession.getAuthorDao().insert(author2);

        Category category1 = new Category(1L, "sedan");
        Category category2 = new Category(2L, "sportowe");
        Category category3 = new Category(3L, "suv");
        daoSession.getCategoryDao().insert(category1);
        daoSession.getCategoryDao().insert(category2);
        daoSession.getCategoryDao().insert(category3);

        Book product1 = new Book("AudiTT", author1, category2, 3000.99);
        product1.setReleaseDate(DateUtils.createTimestamp(1,1,2018));
        Book product2 = new Book("Nissan", author1, category3, 2234.00);
        product2.setReleaseDate(DateUtils.createTimestamp(2,1,2018));
        Book product3 = new Book("Nissan", author2, category1, 9931.99);
        product3.setReleaseDate(DateUtils.createTimestamp(3,1,2018));
        byte[] image = ImageUtils.getBytesFromResource(context, R.drawable.game);
        byte[] telnoImage = ImageUtils.getBytesFromResource(context, R.drawable.tellnoone);
        product1.setCover(image);
        product2.setCover(image);
        product3.setCover(telnoImage);

        daoSession.getBookDao().insert(product1);
        daoSession.getBookDao().insert(product2);
        daoSession.getBookDao().insert(product3);

        Promotions promotions1 = new Promotions(product1.getId(), 20D);
        Promotions promotions2 = new Promotions(product3.getId(), 10D);

        daoSession.getPromotionsDao().insert(promotions1);
        daoSession.getPromotionsDao().insert(promotions2);

        // saving details
        BookDetails details = new BookDetails();
        details.setDescription("Super auto jedzenia po miescie. Zona tylko do kosciala nim jezdzila");

        Favorites favorites = new Favorites(1L);
        daoSession.getFavoritesDao().insert(favorites);
//        Image image1 = new Image();
//        image1.setMImage(image);
//        Image image2 = new Image();
//        image2.setMImage(image);
//        Image image3 = new Image();
//        image3.setMImage(image);

//        daoSession.getImageDao().insert(image1);
//        daoSession.getImageDao().insert(image2);
//        daoSession.getImageDao().insert(image3);
//
//        // to nie działa

//        daoSession.getImageDao().insert(image1);
//        daoSession.getImageDao().insert(image2);
//        details.getImages().add(image1);
//        details.getImages().add(image2);
//        List<Image> images = Arrays.asList(image1, image2, image3);
//        details.setImages(images);

        details.setBook(product1);
        daoSession.getBookDetailsDao().insert(details);

        Image image1 = new Image();
        image1.setImage(image);
        image1.setProductId(details.getId());
        Image image2 = new Image();
        image2.setProductId(details.getId());
        image2.setImage(image);
        daoSession.getImageDao().insert(image1);
        daoSession.getImageDao().insert(image2);
        List<Image> images = Arrays.asList(image1, image2);
//        BooksImages booksImages = new BooksImages();
//        booksImages.setBook(details.getId());
//        booksImages.setImage(image1.getId());
//        daoSession.getBooksImagesDao().insert(booksImages);
    }
}
