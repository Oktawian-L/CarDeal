package pl.szop.cardealshop.data;

import java.util.List;

import pl.szop.cardealshop.models.Image;
import pl.szop.cardealshop.models.Product;
import pl.szop.cardealshop.models.Image;
import pl.szop.cardealshop.models.Product;

public interface IDataProvider {

    List<? extends Product> getProducts(Filter filter);
    List<? extends Product> getProducts();
    Product getDetails(Long id);
    List<Image> getImages(Long productId);
}
