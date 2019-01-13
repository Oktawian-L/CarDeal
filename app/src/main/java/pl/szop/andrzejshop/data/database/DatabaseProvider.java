package pl.szop.andrzejshop.data.database;

import java.util.ArrayList;
import java.util.List;

import kotlin.NotImplementedError;
import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.data.IDataProvider;
import pl.szop.andrzejshop.data.criteria.Filter;
import pl.szop.andrzejshop.data.criteria.FilterGroup;
import pl.szop.andrzejshop.data.criteria.Sort;
import pl.szop.andrzejshop.models.Author;
import pl.szop.andrzejshop.models.Category;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.DaoSession;
import pl.szop.andrzejshop.models.Favorites;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Image;
import pl.szop.andrzejshop.models.Language;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.models.Publisher;

public class DatabaseProvider implements IDataProvider {

    private DaoSession mDaoSession;

    public DatabaseProvider(DaoSession daoSession){
        mDaoSession = daoSession;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    @Override
    public  List<? extends GenericModel> getProducts(Criteria criteria) {
        Class productClass = DatabaseProviderSettings.PRODUCT_CLASS;
        // if filter is empty, load all products
        if(criteria == null || criteria.isEmpty()){
            return (List<? extends GenericModel>) mDaoSession.getDao(productClass).loadAll();
        }
        // else create where statement and load data from database
        String where = criteria.hasConditions() ? createWhere(criteria) : "";
        String[] arguments = criteria.hasConditions()? getWhereArguments(criteria) : new String[0];

        if (criteria.hasSorting()){
            where += createOrderBy(criteria);
        }
        if(criteria.getLimit() != null){
            where += " LIMIT " + String.valueOf(criteria.getLimit());
        }
        return (List<? extends GenericModel>) mDaoSession.getDao(productClass).queryRaw(where, arguments);
    }

    @Override
    public GenericModel getDetails(Long id) {
        return DatabaseProviderSettings.getDetailsDao(mDaoSession).loadDeep(id);
    }

    @Override
    public List<Image> getImages(Long productId) {
        return getDaoSession().getImageDao().queryRaw("WHERE product = ?", new String[]{String.valueOf(productId)});
    }

    @Override
    public boolean isFavorite(Long id) {
        Favorites favorites = getDaoSession().getFavoritesDao().load(id);
        return favorites != null;
    }

    @Override
    public void setFavorite(Long id, boolean favorite) {
        Favorites favorites = new Favorites(id);
        if(favorite){
            getDaoSession().getFavoritesDao().insert(favorites);
        } else {
            getDaoSession().getFavoritesDao().delete(favorites);
        }
    }

    @Override
    public List<? extends GenericModel> getFavorites() {
        return getDaoSession().getFavoritesDao().loadAll();
    }

    @Override
    public List<Category> getCategories() {
        return getDaoSession().getCategoryDao().loadAll();
    }


    @Override
    public void addToCart(Long id) {
        CartItem item = new CartItem();
        item.setId(id);
        getDaoSession().getCartItemDao().insert(item);
    }
    @Override
    public CartItem getItem(long id) {
        return getDaoSession().getCartItemDao().load(id);
    }
    @Override
    public void updateAmount(CartItem id) {
        getDaoSession().getCartItemDao().update(id);
    }
    @Override
    public void removeFromCart(long id) {
        getDaoSession().getCartItemDao().deleteByKey(id);
    }

    @Override
    public List<? extends GenericModel> getCartItems() {
        return getDaoSession().getCartItemDao().loadAll();
    }

    @Override
    public boolean isInCart(Long id) {
        return getDaoSession().getCartItemDao().load(id) != null;
    }

    @Override
    public List<Author> getAuthors() {
        return getDaoSession().getAuthorDao().loadAll();
    }

    @Override
    public int getCartSize() {
        return (int) getDaoSession().getCartItemDao().count();
    }

    @Override
    public List<Promotions> getPromotions(Criteria criteria) {
        // TODO pomyśleć nad jakimś innym sposobem
        if(criteria.getLimit() != null){
            String where = " LIMIT ?";
            String[] whereArgs = new String[]{String.valueOf(criteria.getLimit())};
            return getDaoSession().getPromotionsDao().queryRaw(where, whereArgs);
        }
        return getDaoSession().getPromotionsDao().loadAll();
    }

    @Override
    public Promotions getPromotion(Long id) {
        return getDaoSession().getPromotionsDao().load(id);
    }

    @Override
    public List<Publisher> getPublishers() {
        return getDaoSession().getPublisherDao().loadAll();
    }

    @Override
    public List<Language> getLanguages() {
        return getDaoSession().getLanguageDao().loadAll();
    }

    @Override
    public Publisher getPublisher(Long id) {
        throw new NotImplementedError();
    }

    @Override
    public Language getLanguage(Long id) {
        throw new NotImplementedError();
    }

    private String createWhere(Criteria criteria) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("WHERE ");

        String filtersConditions = getFilterConditions(criteria);
        stringBuilder.append(filtersConditions);
        if(criteria.hasText() && !filtersConditions.isEmpty()){
            stringBuilder.append(" AND ");
        }
        if(criteria.hasText()){
            stringBuilder.append(getTextConditions());
        }
        if(criteria.getLimit() != null){ // TODO można zastąpić to hasLimit
            stringBuilder.append(" LIMIT " + String.valueOf(criteria.getLimit()));
        }

        return stringBuilder.toString();
    }

