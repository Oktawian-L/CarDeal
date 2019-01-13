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
import pl.szop.andrzejshop.models.Language;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.models.Publisher;
import pl.szop.andrzejshop.utils.DateUtils;
import pl.szop.andrzejshop.utils.ImageUtils;

public class DatabaseInitializer {

    private DaoSession mDaoSession;
    private Context mContext;

    public DatabaseInitializer(DaoSession session, Context context){
        mDaoSession = session;
        mContext = context;
    }

    private Author addAuthor(String firstName, String lastName){
        Author author = new Author(firstName, lastName);
        mDaoSession.getAuthorDao().insert(author);
        return author;
    }

    private Category addCategory(String name, byte[] icon){
        Category category = new Category(name);
        category.setImage(icon);
        mDaoSession.getCategoryDao().insert(category);
        return category;
    }

    private Book addBook(String title, Author author, Category category, Double price, long releaseDate, int imageResource, Language language, Publisher publisher){
        Book book = new Book(title, author, category, price);
        book.setPublisher(publisher);
        book.setLanguage(language);
        byte[] image = ImageUtils.getBytesFromResource(mContext, imageResource);
        book.setCover(image);
        mDaoSession.getBookDao().insert(book);
        return book;
    }

    private Promotions addPromotion(Book book, Double price){
        Promotions promotion = new Promotions(book.getId(), price);
        mDaoSession.getPromotionsDao().insert(promotion);
        return promotion;
    }

    private BookDetails addBookDetails(Book book, String description){
        BookDetails details = new BookDetails();
        details.setBook(book);
        details.setDescription(description);

        mDaoSession.getBookDetailsDao().insert(details);

        return details;
    }

    private Favorites addFavorites(Book book){
        Favorites favorites = new Favorites(book.getId());
        mDaoSession.getFavoritesDao().insert(favorites);
        return favorites;
    }

    private Image addImage(int imageResource, Book book){
        Image image = new Image();
        byte[] imageBytes = ImageUtils.getBytesFromResource(mContext, imageResource);
        image.setProductId(book.getId());
        image.setImage(imageBytes);

        mDaoSession.getImageDao().insert(image);

        return image;
    }

    private Publisher addPublisher(String name){
        Publisher publisher = new Publisher();
        publisher.setName(name);

        mDaoSession.getPublisherDao().insert(publisher);

        return publisher;
    }

    private Language addLanguage(String name){
        Language language = new Language();
        language.setName(name);

        mDaoSession.getLanguageDao().insert(language);

        return language;
    }

    final String handDesc = "Edgar Freemantle w ciężkim wypadku samochodowym traci rękę i zdrowe zmysły. Nękany niekontrolowanymi napadami szału, musi zacząć życie od początku. Za radą psychologa wyrusza na Duma Key, olśniewająco piękną i odludną wyspę na wybrzeżu Florydy, należącą do sędziwej Elizabeth Eastlake. Wynajmuje tam dom, wiedząc tylko jedno: chce rysować. Tworzone z chorobliwą pasją obrazy Edgara są owocem talentu, nad którym stopniowo przestaje mieć kontrolę. Kiedy tragiczne dzieje rodziny Eastlake`ów zaczynają wyłaniać się z mroków przeszłości, nieposkromiona moc dzieł Freemantle`a objawia swe coraz bardziej przerażające i niszczycielskie możliwości.";
    final String ringDesc = "Fajny horror";
    final String machineDesc = "Uczenie maszynowe, zajmujące się algorytmami analizującymi dane, stanowi chyba najciekawszą dziedzinę informatyki. W czasach, w których generuje się olbrzymie ilości danych, samouczące się algorytmy maszynowe stanowią wyjątkową metodę przekształcania tych danych w wiedzę. W ten sposób powstało wiele innowacyjnych technologii, a możliwości uczenia maszynowego są coraz większe. Nieocenioną pomoc w rozwijaniu tej dziedziny stanowią liczne nowe biblioteki open source, które pozwalają na budowanie algorytmów w języku Python, będącym ulubionym, potężnym i przystępnym narzędziem naukowców i analityków danych.";
    final String malazDesc = "Od wydarzeń opisanych w „Bramach Domu Umarłych” minęło kilka lat. Sznur Psów uległ zagładzie. Coltaine, otaczany czcią dowódca malazańskiej Siódmej Armii, nie żyje. Tavore, siostra Ganoesa Parana i przyboczna\n" +
            "cesarzowej, przybyła do ostatniej malazańskiej placówki w Siedmiu Miastach.";
    final String hyperDesc = "W obliczu zbliżającej się nieuchronnie międzygalaktycznej wojny na planetę Hyperion przybywa siedmioro pielgrzymów: Kapłan, Żołnierz, Uczony, Poeta, Kapitan, Detektyw i Konsul.";
    final String bussDesc = "Jakaś książka o myśleniu w biznesie";
    final String trapDesc = "Przełomowa książka laureata Nagrody Nobla! Daniel Kahneman wciąga czytelnika do żywej rozmowy o ludzkim myśleniu i pokazuje, kiedy można, a kiedy nie należy ufać własnemu umysłowi. ";
    final String aliceDesc = "Alicja w krainie czarów";

