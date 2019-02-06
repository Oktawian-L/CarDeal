package pl.szop.andrzejshop.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.data.criteria.Filter;
import pl.szop.andrzejshop.data.criteria.Sort;
import pl.szop.andrzejshop.events.UpdateCartSizeEvent;
import pl.szop.andrzejshop.mapper.ProductsAdapter;
import pl.szop.andrzejshop.models.Book;
import pl.szop.andrzejshop.models.GenericModel;
import pl.szop.andrzejshop.models.Promotions;


public class HomeFragment extends Fragment {

    private RecyclerView cPromotionList;
    private ProductsAdapter mPromotionAdapter;
    private RecyclerView cNewList;
    private ProductsAdapter mNewAdapter;
    private RecyclerView cCategoryList;
    private ProductsAdapter mCategoryAdapter;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initComponents(view);
        return view;
    }

    // TODO refactor
    private void initComponents(View view){
        cPromotionList = view.findViewById(R.id.promotion_list);
        cNewList = view.findViewById(R.id.new_list);
        cCategoryList = view.findViewById(R.id.category_list);

        setHorizontalLayout(cPromotionList);
        setHorizontalLayout(cNewList);
        setHorizontalLayout(cCategoryList);

        final int LIMIT = 5;

        Criteria promotionsCriteria = new Criteria();
        promotionsCriteria.setLimit(LIMIT);
        List<Promotions> promotions = MyApplication.instance().getDataProvider().getPromotions(promotionsCriteria);

        Criteria newCriteria = new Criteria();
        newCriteria.setSorting(new Sort("release_date", false));
        newCriteria.setLimit(LIMIT);
        List<Book> newBooks = (List<Book>) MyApplication.instance().getDataProvider().getProducts(newCriteria); // TODO sprawdzić, czy się nie wysypie

        Criteria categoryCriteria = new Criteria();
        categoryCriteria.setFilter(new Filter("category", 1L, Filter.Option.EQUAL));
        List<Book> categoryBooks = (List<Book>) MyApplication.instance().getDataProvider().getProducts(categoryCriteria);

        mPromotionAdapter = createAdapter(promotions, R.layout.item_promotion_main);
        mNewAdapter = createAdapter(newBooks, R.layout.item_book_main);
        mCategoryAdapter = createAdapter(categoryBooks, R.layout.item_book_main);

        cPromotionList.setAdapter(mPromotionAdapter);
        cNewList.setAdapter(mNewAdapter);
        cCategoryList.setAdapter(mCategoryAdapter);
    }

    private void setHorizontalLayout(RecyclerView list){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);
        list.setLayoutManager(layoutManager);
    }

    private ProductsAdapter createAdapter(List<? extends GenericModel> items, int itemResource){
        ProductsAdapter adapter = new ProductsAdapter(getActivity(), itemResource, new ArrayList<>(), productId -> goToDetails(productId)); // TODO dorobić akcję na kliknięcie
        adapter.addAction(R.id.menu_more, (objects,view)-> {
            PopupMenu menu = new PopupMenu(getActivity(), view);
            menu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                Long productId = ((GenericModel)objects).getId();
                switch (id){
                    case R.id.add_to_cart:
                        // TODO można wyświetlić pytanie
                        MyApplication.instance().getDataProvider().addToCart(productId);
                        EventBus.getDefault().post(new UpdateCartSizeEvent());
                        Toast.makeText(getActivity(), "Dodano do koszyka", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.add_to_wish_list:
                        MyApplication.instance().getDataProvider().setFavorite(productId, true);
                        Toast.makeText(getActivity(), "Dodano do listy życzeń", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            });
            menu.inflate(R.menu.home_context_menu);
            menu.show();
        });
        adapter.setItems(items);
        return adapter;
    }

    private void goToDetails(Long productId){
        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra("id", productId);
        startActivity(intent);
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
