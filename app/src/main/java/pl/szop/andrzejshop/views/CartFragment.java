package pl.szop.andrzejshop.views;

import android.content.Context;
import android.content.res.Configuration;
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
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.events.NavigationEvent;
import pl.szop.andrzejshop.events.StartDetailsEvent;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;
import pl.szop.andrzejshop.mapper.ProductsAdapter;
import pl.szop.andrzejshop.models.CartItem;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Promotions;
import pl.szop.andrzejshop.utils.NumberUtils;
import pl.szop.andrzejshop.utils.ViewsCommon;

public class CartFragment extends Fragment {

    private RecyclerView cCartList;
    private TextView cEmptyTextView;
    private ProductsAdapter mAdapter;
    private TextView cSummaryTextView;
    private Button cOrderButton;

    private OnFragmentInteractionListener mListener;

    private Map<Long, Promotions> mPromotions = new HashMap<>();

    public CartFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
    public void onResume(){
        super.onResume();
        refresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view){
        cCartList = view.findViewById(R.id.favorites_list);
        cSummaryTextView = view.findViewById(R.id.summary_price_id);
        cOrderButton = view.findViewById(R.id.order_button);
        cEmptyTextView = view.findViewById(R.id.empty_text);

        List<CartItem> cartItems = (List<CartItem>) MyApplication.instance().getDataProvider().getCartItems();
        mPromotions = ViewsCommon.getPromotions(cartItems);
        mAdapter = createAdapter(cartItems);
        cCartList.setAdapter(mAdapter);
    }


    private ProductsAdapter createAdapter(List<CartItem> items) {
        ProductsAdapter adapter = new ProductsAdapter(getActivity(), R.layout.item_cartitem, new ArrayList<>(), productId -> goToDetails(productId));
        adapter.addAction(R.id.remove_button, (objects, view) -> tryRemoveFromCart(objects));
        adapter.addAction(R.id.plus, (objects, view) -> increaseAmount(objects));
        adapter.addAction(R.id.minus, (objects, view) -> decreaseAmount(objects));

        adapter.addCondition(R.id.price, (object, view) -> ViewsCommon.setPriceText(object, view, mPromotions));
        setListLayout(cCartList);
        adapter.setItems(items);
        return adapter;
    }

    private void goToDetails(Long productId){
        EventBus.getDefault().post(new StartDetailsEvent(productId));
    }

    private void refresh() {
        float sum = 0f;
        // calculate value of the order
        for(int i = 0; i <mAdapter.getItemCount(); i++){
            CartItem item = (CartItem) mAdapter.getItem(i);
            if(mPromotions.containsKey(item.getId())){
                // promotional price
                sum += mPromotions.get(item.getId()).getValue() * item.getAmount();
            } else {
                // normal price
                sum += item.getBook().getPrice() * item.getAmount();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        cSummaryTextView.setText(decimalFormat.format(sum) + " zł");
        mAdapter.notifyDataSetChanged();

        if(mAdapter.getItemCount() == 0){
            cCartList.setVisibility(View.GONE);
            cEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            cCartList.setVisibility(View.VISIBLE);
            cEmptyTextView.setVisibility(View.GONE);
        }
    }

    private void tryRemoveFromCart(Object object){
        showRemoveQuestion(object);
    }

    private void removeFromCart(Object object){
        GenericModel product = (GenericModel) object;
        Long id = product.getId();
        MyApplication.instance().getDataProvider().removeFromCart(id);
        mAdapter.removeItem(product);
        refresh();
        EventBus.getDefault().post(new UpdateCartSizeEvent());
    }

    private void showRemoveQuestion(Object object){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getActivity());
        }
        builder.setTitle(getString(R.string.del_cart_title))
                .setMessage(getString(R.string.del_cart_message))
                .setPositiveButton(android.R.string.yes, (dialog, which) -> removeFromCart(object))
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {})
                .show();
    }

    private void increaseAmount(Object object){
        CartItem cartItem = (CartItem) object;
        cartItem.increase();
        refresh();
    }

    private void decreaseAmount(Object object){
        CartItem cartItem = (CartItem)object;
        cartItem.decrease();
        refresh();
    }

    private void setListLayout(RecyclerView productList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        productList.setLayoutManager(layoutManager);
        productList.setItemAnimator(new DefaultItemAnimator());
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

    @Override
    public void onConfigurationChanged(Configuration configuration){
        super.onConfigurationChanged(configuration);
        // TODO ugly hack
        EventBus.getDefault().post(new NavigationEvent(NavigationEvent.View.CART));
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
