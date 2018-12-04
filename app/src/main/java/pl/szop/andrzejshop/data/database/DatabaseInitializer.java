package pl.szop.andrzejshop.data.database;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.models.Book;
import pl.szop.andrzejshop.models.BookDetails;
import pl.szop.andrzejshop.models.BooksImages;
import pl.szop.andrzejshop.models.DaoSession;
import pl.szop.andrzejshop.models.Favorites;
import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.utils.ImageUtils;

public class DatabaseInitializer {

    public static void init(DaoSession daoSession, Context context){

        Book product1 = new Book("Love Metal", "HIM", "Metal", 30.99);
        Book product2 = new Book("I see you", "The XX", "Indie rock", 99.99);
        Book product3 = new Book("Coexist", "The XX", "Indie Rock", 29.99);
        Book product4 = new Book("Collide", "Aeraile Bightman", "Game Soundtrack", 19.99);
        Book product5 = new Book("Whatever people say I am, that's what I am not", "Arctic Monkeys", "Indie Rock", 29.99);
        Book product6 = new Book("Always ascending", "Franz Ferdinand", "Indie rock", 39.99);
        Book product7 = new Book("Chapter 11", "HIM", "Metal", 69.99);
        Book product8 = new Book("Dark light", "HIM", "Metal", 29.99);
        Book product9 = new Book("Devils", "69 eyes", "Hard Rock", 9.99);
        Book product10 = new Book("Fallen", "Evanescene", "Hard Rock", 19.99);
        Book product11 = new Book("Bright skies", "The howling bells", "Indie Rock", 29.99);
        Book product12 = new Book("Ori and the blind forest", "Gareth Coker", "Game Soundtrack", 29.99);
        Book product13 = new Book("Primal", "Gareth Coker", "Game Soundtrack", 29.99);
        Book product14 = new Book("Tonight", "Franz Ferdidand", "Indie Rock", 34.97);

        byte[] img1 = ImageUtils.getBytesFromResource(context, R.drawable.lovemetal);
        byte[] img2 = ImageUtils.getBytesFromResource(context, R.drawable.aeriale);
        byte[] img3 = ImageUtils.getBytesFromResource(context, R.drawable.alwaysascending);
        byte[] img4 = ImageUtils.getBytesFromResource(context, R.drawable.chapter);
        byte[] img5 = ImageUtils.getBytesFromResource(context, R.drawable.dark);
        byte[] img6 = ImageUtils.getBytesFromResource(context, R.drawable.devils);
        byte[] img7 = ImageUtils.getBytesFromResource(context, R.drawable.fallen);
        byte[] img8 = ImageUtils.getBytesFromResource(context, R.drawable.howling);
        byte[] img9 = ImageUtils.getBytesFromResource(context, R.drawable.ori);
        byte[] img10 = ImageUtils.getBytesFromResource(context, R.drawable.primal);
        byte[] img11 = ImageUtils.getBytesFromResource(context, R.drawable.tonight);
        byte[] img12 = ImageUtils.getBytesFromResource(context, R.drawable.xx);
        byte[] img13 = ImageUtils.getBytesFromResource(context, R.drawable.album1);
        byte[] img14 = ImageUtils.getBytesFromResource(context, R.drawable.isthisit);

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
        details.setDescription("The production of Mezzanine was a stressful process, with tensions arising within the group, it almost split the band.[2] They disagreed about the musical direction for the new material. Robert Del Naja first started making samples from new wave records, from the likes of Wire and Gang of Four: it was the music he'd listened to in his early teens. Del Naja wanted Massive Attack to make an album having this astmosphere of edginess and paranoia present in the music of the late 1970s");
        Favorites favorites = new Favorites(1L);
        daoSession.getFavoritesDao().insert(favorites);
        details.setBook(product1);
        daoSession.getBookDetailsDao().insert(details);
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
        daoSession.getBookDetailsDao().insert(details);

        Image image1 = new Image();
        image1.setImage(img1);
        image1.setProductId(product1.getId());
        Image image2 = new Image();
        image2.setProductId(product2.getId());
        image2.setImage(img2);
        Image image3 = new Image();
        image3.setProductId(product3.getId());
        image3.setImage(img3);
        Image image4 = new Image();
        image4.setProductId(product4.getId());
        image4.setImage(img4);
        Image image5 = new Image();
        image5.setProductId(product5.getId());
        image5.setImage(img5);
        Image image6 = new Image();
        image6.setProductId(product6.getId());
        image6.setImage(img6);
        Image image7 = new Image();
        image7.setProductId(product7.getId());
        image7.setImage(img7);
        Image image8 = new Image();
        image8.setProductId(product8.getId());
        image8.setImage(img8);
        Image image9 = new Image();
        image9.setProductId(product9.getId());
        image9.setImage(img9);
        Image image10 = new Image();
        image10.setProductId(product10.getId());
        image10.setImage(img10);
        Image image11 = new Image();
        image11.setProductId(product11.getId());
        image11.setImage(img11);
        Image image12 = new Image();
        image12.setProductId(product12.getId());
        image12.setImage(img12);
        Image image13 = new Image();
        image13.setProductId(product13.getId());
        image13.setImage(img13);
        Image image14 = new Image();
        image14.setProductId(product14.getId());
        image14.setImage(img14);
        daoSession.getImageDao().insert(image1);
        daoSession.getImageDao().insert(image2);
        daoSession.getImageDao().insert(image3);
        daoSession.getImageDao().insert(image4);
        daoSession.getImageDao().insert(image5);
        daoSession.getImageDao().insert(image6);
        daoSession.getImageDao().insert(image7);
        daoSession.getImageDao().insert(image8);
        daoSession.getImageDao().insert(image9);
        daoSession.getImageDao().insert(image10);
        daoSession.getImageDao().insert(image11);
        daoSession.getImageDao().insert(image12);
        daoSession.getImageDao().insert(image13);
        daoSession.getImageDao().insert(image14);


       List<Image> images = Arrays.asList(image1, image2, image3, image4, image5, image6, image7, image8, image9, image10, image11, image12, image13, image14);
        BooksImages booksImages = new BooksImages();
        booksImages.setBook(details.getId());
        booksImages.setImage(image1.getId());
        daoSession.getBooksImagesDao().insert(booksImages);

        BooksImages booksImages2 = new BooksImages();
        booksImages2.setBook(details.getId());
        booksImages2.setImage(image2.getId());
        daoSession.getBooksImagesDao().insert(booksImages2);


    }
}
