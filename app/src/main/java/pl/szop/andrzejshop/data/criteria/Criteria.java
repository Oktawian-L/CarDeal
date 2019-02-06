package pl.szop.andrzejshop.data.criteria;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Criteria implements Parcelable {

    private String mText;
    private Sort mSort;

    private Map<String, FilterGroup> mFilters;

    private Integer mLimit;

    public Criteria() {
        mFilters = new HashMap<>();
    }

    protected Criteria(Parcel in) {
        mText = in.readString();
        mSort = in.readParcelable(Sort.class.getClassLoader());
        List<FilterGroup> filters = in.createTypedArrayList(FilterGroup.CREATOR);
        mFilters = new HashMap<>();
        for(FilterGroup filter : filters){
            mFilters.put(filter.getName(), filter);
        }
    }

    public static final Creator<Criteria> CREATOR = new Creator<Criteria>() {
        @Override
        public Criteria createFromParcel(Parcel in) {
            return new Criteria(in);
        }

        @Override
        public Criteria[] newArray(int size) {
            return new Criteria[size];
        }
    };

    public String getText(){
        if(mText == null || mText.isEmpty()){
            return null;
        }
        return mText;
    }

    public Collection<FilterGroup> getFilters() {
        return mFilters.values();
    }

    public FilterGroup getFilter(String name) {
        return mFilters.get(name);
    }

    public void setFilter(String name, Filter filter){
        if(filter.getValue()== null){
            mFilters.remove(name);
        } else {
            FilterGroup filterGroup = new FilterGroup(name, FilterGroup.Type.AND, filter);
            mFilters.put(name, filterGroup);
        }
    }

    public void setFilter(String name, FilterGroup.Type type, Filter... filters){
        FilterGroup filterGroup = new FilterGroup(name, type, filters);
        if(filterGroup.getValues().isEmpty()){
            mFilters.remove(name);
        } else {
            mFilters.put(name, filterGroup);
        }
    }

    public Integer getLimit(){
        return mLimit;
    }

    public void setLimit(int limit){
        mLimit = limit;
    }

    public void setFilter(Filter filter){
        setFilter(filter.getField(), filter);
    }

    public boolean hasText(){
        return mText != null && !mText.isEmpty();
    }

    public Sort getSort(){
        return mSort;
    }

    public void setSorting(Sort sort){
        this.mSort = sort;
    }

    public void setText(String text){
        mText = text;
    }

    public boolean isEmpty(){
        return !hasConditions() && !hasSorting() && !hasLimit();
    }

    public boolean hasLimit(){
        return mLimit != null;
    }

    public boolean hasSorting(){
        return getSort() != null;
    }

    public boolean hasConditions() {
        return hasText() || hasFilters();
    }

    public boolean hasFilters(){
        return !mFilters.isEmpty();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
        dest.writeParcelable(mSort, flags);
        Collection<FilterGroup> filters = mFilters.values();
        List<FilterGroup> filterList = new ArrayList<>(filters);
        dest.writeTypedList(filterList);
    }
}