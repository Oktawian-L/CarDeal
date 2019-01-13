package pl.szop.andrzejshop.events;

import pl.szop.andrzejshop.data.criteria.Criteria;

public class NavigationEvent {
    public enum View{
        CART,
        PRODUCTS,
        MAIN,
        CATEGORIES,
        WISH_LIST
    }

    private View mView;
    private Criteria mCriteria;

    public NavigationEvent(View view) {
        mView = view;
    }

    public NavigationEvent(View view, Criteria criteria){
        mView = view;
        mCriteria = criteria;
    }

    public void setCriteria(Criteria criteria){
        mCriteria = criteria;
    }

    public Criteria getCriteria(){
        return mCriteria;
    }

    public View getView(){
        return mView;
    }
}
