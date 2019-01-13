package pl.szop.andrzejshop.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.ToOne;

@Entity(nameInDb = "book_details")
public class AutoDetails extends Product{

    @ToOne(joinProperty = "id")
    private Auto auto;

    @Id
    private Long id;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "rating")
    private Double rating;

//    @ToMany
//    @JoinEntity(
//            entity = AutoImages.class,
//            sourceProperty = "auto",
//            targetProperty = "image"
//    )
//    private List<Image> images;

    @ToMany(referencedJoinProperty = "productId")
    private List<Image> images;


    @Property(nameInDb = "translation")
    private String translation;

    @Property(nameInDb = "publisher")
    private String publisher;

    @Property(nameInDb = "language")
    private String language;

    @Property(nameInDb = "org_language")
    private String orgLanguage;

    @Property(nameInDb = "pages")
    private int pages;

    @Property(nameInDb = "release_number")
    private int releaseNumber;

    @Property(nameInDb = "relase_year")
    private int releaseYear;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1281899595)
    private transient AutoDetailsDao myDao;

    @Generated(hash = 2039703661)
    public AutoDetails(Long id, String description, Double rating, String translation, String publisher,
            String language, String orgLanguage, int pages, int releaseNumber, int releaseYear) {
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.translation = translation;
        this.publisher = publisher;
        this.language = language;
        this.orgLanguage = orgLanguage;
        this.pages = pages;
        this.releaseNumber = releaseNumber;
        this.releaseYear = releaseYear;
    }

    @Generated(hash = 1042681008)
    public AutoDetails() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getTranslation() {
        return this.translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOrgLanguage() {
        return this.orgLanguage;
    }

    public void setOrgLanguage(String orgLanguage) {
        this.orgLanguage = orgLanguage;
    }

    public int getPages() {
        return this.pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReleaseNumber() {
        return this.releaseNumber;
    }

    public void setReleaseNumber(int releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle(){
        return auto.getTitle();
    }

    public String getAuthor(){
        return auto.getAuthor();
    }

    public String getCategory(){
        return auto.getCategory();
    }

    @Generated(hash = 1964815679)
    private transient Long auto__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 933141992)
    public Auto getAuto() {
        Long __key = this.id;
        if (auto__resolvedKey == null || !auto__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AutoDao targetDao = daoSession.getAutoDao();
            Auto autoNew = targetDao.load(__key);
            synchronized (this) {
                auto = autoNew;
                auto__resolvedKey = __key;
            }
        }
        return auto;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 256181844)
    public void setAuto(Auto auto) {
        synchronized (this) {
            this.auto = auto;
            id = auto == null ? null : auto.getId();
            auto__resolvedKey = id;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 703104145)
    public List<Image> getImages() {
        if (images == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ImageDao targetDao = daoSession.getImageDao();
            List<Image> imagesNew = targetDao._queryAutoDetails_Images(id);
            synchronized (this) {
                if (images == null) {
                    images = imagesNew;
                }
            }
        }
        return images;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 604059028)
    public synchronized void resetImages() {
        images = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1141292141)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getAutoDetailsDao() : null;
    }

    /** Used to resolve relations */
   

}
