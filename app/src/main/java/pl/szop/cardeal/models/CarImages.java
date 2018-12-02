package pl.szop.cardeal.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "car_images")
public class CarImages {

    @Id
    private Long id;

    private Long car;
    private Long image;


    @Generated(hash = 1611942334)
    public CarImages(Long id, Long car, Long image) {
        this.id = id;
        this.car = car;
        this.image = image;
    }


    @Generated(hash = 955502447)
    public CarImages() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getCar() {
        return this.car;
    }


    public void setCar(Long car) {
        this.car = car;
    }


    public Long getImage() {
        return this.image;
    }


    public void setImage(Long image) {
        this.image = image;
    }

}
