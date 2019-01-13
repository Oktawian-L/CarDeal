package pl.szop.andrzejshop.data.criteria;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FilterGroup implements Parcelable {


    public enum Type{
        AND(0),
        OR(1);

        int value;

        Type(int value){
            this.value = value;
        }

        int getValue(){
            return this.value;
        }
    }

    private String mName;
    private List<Filter> mFilters;
    private Type mType;

    public FilterGroup(String name, Type type, Filter... filters){
        mName = name;
        mType = type;
        mFilters = new ArrayList<>();
        for (Filter filter : filters){
            if(filter.getValue() != null){
                mFilters.add(filter);
            }
        }
    }

    protected FilterGroup(Parcel in) {
        mName = in.readString();
        mType = Type.values()[in.readInt()];
//        mFilters = in.createTypedArrayList(Filter.CREATOR);
        mFilters = new ArrayList<>();
        in.readTypedList(mFilters, Filter.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeInt(mType.getValue());
        dest.writeTypedList(mFilters);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FilterGroup> CREATOR = new Creator<FilterGroup>() {
        @Override
        public FilterGroup createFromParcel(Parcel in) {
            return new FilterGroup(in);
        }

        @Override
        public FilterGroup[] newArray(int size) {
            return new FilterGroup[size];
        }
    };

    public String getName(){
        return mName;
    }

    // TODO zmienić nazwę
    public String getQuery(){
        if(mFilters.isEmpty()){
            return ""; // TODO może być konieczne wyrzucenie nulla
        }
        StringBuilder queryBuilder = new StringBuilder();
        boolean firstCondition = true;
        queryBuilder.append("(");
        for (Filter filter : mFilters){
            if(!firstCondition){
                queryBuilder.append(getJoin(mType));
            }
            firstCondition = false;
            queryBuilder.append(filter.getField());
            queryBuilder.append(filter.getOption().getText());
            queryBuilder.append("?");
        }
        queryBuilder.append(")");
        return queryBuilder.toString();
    }

    public List<Object> getValues(){
        List<Object> values = new ArrayList<>();
        for(Filter filter : mFilters){
            values.add(filter.getValue());
        }
        return values;
    }


    // TODO zmienić nazwę
    private String getJoin(Type type){
        switch (type){
            case AND:
                return " AND ";
            case OR:
                return " OR ";
        }
        throw new IllegalArgumentException();
    }
}
