package pl.szop.andrzejshop.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.Book;
import pl.szop.andrzejshop.models.BookDetails;
import pl.szop.andrzejshop.models.BooksImages;
import pl.szop.andrzejshop.models.DaoSession;
import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.utils.ImageUtils;

public class DatabaseInitializer {

    public static void init(DaoSession daoSession, Context context){

        Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        Book product2 = new Book("Camaro2", "Chevrolet", "Sport", 32340.99);
        Book product3 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        /*Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);
        Book product1 = new Book("Camaro", "Chevrolet", "Sport", 32340.99);*/


        byte[] img1 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img2 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img3 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img4 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img5 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img6 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img7 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img8 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img9 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img10 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img11 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img12 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img13 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);
        byte[] img14 = ImageUtils.getBytesFromResource(context, R.drawable.camaro);

        product1.setCover(img1);
        product2.setCover(img2);
        product3.setCover(img3);
        /*product4.setCover(img4);
        product5.setCover(img5);
        product6.setCover(img6);
        product7.setCover(img7);
        product8.setCover(img8);
        product9.setCover(img9);
        product10.setCover(img10);
        product11.setCover(img11);
        product12.setCover(img12);
        product13.setCover(img13);
        product14.setCover(img14);*/

        daoSession.getBookDao().insert(product1);
        daoSession.getBookDao().insert(product2);
        daoSession.getBookDao().insert(product3);
      /* daoSession.getBookDao().insert(product4);
        daoSession.getBookDao().insert(product5);
       daoSession.getBookDao().insert(product6);
       daoSession.getBookDao().insert(product7);
        daoSession.getBookDao().insert(product8);
        daoSession.getBookDao().insert(product9);
       daoSession.getBookDao().insert(product10);
        daoSession.getBookDao().insert(product11);
       daoSession.getBookDao().insert(product12);
        daoSession.getBookDao().insert(product13);
        daoSession.getBookDao().insert(product14);*/




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
        image1.setImage(img1);
        image1.setProductId(details.getId());
        Image image2 = new Image();
//        image2.setProductId(details.getId());
//        image2.setImage(img2);
//        Image image3 = new Image();
//        image3.setProductId(details.getId());
//        image3.setImage(img3);
//        Image image4 = new Image();
//        image4.setProductId(details.getId());
//        image4.setImage(img4);
//        Image image5 = new Image();
//        image5.setProductId(details.getId());
//        image5.setImage(img5);
//        Image image6 = new Image();
//        image6.setProductId(details.getId());
//        image6.setImage(img6);
//        Image image7 = new Image();
//        image7.setProductId(details.getId());
//        image7.setImage(img7);
//        Image image8 = new Image();
//        image8.setProductId(details.getId());
//        image8.setImage(img8);
//        Image image9 = new Image();
//        image9.setProductId(details.getId());
//        image9.setImage(img9);
//        Image image10 = new Image();
//        image10.setProductId(details.getId());
//        image10.setImage(img10);
//        Image image11 = new Image();
//        image11.setProductId(details.getId());
//        image11.setImage(img11);
//        Image image12 = new Image();
//        image12.setProductId(details.getId());
//        image12.setImage(img12);
//        Image image13 = new Image();
//        image13.setProductId(details.getId());
//        image13.setImage(img13);
//        Image image14 = new Image();
//        image14.setProductId(details.getId());
//        image14.setImage(img14);
//        daoSession.getImageDao().insert(image1);
//        daoSession.getImageDao().insert(image2);
//        daoSession.getImageDao().insert(image3);
//        daoSession.getImageDao().insert(image4);
//        daoSession.getImageDao().insert(image5);
//        daoSession.getImageDao().insert(image6);
//        daoSession.getImageDao().insert(image7);
//        daoSession.getImageDao().insert(image8);
//        daoSession.getImageDao().insert(image9);
//        daoSession.getImageDao().insert(image10);
//        daoSession.getImageDao().insert(image11);
//        daoSession.getImageDao().insert(image12);
//        daoSession.getImageDao().insert(image13);
//        daoSession.getImageDao().insert(image14);


 //      List<Image> images = Arrays.asList(image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14);
//        BooksImages booksImages = new BooksImages();
//        booksImages.setBook(details.getId());
//        booksImages.setImage(image1.getId());
//        daoSession.getBooksImagesDao().insert(booksImages);
    }
}
