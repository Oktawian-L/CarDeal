package pl.szop.cardealshop.data.database;

import java.util.List;
import pl.szop.cardealshop.data.Filter;
import pl.szop.cardealshop.data.IDataProvider;
import pl.szop.cardealshop.models.DaoSession;
import pl.szop.cardealshop.models.Image;
import pl.szop.cardealshop.models.Product;
import pl.szop.cardealshop.models.Image;
import pl.szop.cardealshop.models.Product;

public class ProductDAO implements IDataProvider {

    private DaoSession mDaoSession;

    public ProductDAO(DaoSession daoSession){
        mDaoSession = daoSession;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    @Override
    public List<? extends Product> getProducts(Filter filter) {
        Class productClass = ProductDAOSettings.PRODUCT_CLASS;
        if(filter == null || filter.isEmpty()){
            return (List<? extends Product>) mDaoSession.getDao(productClass).loadAll();
        }
        String where = createWhere(filter);
        String[] arguments = getWhereArguments(filter);
        return (List<? extends Product>) mDaoSession.getDao(productClass).queryRaw(where, arguments);
    }

    @Override
    public List<? extends Product> getProducts(){
        return getProducts((Filter) null);
    }

    @Override
    public Product getDetails(Long id) {
        return ProductDAOSettings.getDetailsDao(mDaoSession).loadDeep(id);
    }

    @Override
    public List<Image> getImages(Long productId) {
        return getDaoSession().getImageDao().queryRaw("WHERE product = ?", new String[]{String.valueOf(productId)});
    }

    private String createWhere(Filter filter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WHERE ");
        String[] whereColumns = ProductDAOSettings.SEARCH_COLUMN;
        for(int i=0; i<whereColumns.length; i++){
            stringBuilder.append(whereColumns[i]);
            stringBuilder.append(" LIKE ?");
            if(i != whereColumns.length -1){
                stringBuilder.append(" OR ");
            }
        }

        return stringBuilder.toString();
    }

    private String[] getWhereArguments(Filter filter){
        String[] arguments = new String[ProductDAOSettings.SEARCH_COLUMN.length];
        String filterText = "%"+filter.getText()+"%";
        for(int i=0; i < arguments.length; i++){
            arguments[i] = filterText;
        }

        return arguments;
    }
}