    public  void init(){
        Language polishLang = addLanguage("polski");
        Language englishLang = addLanguage("angielski");

        Publisher publisher1 = addPublisher("Niebieska Róża");
        Publisher publisher2 = addPublisher("Zielony Druk");
        Publisher publisher3 = addPublisher("Kserokopyja");

        byte[] psychologyIcon = ImageUtils.getBytesFromResource(mContext, R.drawable.psychology);
        byte[] fantasyIcon = ImageUtils.getBytesFromResource(mContext, R.drawable.fantasy);
        byte[] scifiIcon = ImageUtils.getBytesFromResource(mContext, R.drawable.scifi);
        byte[] thrillerIcon = ImageUtils.getBytesFromResource(mContext,R.drawable.thriller);
        byte[] horrorIcon = ImageUtils.getBytesFromResource(mContext, R.drawable.horror);

        Category psychologyCat = addCategory("psychology", psychologyIcon);
        Category fantasyCat = addCategory("fantasy", fantasyIcon);
        Category sciFiCat = addCategory("sci_fi", scifiIcon);
        Category thrillerCat = addCategory("thriller", thrillerIcon);
        Category horrorCat = addCategory("horror", horrorIcon);


        Author kingAuth = addAuthor("Stephen", "King");
        Author suzukiAuth = addAuthor("Koji", "Suzuki");
        Author stevAuth = addAuthor("Steven", "Erikson");
        Author simmAuth = addAuthor("Dann", "Simmons");
        Author kahnAuth = addAuthor("Daniel", "Kahneman");
        Author carrolAuth = addAuthor("Lewis", "Carrol");
        Author otherAuth = addAuthor("Inny", "Autor");

        long timestamp1 = DateUtils.createTimestamp(22, 2, 2017);
        long timestamp2 = DateUtils.createTimestamp(15, 5, 2015);
        long timestamp3 = DateUtils.createTimestamp(9, 11, 2018);

        Book handBook = addBook("Ręka mistrza", kingAuth, horrorCat, 40.0, timestamp1, R.drawable.reka1, polishLang, publisher1);
        Book ringBook = addBook("Ring", suzukiAuth, horrorCat, 20.0, timestamp2, R.drawable.ring, englishLang, publisher1);
        Book machineBook = addBook("Uczenie maszynowe", otherAuth, sciFiCat, 35.0, timestamp3, R.drawable.maszyna1, polishLang, publisher2);
        Book malazBook = addBook("Malazańska księga poległych", stevAuth, fantasyCat, 60.0, timestamp1, R.drawable.ksiega1, polishLang, publisher3);
        Book hyperBook = addBook("Hyperion", simmAuth, sciFiCat, 30.0, timestamp2, R.drawable.hyperion1, englishLang, publisher3);
        Book bussBook = addBook("Myślenie w biznesie", otherAuth, psychologyCat, 14.0, timestamp3, R.drawable.byznes1,polishLang, publisher2);
        Book trapBook = addBook("Pułapki myślenia", kahnAuth, psychologyCat, 34.0, timestamp1, R.drawable.trap1, polishLang, publisher1);
        Book aliceBook = addBook("Alicja w krainie czarów", carrolAuth, fantasyCat, 10.0, timestamp2, R.drawable.alicja1, englishLang, publisher2);

        addBookDetails(handBook, handDesc);
        addBookDetails(ringBook, ringDesc);
        addBookDetails(machineBook, machineDesc);
        addBookDetails(hyperBook, hyperDesc);
        addBookDetails(bussBook, bussDesc);
        addBookDetails(trapBook, trapDesc);
        addBookDetails(aliceBook, aliceDesc);
        addBookDetails(malazBook, machineDesc);

        addImage(R.drawable.alicja1, aliceBook);
        addImage(R.drawable.trap1, trapBook);
        addImage(R.drawable.byznes1, bussBook);
        addImage(R.drawable.hyperion1, hyperBook);
        addImage(R.drawable.hyperion2, hyperBook);
        addImage(R.drawable.hyperion3, hyperBook);
        addImage(R.drawable.ksiega1, malazBook);
        addImage(R.drawable.reka1, handBook);
        addImage(R.drawable.reka2, handBook);
        addImage(R.drawable.maszyna1, machineBook);
        addImage(R.drawable.uczenie2, machineBook);
        addImage(R.drawable.ring1, ringBook);
        addImage(R.drawable.ring2, ringBook);

    }
}
