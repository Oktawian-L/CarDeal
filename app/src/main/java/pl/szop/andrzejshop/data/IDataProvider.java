package pl.szop.andrzejshop.data;

import java.util.List;

import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.models.Product;

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
    boolean isFavorite(Long id);
    void setFavorite(Long id, boolean favorite);
    List<? extends Product> getFavorites();

}
