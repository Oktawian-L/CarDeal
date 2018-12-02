package pl.szop.cardeal.data;

import java.util.List;

import pl.szop.cardeal.models.CartItem;
import pl.szop.cardeal.models.Image;
import pl.szop.cardeal.models.Product;

public interface IDataProvider {

    List<? extends Product> getProducts(Filter filter);
    List<? extends Product> getProducts();
    Product getDetails(Long id);
    List<Image> getImages(Long productId);
    void addToCart(CartItem ci);
    void removeFromCart(long id);
    void updateAmount(CartItem ci);
    CartItem getItem(long id);
    List<? extends Product> getCartItems();
}
