package pl.szop.andrzejshop.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import pl.szop.andrzejshop.adapters.SortingAdapter;
import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.data.criteria.Sort;
import pl.szop.andrzejshop.events.NavigationEvent;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;
import pl.szop.andrzejshop.mapper.ProductsAdapter;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Promotions;


public class ProductsListFragment extends Fragment {

    private Button cFilterButton;
    private Button cSortButton;
    private ImageView cChangeViewButton;

    private RecyclerView cProductsList;
    private OnFragmentInteractionListener mListener;
    private ProductsAdapter mAdapter;

    private Criteria mCurrentCriteria = new Criteria();

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
        mAdapter.setItems(products);
    }

    private void addActions(ProductsAdapter adapter) {
        adapter.addAction(R.id.buy_button, (objects, view) -> buyProduct(objects));
        adapter.addAction(R.id.favorites, (objects, view) -> addToFavorites(objects));
    }

    private void buyProduct(Object object){
        Long id = ((GenericModel)object).getId();
        // TODO można zbudować własną klasę
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        MyApplication.instance().getDataProvider().addToCart(id);
        mAdapter.notifyDataSetChanged();
        // TODO ogarnąć w jaki sposób powinno komunikować się z aktywnością\
        // TODO można to zrobić za pomocą EventBusa

        EventBus.getDefault().post(new UpdateCartSizeEvent());
        // TODO dodać to do zasobów
        builder.setTitle("Dodano")
                .setMessage("Dodano produkt do koszyka")
                .setNegativeButton("Kontynuuj zakupy", (dialog, which) -> dialog.dismiss() )
                .setPositiveButton("Przejdź do koszyka", (dialog, which) -> {
                    goToCart();
                    dialog.dismiss();
                });
        builder.create().show();
    }

    private void goToCart(){
        EventBus.getDefault().post(new NavigationEvent(NavigationEvent.View.CART));
    }

    private void addToFavorites(Object object) {
        Long id = ((GenericModel)object).getId();
        // TODO spróbować to zrobić bez pobierania
        boolean favorites = MyApplication.instance().getDataProvider().isFavorite(id);
        MyApplication.instance().getDataProvider().setFavorite(id, !favorites);
    }

    private void addConditions(ProductsAdapter adapter){
        adapter.addCondition(R.id.buy_button, (object, view) -> setCartVisibility(object,view, false));
        adapter.addCondition(R.id.price, (object, view) -> {
            setCartVisibility(object, view, false);
            setPriceText(object, view);
        });
        adapter.addCondition(R.id.bought, (object, view) -> setCartVisibility(object, view, true));

        adapter.addCondition(R.id.favorites, (object, view) -> setWishList(object, view));
    }

    private void setPriceText(Object object, View view){
        Long id = ((GenericModel)object).getId();
        Promotions promotions = MyApplication.instance().getDataProvider().getPromotion(id);
        if(promotions != null && view.getVisibility() == View.VISIBLE) {
            ((TextView)view).setTextColor(Color.GREEN);
            DecimalFormat decimalFormat = new DecimalFormat("#.00");
            ((TextView)view).setText(decimalFormat.format(promotions.getValue()) + " zł");
        }
    }

    private void setWishList(Object object, View view){
        Long id = ((GenericModel)object).getId();
        boolean isInWishList = MyApplication.instance().getDataProvider().isFavorite(id);
        ((CheckBox)view).setChecked(isInWishList);
    }

    private void setCartVisibility(Object object, View view, boolean showWhenInCart){
        int visibility;
        if(isInCart(object)){
            visibility = showWhenInCart ? View.VISIBLE : View.INVISIBLE;
        } else {
            visibility = showWhenInCart ? View.INVISIBLE : View.VISIBLE;
        }
        view.setVisibility(visibility);
    }

    private boolean isInCart(Object object){
        // TODO można spróbować jakoś zapobiec wielokrotnemu pobieraniu informacji z bazy
        GenericModel model = (GenericModel)object;
        return MyApplication.instance().getDataProvider().isInCart(model);
    }

    private void startDetailsActivity(Long productId){
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra("id", productId);
        startActivity(intent);
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
            layoutManager = new GridLayoutManager(getActivity(), 2);
            mAdapter.setResource(R.layout.item_product_grid);

        }
        cProductsList.setLayoutManager(layoutManager);
        cProductsList.setAdapter(mAdapter);
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
