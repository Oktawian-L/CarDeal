package pl.szop.andrzejshop.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "publishers")
public class Publisher extends GenericModel{

    @Id
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Generated(hash = 342604881)
    public Publisher(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 1150157344)
    public Publisher() {
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
