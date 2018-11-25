package pl.szop.andrzejshop.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(nameInDb = "cartitem")
public class CartItem extends Product {

    @Id(autoincrement = false)
    private Long id;

    @ToOne(joinProperty = "id")
    private Book book;

    @Generated
    private int amount;


    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 1725826038)
    private transient CartItemDao myDao;

    @Generated(hash = 893611298)
    private transient Long book__resolvedKey;

   @Generated(hash = 273818085)
public CartItem(Long id, int amount) {
    this.id = id;
    this.amount = amount;
}


@Generated(hash = 1451153759)
public CartItem() {
}
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getCover() {
        return book.getCover();
    }

    public void setPrice(double price) {
       this.book.setPrice(price);
    }

    public String getTitle() {
        return book.getTitle();
    }

    public String getAuthor() {
        return book.getAuthor();
    }
    public double getPrice() {
       return book.getPrice();
    }


    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1425297770)
    public Book getBook() {
        Long __key = this.id;
        if (book__resolvedKey == null || !book__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            BookDao targetDao = daoSession.getBookDao();
            Book bookNew = targetDao.load(__key);
            synchronized (this) {
                book = bookNew;
                book__resolvedKey = __key;
            }
        }
        return book;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1395036341)
    public void setBook(Book book) {
        synchronized (this) {
            this.book = book;
            id = book == null ? null : book.getId();
            book__resolvedKey = id;
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


    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 73267621)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCartItemDao() : null;
    }
}