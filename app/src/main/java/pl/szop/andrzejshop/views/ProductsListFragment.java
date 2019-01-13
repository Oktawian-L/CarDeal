package pl.szop.andrzejshop.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.szop.andrzejshop.adapters.SortingAdapter;
import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.data.criteria.Sort;
import pl.szop.andrzejshop.events.NavigationEvent;
import pl.szop.andrzejshop.events.StartDetailsEvent;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;
import pl.szop.andrzejshop.mapper.ProductsAdapter;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.utils.ViewsCommon;


public class ProductsListFragment extends Fragment {

    private Button cFilterButton;
    private Button cSortButton;
    private ImageView cChangeViewButton;

    private RecyclerView cProductsList;
    private OnFragmentInteractionListener mListener;
    private ProductsAdapter mAdapter;

    private Criteria mCurrentCriteria = new Criteria();

    private Set<Long> mCart;
    private Set<Long> mWishList;
    private Map<Long, Promotions> mPromotions;

    public ProductsListFragment() {

    }

    public void setCriteria(Criteria criteria) {
        mCurrentCriteria = criteria;
    }

    // TODO: Rename and change types and number of parameters
    public static ProductsListFragment newInstance(String param1, String param2) {
        ProductsListFragment fragment = new ProductsListFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_products_list, container, false);
        initComponents(view);
        prepareProductsList(view, "");
        return view;
    }

    private void initComponents(View view) {
        cFilterButton = view.findViewById(R.id.filter_button);
        cSortButton = view.findViewById(R.id.sort_button);
        cChangeViewButton = view.findViewById(R.id.change_view_button);

        cFilterButton.setOnClickListener(v->openFilterView());
        cSortButton.setOnClickListener(v->openSortView());
        cChangeViewButton.setOnClickListener(v->changeListLayout());
    }

    private void openFilterView(){
        FilterDialogFragment dialog = new FilterDialogFragment();
        dialog.setCriteria(mCurrentCriteria);
        dialog.setListener(this::loadProducts);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        dialog.show(fragmentManager, "Filter Dialog");
    }

    private void openSortView(){
        List<Sort> sortingOptions = getSortingOptions();
        int currentSortingOptions = getCurrentSortingOptions(sortingOptions, mCurrentCriteria);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(getString(R.string.sorting));
        builder.setSingleChoiceItems(new SortingAdapter(getActivity(), sortingOptions, android.R.layout.select_dialog_singlechoice), currentSortingOptions, (dialog, which) -> {
            Criteria filter = mCurrentCriteria;
            filter.setSorting(sortingOptions.get(which));
            loadProducts(filter);
            dialog.dismiss();
        });
        builder.create().show();
    }

    private int getCurrentSortingOptions(List<Sort> sortingOptions, Criteria currentCriteria){
        Sort currentSort = currentCriteria.getSort();
        Sort sort;
        for(int i=0; i<sortingOptions.size(); i++){
            sort = sortingOptions.get(i);
            if(currentSort == null && sort == null){
                return i;
            } else if (currentSort == null || sort == null){
                continue;
            }
            if(currentSort.equals(sort)){
                return i;
            }
        }
        throw new IllegalArgumentException();
    }

    @NonNull
    private List<Sort> getSortingOptions() {
        List<Sort> sortingOptions = new ArrayList<>();
        sortingOptions.add(null);
        sortingOptions.add(new Sort("title", false));
        sortingOptions.add(new Sort("title", true));
        sortingOptions.add(new Sort("author", false));
        sortingOptions.add(new Sort("author", true));
        sortingOptions.add(new Sort("price", false));
        sortingOptions.add(new Sort("price", true));
        return sortingOptions;
    }


    private void prepareProductsList(View view, String filterText) {
        final int LIST_RESOURCE = R.id.products_list;
        final int ITEM_LAYOUT = R.layout.item_product_list;
        RecyclerView productList = view.findViewById(LIST_RESOURCE);

        ProductsAdapter adapter = createProductsAdapter(ITEM_LAYOUT);
        productList.setAdapter(adapter);
        setListLayout(productList);

        cProductsList = productList;
        mAdapter = adapter;

        loadProducts(mCurrentCriteria); //load all products
    }

    @NonNull
    private ProductsAdapter createProductsAdapter(int ITEM_LAYOUT) {
        List<? extends GenericModel> products = new ArrayList<>();
        ProductsAdapter adapter = new ProductsAdapter(getActivity(), ITEM_LAYOUT, products, productId->startDetailsActivity(productId));
        addActions(adapter);
        addConditions(adapter);
        return adapter;
    }

    public  void loadProducts(Criteria criteria) {
        List<? extends GenericModel> products =  MyApplication.instance().getDataProvider().getProducts(criteria);
        mCart = ViewsCommon.getCart(products);
        mWishList = ViewsCommon.getWishList(products);
        mPromotions = ViewsCommon.getPromotions(products);
        mAdapter.setItems(products);
    }

    private void addActions(ProductsAdapter adapter) {
        adapter.addAction(R.id.buy_button, (objects, view) -> buyProduct(objects));
        adapter.addAction(R.id.favorites, (objects, view) -> setWishList(objects));
    }

    private void buyProduct(Object object){
        Long id = ((GenericModel)object).getId();
        createAddToCartDialog(id);
    }

    private void createAddToCartDialog(Long id) {
        new AddedToCartDialog(
                (dialog, which) -> {addToCart(id);goToCart();},
                (dialog, which) -> {addToCart(id); updateCart(id);})
                .makeDialog(getActivity());
    }

    private void addToCart(Long id) {
        MyApplication.instance().getDataProvider().addToCart(id);
        EventBus.getDefault().post(new UpdateCartSizeEvent());
    }

    private void updateCart(Long id){
        mCart.add(id);
        mAdapter.notifyDataSetChanged();
    }

    private void goToCart(){
        EventBus.getDefault().post(new NavigationEvent(NavigationEvent.View.CART));
    }

    private void setWishList(Object object) {
        Long id = ((GenericModel)object).getId();
        MyApplication.instance().getDataProvider().setFavorite(id, !mWishList.contains(id));
        if(mWishList.contains(id)){
            mWishList.remove(id);
        } else {
            mWishList.add(id);
        }
    }

    private void addConditions(ProductsAdapter adapter){
        adapter.addCondition(R.id.buy_button, (object, view) -> ViewsCommon.setAddToCartVisibility(object, view, false, mCart));
        adapter.addCondition(R.id.price, (object, view) -> {
            ViewsCommon.setAddToCartVisibility(object, view, false, mCart);
            ViewsCommon.setPriceText(object, view, mPromotions);
        });
        adapter.addCondition(R.id.bought, (object, view) -> ViewsCommon.setAddToCartVisibility(object, view, true, mCart));

        adapter.addCondition(R.id.favorites, (object, view) -> setWishList(object, view));
    }

    private void setWishList(Object object, View view){
        Long id = ((GenericModel)object).getId();
        boolean isInWishList = MyApplication.instance().getDataProvider().isFavorite(id);
        ((CheckBox)view).setChecked(isInWishList);
    }

    private void startDetailsActivity(Long productId){
        EventBus.getDefault().post(new StartDetailsEvent(productId));
    }

    private void setListLayout(RecyclerView productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        productList.setLayoutManager(layoutManager);
        productList.setItemAnimator(new DefaultItemAnimator());
    }

    public void changeListLayout(){
        RecyclerView.LayoutManager layoutManager;
        if (cProductsList.getLayoutManager() instanceof GridLayoutManager){
            layoutManager = new LinearLayoutManager(getActivity());
            mAdapter.setResource(R.layout.item_product_list);
        } else {
            int spanCount = 2;
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                spanCount = 3;
            }
            layoutManager = new GridLayoutManager(getActivity(), spanCount);
            mAdapter.setResource(R.layout.item_product_grid);

        }
        cProductsList.setLayoutManager(layoutManager);
        cProductsList.setAdapter(mAdapter);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void filter(Criteria filter){
        if(filter.getText() != null){
            List<? extends GenericModel> products = MyApplication.instance().getDataProvider().getProducts(filter);
            mAdapter.setItems(products);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration configuration){
        // TODO tutaj być może nie do końca o to chodzi
        super.onConfigurationChanged(configuration);
        updateLayoutView();
    }

    private void updateLayoutView(){
        RecyclerView.LayoutManager layoutManager;
        if (cProductsList.getLayoutManager() instanceof GridLayoutManager){
            int spanCount = 2;
            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                spanCount = 3;
            }
            layoutManager = new GridLayoutManager(getActivity(), spanCount);
            cProductsList.setLayoutManager(layoutManager);
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