    private String getTextConditions(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(");
        String[] whereColumns = DatabaseProviderSettings.SEARCH_COLUMN;
        for(int i=0; i<whereColumns.length; i++){
            stringBuilder.append(whereColumns[i]);
            stringBuilder.append(" LIKE ?");
            if(i != whereColumns.length -1){
                stringBuilder.append(" OR ");
            }
        }
        // TODO very ugly hack
        stringBuilder.append(" OR ");
        stringBuilder.append("author IN (SELECT _id FROM authors WHERE firstName LIKE ?)");
        stringBuilder.append(" OR ");
        stringBuilder.append("author IN (SELECT _id FROM authors WHERE lastName LIKE ?)");
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String getFilterConditions(Criteria criteria) {
        StringBuilder stringBuilder = new StringBuilder();
        boolean firstCondition = true;
        for(FilterGroup filter : criteria.getFilters()) {
            if(!firstCondition){
                stringBuilder.append(" AND ");
            }
            firstCondition = false;

            stringBuilder.append(filter.getQuery());
        }
        return stringBuilder.toString();
    }

    private String getOption(Filter.Option option) {
        switch (option){
            case EQUAL:
                return " = ";
            case IN:
                return " IN ";
            case LESS:
                return " < ";
            case GREATER:
                return " > ";
                default:
                throw new IllegalArgumentException("Unknown filter option");
        }
    }

    private String createOrderBy(Criteria filter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ORDER BY ");
        Sort sort = filter.getSort();
        stringBuilder.append(sort.getName());
        if(sort.isDesc()){
            stringBuilder.append(" ").append("DESC");
        }
        return stringBuilder.toString();

    }

    private String[] getWhereArguments(Criteria criteria){
        List<String> arguments = new ArrayList<>();
        if(criteria.hasFilters()) {
            List<String> filterArguments = new ArrayList<>();
            for(FilterGroup filter : criteria.getFilters()) {
                for(Object value : filter.getValues()){
                    filterArguments.add(value.toString());
                }
//                filterArguments.add(filter.getValue().toString());
            }
            arguments.addAll(filterArguments);
        }
        if(criteria.hasText()) {
            int searchArgumentsSize = DatabaseProviderSettings.SEARCH_COLUMN.length;
            String filterText = "%"+criteria.getText()+"%";
            List<String> searchArguments = new ArrayList<>();
            for(int i=0; i<searchArgumentsSize; i++){
                searchArguments.add(filterText);
            }
            // TODO very ugly hack
            searchArguments.add(filterText);
            searchArguments.add(filterText);
            arguments.addAll(searchArguments);
        }

        return arguments.toArray(new String[0]);
    }
}
