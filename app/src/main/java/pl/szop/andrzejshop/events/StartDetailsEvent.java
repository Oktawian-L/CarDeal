package pl.szop.andrzejshop.events;

public class StartDetailsEvent {

    private Long mProductId;

    public StartDetailsEvent(Long productId){
        mProductId = productId;
    }

    public Long getProductId(){
        return mProductId;
    }
}
