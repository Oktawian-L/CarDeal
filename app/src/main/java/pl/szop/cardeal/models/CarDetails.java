package pl.szop.cardeal.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;

//@Entity(nameInDb = cardetails)
@Entity(nameInDb = "car_details")
public class CarDetails extends Product {

    @ToOne(joinProperty = "id")
    private Car car;

    @Id
    private Long id;

    @Property(nameInDb = "description")
    private String description;

    @Property(nameInDb = "rating")
    private Double rating;

   // @ToMany(referencedJoinProperty = "productId")
   // private ListImage images;

    @Property(nameInDb = "moc")
    private String moc;

    @Property(nameInDb = "nadwozie")
    private String nadwozie;

    @Property(nameInDb = "has_abs")
    private String has_abs;

    @Property(nameInDb = "has_klima")
    private String has_klima;

    @Property(nameInDb = "przebieg")
    private int przebieg;

    @Property(nameInDb = "nr_silnika")
    private int nr_silnika;

<<<<<<< HEAD
    @Property(nameInDb = "relase_year")
     private int releaseYear;
=======
    // @Property(nameInDb = relase_year)
    // private int releaseYear;

    //Used to resolve relations
    // @Generated(hash = 2040040024)
    //  private transient DaoSession daoSession;

    //Used for active entity operations.
    // @Generated(hash = 62405507)
    // private transient CarDetailsDao myDao;
>>>>>>> master

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 62405507)
    private transient CarDetailsDao myDao;

    @Generated(hash = 440805916)
    private transient Long car__resolvedKey;

<<<<<<< HEAD
    @Generated(hash = 1909274008)
    public CarDetails(Long id, String description, Double rating, String moc, String nadwozie, String has_abs,
            String has_klima, int przebieg, int nr_silnika, int releaseYear) {
=======
    @Generated(hash = 1985101679)
    public CarDetails(Long id, String description, Double rating, String moc, String nadwozie, String has_abs,
            String has_klima, int przebieg, int nr_silnika) {
>>>>>>> master
        this.id = id;
        this.description = description;
        this.rating = rating;
        this.moc = moc;
        this.nadwozie = nadwozie;
        this.has_abs = has_abs;
        this.has_klima = has_klima;
        this.przebieg = przebieg;
        this.nr_silnika = nr_silnika;
<<<<<<< HEAD
        this.releaseYear = releaseYear;
=======
>>>>>>> master
    }

    @Generated(hash = 1517749497)
    public CarDetails() {
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

    public String getMoc() {
        return this.moc;
    }

    public void setMoc(String moc) {
        this.moc = moc;
    }

    public String getNadwozie() {
        return this.nadwozie;
    }

    public void setNadwozie(String nadwozie) {
        this.nadwozie = nadwozie;
    }

    public String getHas_abs() {
        return this.has_abs;
    }

    public void setHas_abs(String has_abs) {
        this.has_abs = has_abs;
    }

    public String getHas_klima() {
        return this.has_klima;
    }

    public void setHas_klima(String has_klima) {
        this.has_klima = has_klima;
    }

    public int getPrzebieg() {
        return this.przebieg;
    }

    public void setPrzebieg(int przebieg) {
        this.przebieg = przebieg;
    }

    public int getNr_silnika() {
        return this.nr_silnika;
    }

    public void setNr_silnika(int nr_silnika) {
        this.nr_silnika = nr_silnika;
    }

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 847991781)
    public Car getCar() {
        Long __key = this.id;
        if (car__resolvedKey == null || !car__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CarDao targetDao = daoSession.getCarDao();
            Car carNew = targetDao.load(__key);
            synchronized (this) {
                car = carNew;
                car__resolvedKey = __key;
            }
        }
        return car;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1489653394)
    public void setCar(Car car) {
        synchronized (this) {
            this.car = car;
            id = car == null ? null : car.getId();
            car__resolvedKey = id;
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

<<<<<<< HEAD
    public int getReleaseYear() {
        return this.releaseYear;
    }

   public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1550864587)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getCarDetailsDao() : null;
}
=======
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1550864587)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCarDetailsDao() : null;
    }

   /* public int getReleaseYear() {
        return this.releaseYear;
    }*/

   /* public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }*/
>>>>>>> master

}
