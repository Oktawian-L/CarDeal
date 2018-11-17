package pl.szop.cardealshop.data;

import java.util.List;

import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.models.Product;

public interface IDataProvider {

    List<? extends Product> getProducts(pl.szop.cardealshop.data.Filter filter);
    List<? extends Product> getProducts();
    Product getDetails(Long id);
    List<Image> getImages(Long productId);
}
