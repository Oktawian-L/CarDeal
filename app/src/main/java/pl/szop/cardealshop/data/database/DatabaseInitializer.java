package pl.szop.cardealshop.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.cardealshop.R;
import pl.szop.andrzejshop.models.Book;
import pl.szop.cardealshop.models.CarDetails;
//import pl.szop.cardealshop.models.BooksImages;
import pl.szop.andrzejshop.models.DaoSession;
import pl.szop.andrzejshop.models.Image;
import pl.szop.cardealshop.models.Car;
import pl.szop.cardealshop.models.CarDetails;
import pl.szop.cardealshop.utils.ImageUtils;

public class DatabaseInitializer {

    public static void init(DaoSession daoSession, Context context){

        Car car1 = new Car("Camaro","Chevrolet","Diesel", (double) 16000);
        Car car2 = new Car("Supra","Nissan","Diesel", (double) 10000);
        byte[] image_car2 = ImageUtils.getBytesFromResource(context, R.drawable.nissan);
        byte[] image_car1 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        car1.setPhoto(image_car1);
        car2.setPhoto(image_car2);


        Book product1 = new Book("Władca pierścieni", "Tolkien", "Fantastyka", 30.99);
        Book product2 = new Book("Hyperion", "Simmons", "Science fiction", 99.99);
        byte[] image = ImageUtils.getBytesFromResource(context, R.drawable.game);
        product1.setCover(image);
        product2.setCover(image);

        daoSession.getCarDao().insert(car1);
        daoSession.getCarDao().insert(car2);

        daoSession.getBookDao().insert(product1);
        daoSession.getBookDao().insert(product2);

        // saving details
        CarDetails details = new CarDetails();
        details.setDescription("“Władca Pierścieni” to niesamowita przygoda mówiąca o przyjaźni, poświęceniu i walce o dobro. Przenieś się w świat porywającego Śródziemia, miejsca, gdzie można spotkać nie tylko ludzi, lecz dumne krasnoludy, piękne elfy i, co najważniejsze, dzielne hobbity. Poznaj historię niepozornego niziołka, Froda Bagginsa, na którego barki niespodziewanie spada ogromna odpowiedzialność. Dzięki życzliwości swoich towarzyszy podejmuje się niebezpiecznego zadania i zabiera nas do magicznego świata.");


        details.setCar(car1);
        daoSession.getCarDetailsDao().insert(details);

        Image image1 = new Image();
        image1.setImage(image);
        image1.setProductId(details.getId());
        Image image2 = new Image();
        image2.setProductId(details.getId());
        image2.setImage(image);
        daoSession.getImageDao().insert(image1);
        daoSession.getImageDao().insert(image2);
        List<Image> images = Arrays.asList(image1, image2);

    }
}
