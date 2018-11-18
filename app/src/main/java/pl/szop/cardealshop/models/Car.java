package pl.szop.cardealshop.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import pl.szop.andrzejshop.models.Product;

@Entity(nameInDb = "cars")
public class Car extends Product {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "model")
    private String model;

    @Property(nameInDb = "marka")
    private String marka;

    @Property(nameInDb = "typ")
    private String typ;

    @Property(nameInDb = "price")
    private Double price;

    @Property(nameInDb = "pomotional_price")
    private Double promotionalPrice;

    @Property(nameInDb = "photo")
    private byte[] photo;

    public Car() {

    }

    public Car(String model, String marka, String typ, Double price) {
        setModel(model);
        setMarka(marka);
        setTyp(typ);
        setPrice(price);
    }

    @Generated(hash = 416594735)
    public Car(Long id, String model, String marka, String typ, Double price,
            Double promotionalPrice, byte[] photo) {
        this.id = id;
        this.model = model;
        this.marka = marka;
        this.typ = typ;
        this.price = price;
        this.promotionalPrice = promotionalPrice;
        this.photo = photo;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return this.model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String getMarka() {
        return this.marka;
    }
    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTyp() {
        return this.typ;
    }
    public void setTyp(String typ) {
        this.typ = typ;
    }

    public Double getPrice() {
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }


    public Double getPromotionalPrice() {
        return this.promotionalPrice;
    }
    public void setPromotionalPrice(Double promotionalPrice) {
        this.promotionalPrice = promotionalPrice;
    }

    public byte[] getCover() {
        return this.photo;
    }
    public void setCover(byte[] photo) {
        this.photo = photo;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
