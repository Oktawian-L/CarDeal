package pl.szop.andrzejshop.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;
import pl.szop.andrzejshop.adapters.AuthorsAdapter;
import pl.szop.andrzejshop.adapters.CategoryAdapter;
import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.data.criteria.Filter;
import pl.szop.andrzejshop.data.criteria.FilterGroup;
import pl.szop.andrzejshop.models.Author;
import pl.szop.andrzejshop.models.Category;

public class FilterDialogFragment extends DialogFragment {

    public interface FilterListener{
        void onOk(Criteria criteria);
    }

    private Spinner cCategorySpinner;
    private AutoCompleteTextView cAuthorTextView;
    private EditText cMinPrice;
    private EditText cMaxPrice;
    private Button cFilterButton;

    private Criteria mCurrentCriteria;
    private FilterListener mListener;

    public void setCriteria(Criteria criteria){
        mCurrentCriteria = criteria;
        // TODO zamiast tego mozna po prostu ustawic odpowiednio okna
    }

    public void setListener(FilterListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);
        initComponents(view);
        return view;
    }

    private void initComponents(View view){
        cCategorySpinner = view.findViewById(R.id.category_spinner);

        cMinPrice = view.findViewById(R.id.min_price);
        cMaxPrice = view.findViewById(R.id.max_price);
        cFilterButton = view.findViewById(R.id.filter_button);
        cFilterButton.setOnClickListener(v->filter());

        fillCategories();
//        fillAuthors();
    }

    private void fillCategories(){
        List<Category> categories = MyApplication.instance().getDataProvider().getCategories();
        categories.set(0, null);
        CategoryAdapter adapter = new CategoryAdapter(categories, getActivity(), R.layout.item_filter_category);
        cCategorySpinner.setAdapter(adapter);
    }

    private void fillAuthors(){
        List<Author> authors = MyApplication.instance().getDataProvider().getAuthors();
        AuthorsAdapter adapter = new AuthorsAdapter(authors, getActivity(), R.layout.item_filter_category);
        cAuthorTextView.setAdapter(adapter);
    }

    private void filter(){
        Criteria resultCriteria = getCriteria();
        if(mListener != null){
            mListener.onOk(resultCriteria);
        }
        dismiss();
    }

    private Criteria getCriteria(){
        Criteria criteria = mCurrentCriteria;
        // TODO wprowadzić stałe
        Long categoryId = cCategorySpinner.getSelectedItem() != null ? ((Category)cCategorySpinner.getSelectedItem()).getId() : null;
        Filter categoryFilter = new Filter("category", categoryId, Filter.Option.EQUAL);
        criteria.setFilter("category", categoryFilter);
        Integer minPrice = cMinPrice.getText().length()!=0 ? Integer.parseInt(cMinPrice.getText().toString()) : null;
        Integer maxPrice = cMinPrice.getText().length()!=0 ? Integer.parseInt(cMaxPrice.getText().toString()) : null;
        Filter minPriceFilter = new Filter("price", minPrice, Filter.Option.GREATER);
        Filter maxPriceFilter = new Filter("price", maxPrice, Filter.Option.LESS);
        criteria.setFilter("price",FilterGroup.Type.AND, minPriceFilter, maxPriceFilter);

        return criteria;
    }
}
