package pl.szop.andrzejshop.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.Auto;
import pl.szop.andrzejshop.models.AutoDetails;
import pl.szop.andrzejshop.models.Category;
import pl.szop.andrzejshop.models.DaoSession;
import pl.szop.andrzejshop.models.Favorites;
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

        Auto product1 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product2 = new Auto("chevrolet", "camaro", "sportowe", 5432.99);
        Auto product3 = new Auto("bmw", "tt", "sedan", 3234.45);
        Auto product4 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product5 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product6 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product7 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product8 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product9 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product10 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product11 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product12 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product13 = new Auto("audi", "tt", "sedan", 3234.45);
        Auto product14 = new Auto("audi", "tt", "sedan", 3234.45);

        byte[] img1 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
       /* byte[] img2 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);
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
        byte[] img14 = ImageUtils.getBytesFromResource(context, R.drawable.audi2);*/

        product1.setCover(img1);
        product2.setCover(img1);
        product3.setCover(img1);
        product4.setCover(img1);
        product5.setCover(img1);
        product6.setCover(img1);
        product7.setCover(img1);
        product8.setCover(img1);
        product9.setCover(img1);
        product10.setCover(img1);
        product11.setCover(img1);
        product12.setCover(img1);
        product13.setCover(img1);
        product14.setCover(img1);

        daoSession.getAutoDao().insert(product1);
        daoSession.getAutoDao().insert(product2);
        daoSession.getAutoDao().insert(product3);
        daoSession.getAutoDao().insert(product4);
        daoSession.getAutoDao().insert(product5);
        daoSession.getAutoDao().insert(product6);
        daoSession.getAutoDao().insert(product7);
        daoSession.getAutoDao().insert(product8);
        daoSession.getAutoDao().insert(product9);
        daoSession.getAutoDao().insert(product10);
        daoSession.getAutoDao().insert(product11);
        daoSession.getAutoDao().insert(product12);
        daoSession.getAutoDao().insert(product13);
        daoSession.getAutoDao().insert(product14);




        // saving details
        AutoDetails details = new AutoDetails();
        details.setDescription("Camaro car descrpt from database");

        details.setAuto(product1);
        daoSession.getAutoDetailsDao().insert(details);
        Favorites favorites = new Favorites(1L);
        daoSession.getFavoritesDao().insert(favorites);
        details.setAuto(product1);
       /* daoSession.getBookDetailsDao().insert(details);
        details.setBook(product2);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product3);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product4);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product5);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product6);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product7);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product8);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product9);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product10);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product11);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product12);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product13);
        daoSession.getBookDetailsDao().insert(details);
        details.setBook(product14);
        daoSession.getBookDetailsDao().insert(details);*/

        Image image1 = new Image();
        image1.setImage(img1);
        image1.setProductId(details.getId());
        Image image2 = new Image();

    }
}
