package pl.szop.cardeal.data;

import java.util.List;

import pl.szop.cardeal.models.Image;
import pl.szop.cardeal.models.Product;

public interface IDataProvider {

    List<? extends Product> getProducts(Filter filter);
    List<? extends Product> getProducts();
    Product getDetails(Long id);
    List<Image> getImages(Long productId);
}
