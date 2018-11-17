package pl.szop.cardeal.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.cardeal.models.Book;
import pl.szop.cardeal.models.BookDetails;
import pl.szop.cardeal.models.DaoSession;
import pl.szop.cardeal.models.Image;
import pl.szop.cardeal.utils.ImageUtils;

public class DatabaseInitializer {

    public static void init(DaoSession daoSession, Context context){

        Book product1 = new Book("Władca pierścieni", "Tolkien", "Fantastyka", 30.99);
        Book product2 = new Book("Hyperion", "Simmons", "Science fiction", 99.99);
        byte[] image = ImageUtils.getBytesFromResource(context, R.drawable.game);
        product1.setCover(image);
        product2.setCover(image);



        daoSession.getBookDao().insert(product1);
        daoSession.getBookDao().insert(product2);

        // saving details
        BookDetails details = new BookDetails();
        details.setDescription("“Władca Pierścieni” to niesamowita przygoda mówiąca o przyjaźni, poświęceniu i walce o dobro. Przenieś się w świat porywającego Śródziemia, miejsca, gdzie można spotkać nie tylko ludzi, lecz dumne krasnoludy, piękne elfy i, co najważniejsze, dzielne hobbity. Poznaj historię niepozornego niziołka, Froda Bagginsa, na którego barki niespodziewanie spada ogromna odpowiedzialność. Dzięki życzliwości swoich towarzyszy podejmuje się niebezpiecznego zadania i zabiera nas do magicznego świata.");
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
