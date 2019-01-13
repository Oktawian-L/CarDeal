package pl.szop.andrzejshop.data;

import java.util.List;

import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.models.Author;
import pl.szop.andrzejshop.models.Category;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.models.Language;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.models.Publisher;

public interface IDataProvider {

    List<? extends GenericModel> getProducts(Criteria criteria);
    GenericModel getDetails(Long id);
    List<Image> getImages(Long productId);
    boolean isFavorite(Long id);
    void setFavorite(Long id, boolean favorite);
    List<? extends GenericModel> getFavorites();
    List<Category> getCategories();
    void addToCart(Long id);
    void removeFromCart(long id);
    void updateAmount(CartItem ci);
    CartItem getItem(long id);
    List<? extends GenericModel> getCartItems();
    boolean isInCart(Long id);
    List<Author> getAuthors();
    int getCartSize();
    List<Promotions> getPromotions(Criteria criteria);
    Promotions getPromotion(Long id);
    List<Publisher> getPublishers();
    List<Language> getLanguages();
    Publisher getPublisher(Long id);
    Language getLanguage(Long id);
}
