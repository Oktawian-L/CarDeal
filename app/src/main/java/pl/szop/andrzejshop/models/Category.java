package pl.szop.andrzejshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.utils.ResourceUtils;

// TODO zmienić nazwę klasy GenericModel
@Entity(nameInDb = "categories")
public class Category extends GenericModel implements Parcelable {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "name")
    private String name;

    @Property(nameInDb = "image")
    private byte[] image;

    protected Category(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        name = in.readString();
    }

    @Generated(hash = 666698067)
    public Category(Long id, String name, byte[] image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public Category(String name){
        this.name = name;
    }

    @Generated(hash = 1150634039)
    public Category() {
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0; 
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(name);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLocalisedName(){
        return ResourceUtils.getStringByName(this.name, MyApplication.instance().getContext());
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return getLocalisedName();
    }

    public byte[] getImage() {
        return this.image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
