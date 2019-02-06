package pl.szop.andrzejshop.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "authors")
public class Author extends GenericModel{

    @Id
    private Long id;

    @Property(nameInDb = "firstName")
    private String firstName;

    @Property(nameInDb = "lastName")
    private String lastName;

    @Generated(hash = 920574227)
    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Generated(hash = 64241762)
    public Author() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }
}
