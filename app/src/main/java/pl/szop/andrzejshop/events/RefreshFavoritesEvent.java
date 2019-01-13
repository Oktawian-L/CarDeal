package pl.szop.andrzejshop.events;

import pl.szop.andrzejshop.models.GenericModel;

public class RefreshFavoritesEvent {

    private GenericModel mProduct;

    public RefreshFavoritesEvent(GenericModel product){
        mProduct = product;
    }

    public GenericModel getProduct() {
        return mProduct;
    }
}
