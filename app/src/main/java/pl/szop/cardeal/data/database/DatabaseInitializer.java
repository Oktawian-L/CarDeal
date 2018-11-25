package pl.szop.cardeal.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.cardeal.R;
import pl.szop.cardeal.models.Book;
import pl.szop.cardeal.models.BookDetails;
import pl.szop.cardeal.models.Car;
import pl.szop.cardeal.models.CarDetails;
import pl.szop.cardeal.models.Category;
import pl.szop.cardeal.models.DaoSession;
import pl.szop.cardeal.models.Image;
import pl.szop.cardeal.utils.ImageUtils;

public class DatabaseInitializer {

    public static void init(DaoSession daoSession, Context context){

        Category category1 = new Category(1L, "sportowe");
        Category category2 = new Category(2L, "sedan");
        Category category3 = new Category(3L, "coupe");
        daoSession.getCategoryDao().insert(category1);
        daoSession.getCategoryDao().insert(category2);
        daoSession.getCategoryDao().insert(category3);

        Car car1 = new Car("Camaro","Chevrolet","Diesel", (double) 16000);
        Car car2 = new Car("Supra","Nissan","Diesel", (double) 10000);
        byte[] image_car2 = ImageUtils.getBytesFromResource(context, R.drawable.nissan);
        byte[] image_car1 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        car1.setPhoto(image_car1);
        car2.setPhoto(image_car2);


        /*Book product1 = new Book("Władca pierścieni", "Tolkien", "Fantastyka", 30.99);
        Book product2 = new Book("Hyperion", "Simmons", "Science fiction", 99.99);

        product1.setCover(image);
        product2.setCover(image);*/

        byte[] image = ImageUtils.getBytesFromResource(context, R.drawable.game);
        byte[] image_def = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        daoSession.getCarDao().insert(car1);
        daoSession.getCarDao().insert(car2);

       /* daoSession.getBookDao().insert(product1);
        daoSession.getBookDao().insert(product2);*/

        // saving details
        CarDetails details = new CarDetails();
        details.setDescription("Camaro car descrpit");


        details.setCar(car1);
        daoSession.getCarDetailsDao().insert(details);

        Image image1 = new Image();
        image1.setImage(image);
        image1.setProductId(details.getId());
        Image image2 = new Image();
        image2.setProductId(details.getId());
        image2.setImage(image_def);
        daoSession.getImageDao().insert(image1);
        daoSession.getImageDao().insert(image2);
        List<Image> images = Arrays.asList(image1, image2);
    }
}
