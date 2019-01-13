package pl.szop.andrzejshop.views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.events.NavigationEvent;
import pl.szop.andrzejshop.events.StartDetailsEvent;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;
import pl.szop.andrzejshop.mapper.ProductsAdapter;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.Favorites;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.utils.ViewsCommon;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WishListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WishListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishListFragment extends Fragment {

    private RecyclerView cFavoritesList;
    private ProductsAdapter mAdapter;
    private TextView cEmptyText;

    private OnFragmentInteractionListener mListener;

    private Map<Long, Promotions> mPromotions;
    private Set<Long> mCart;

    public WishListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishListFragment newInstance(String param1, String param2) {
        WishListFragment fragment = new WishListFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wish_list, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view){
        cFavoritesList = view.findViewById(R.id.favorites_list);
        cEmptyText = view.findViewById(R.id.empty_text);
        List<? extends GenericModel> favorites = MyApplication.instance().getDataProvider().getFavorites();
        mCart = ViewsCommon.getCart(favorites);
        if(favorites.isEmpty()){
            showEmptyText();
        } else {
            mAdapter = createAdapter();
            mAdapter.setItems(favorites);
            mPromotions = ViewsCommon.getPromotions(favorites);
            cFavoritesList.setAdapter(mAdapter);
        }
    }

    private ProductsAdapter createAdapter() {
        ProductsAdapter adapter = new ProductsAdapter(getContext(), R.layout.item_favorites, new ArrayList<>(), productId -> goToDetails(productId));
        adapter.addAction(R.id.remove_button, (objects, view) -> tryRemoveFromFavorites(objects));
        adapter.addAction(R.id.add_to_cart, (objects, view) -> addToCart(objects));

        adapter.addCondition(R.id.add_to_cart, (object, view) -> ViewsCommon.setAddToCartVisibility(object, view, false, mCart));
        adapter.addCondition(R.id.price, (object, view) -> {
            ViewsCommon.setAddToCartVisibility(object, view, false, mCart);
            ViewsCommon.setPriceText(object, view, mPromotions);
        });
        adapter.addCondition(R.id.in_cart_text, (object, view) -> ViewsCommon.setAddToCartVisibility(object, view, true, mCart));

        setListLayout(cFavoritesList);
        return adapter;
    }

    private void goToDetails(Long productId){
        EventBus.getDefault().post(new StartDetailsEvent(productId));
    }

    private void addToCart(Object object){
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

    private void tryRemoveFromFavorites(Object object) {
        showRemoveQuestion(object);
    }

    private void showRemoveQuestion(Object object){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(getString(R.string.del_fav_title))
                .setMessage(getString(R.string.del_fav_message))
                .setPositiveButton(android.R.string.yes, (dialog, which) -> removeFromFavorites(object))
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .show();
    }

    private void removeFromFavorites(Object object) {
        GenericModel product = (GenericModel) object;
        Long id = product.getId();
        MyApplication.instance().getDataProvider().setFavorite(id, false);
        mAdapter.removeItem(product);
        if(mAdapter.getItemCount()==0){
            showEmptyText();
        }
    }

    private void showEmptyText(){
        cEmptyText.setVisibility(View.VISIBLE);
        cFavoritesList.setVisibility(View.GONE);
    }

    private void setListLayout(RecyclerView productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        productList.setLayoutManager(layoutManager);
        productList.setItemAnimator(new DefaultItemAnimator());
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
