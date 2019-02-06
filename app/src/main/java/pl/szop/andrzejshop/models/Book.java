package pl.szop.andrzejshop.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.NotNull;

import java.text.DecimalFormat;

import pl.szop.andrzejshop.utils.DateUtils;

@Entity(nameInDb = "books")
public class Book extends GenericModel {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "title")
    private String title;

    @Property(nameInDb = "author")
    private long authorId;

    @ToOne(joinProperty = "authorId")
    private Author author;

    @Property(nameInDb = "category")
    private long categoryId;

    @ToOne(joinProperty = "categoryId")
    private Category category;

    @Property(nameInDb = "price")
    private Double price;

    @Property(nameInDb = "pomotional_price")
    private Double promotionalPrice;

    @Property(nameInDb = "cover")
    private byte[] cover;

    @Property(nameInDb = "release_date")
    private Long releaseDate;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1097957864)
    private transient BookDao myDao;

    @Generated(hash = 1372501278)
    private transient Long category__resolvedKey;

    @Generated(hash = 1107320010)
    private transient Long author__resolvedKey;

    public Book() {

    }

    public Book(String title, Author autor, Category category, Double price) {
        setTitle(title);
        setAuthor(autor);
        setCategory(category);
        setPrice(price);
    }

    @Generated(hash = 739805090)
    public Book(Long id, String title, long authorId, long categoryId, Double price,
            Double promotionalPrice, byte[] cover, Long releaseDate) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.price = price;
        this.promotionalPrice = promotionalPrice;
        this.cover = cover;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceText(){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(this.price) + " zł";
    }

    public Double getPromotionalPrice() {
        return this.promotionalPrice;
    }

    public void setPromotionalPrice(Double promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public byte[] getCover() {
        return this.cover;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 391889431)
    public Author getAuthor() {
        long __key = this.authorId;
        if (author__resolvedKey == null || !author__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            AuthorDao targetDao = daoSession.getAuthorDao();
            Author authorNew = targetDao.load(__key);
            synchronized (this) {
                author = authorNew;
                author__resolvedKey = __key;
            }
        }
        return author;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2013409422)
    public void setAuthor(@NotNull Author author) {
        if (author == null) {
            throw new DaoException(
                    "To-one property 'authorId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.author = author;
            authorId = author.getId();
            author__resolvedKey = authorId;
        }
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 234631651)
    public Category getCategory() {
        long __key = this.categoryId;
        if (category__resolvedKey == null || !category__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CategoryDao targetDao = daoSession.getCategoryDao();
            Category categoryNew = targetDao.load(__key);
            synchronized (this) {
                category = categoryNew;
                category__resolvedKey = __key;
            }
        }
        return category;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1927364589)
    public void setCategory(@NotNull Category category) {
        if (category == null) {
            throw new DaoException(
                    "To-one property 'categoryId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.category = category;
            categoryId = category.getId();
            category__resolvedKey = categoryId;
        }
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

    public Long getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDateText(){
        return DateUtils.toString(this.releaseDate);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1115456930)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getBookDao() : null;
    }




}
