package pl.szop.andrzejshop.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.Book;
import pl.szop.andrzejshop.models.BookDetails;
import pl.szop.andrzejshop.models.BooksImages;
import pl.szop.andrzejshop.models.Category;
import pl.szop.andrzejshop.models.DaoSession;
import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.utils.ImageUtils;

public class DatabaseInitializer {

    public static void init(DaoSession daoSession, Context context){

        Category category1 = new Category( "sportowe");
        Category category2 = new Category( "sedan");
        Category category3 = new Category ("coupe");
        Category category4 = new Category( "kombi");
        /*daoSession.getCategoryDao().insert(category1);
        daoSession.getCategoryDao().insert(category2);
        daoSession.getCategoryDao().insert(category3);
        daoSession.getCategoryDao().insert(category4);*/

        Book product1 = new Book("audi", "tt", "sedan", 3234.45);
        Book product2 = new Book("audi", "tt", "sedan", 3234.45);
        Book product3 = new Book("audi", "tt", "sedan", 3234.45);
        Book product4 = new Book("audi", "tt", "sedan", 3234.45);
        Book product5 = new Book("audi", "tt", "sedan", 3234.45);
        Book product6 = new Book("audi", "tt", "sedan", 3234.45);
        Book product7 = new Book("audi", "tt", "sedan", 3234.45);
        Book product8 = new Book("audi", "tt", "sedan", 3234.45);
        Book product9 = new Book("audi", "tt", "sedan", 3234.45);
        Book product10 = new Book("audi", "tt", "sedan", 3234.45);
        Book product11 = new Book("audi", "tt", "sedan", 3234.45);
        Book product12 = new Book("audi", "tt", "sedan", 3234.45);
        Book product13 = new Book("audi", "tt", "sedan", 3234.45);
        Book product14 = new Book("audi", "tt", "sedan", 3234.45);

        byte[] img1 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img2 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img3 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img4 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img5 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img6 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img7 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img8 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img9 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img10 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img11 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img12 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img13 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
        byte[] img14 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);

        product1.setCover(img1);
        product2.setCover(img2);
        product3.setCover(img3);
        product4.setCover(img4);
        product5.setCover(img5);
        product6.setCover(img6);
        product7.setCover(img7);
        product8.setCover(img8);
        product9.setCover(img9);
        product10.setCover(img10);
        product11.setCover(img11);
        product12.setCover(img12);
        product13.setCover(img13);
        product14.setCover(img14);

        daoSession.getBookDao().insert(product1);
        daoSession.getBookDao().insert(product2);
        daoSession.getBookDao().insert(product3);
       daoSession.getBookDao().insert(product4);
        daoSession.getBookDao().insert(product5);
       daoSession.getBookDao().insert(product6);
       daoSession.getBookDao().insert(product7);
        daoSession.getBookDao().insert(product8);
        daoSession.getBookDao().insert(product9);
       daoSession.getBookDao().insert(product10);
        daoSession.getBookDao().insert(product11);
       daoSession.getBookDao().insert(product12);
        daoSession.getBookDao().insert(product13);
        daoSession.getBookDao().insert(product14);




        // saving details
        BookDetails details = new BookDetails();
        details.setDescription("Camaro car descrpt from database");

        details.setBook(product1);
        daoSession.getBookDetailsDao().insert(details);

        Image image1 = new Image();
        image1.setImage(img1);
        image1.setProductId(details.getId());
        Image image2 = new Image();

    }
}
