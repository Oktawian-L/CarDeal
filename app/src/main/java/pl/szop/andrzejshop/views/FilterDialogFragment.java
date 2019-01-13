package pl.szop.andrzejshop.views;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

import pl.szop.andrzejshop.MyApplication;
import pl.szop.andrzejshop.R;

import pl.szop.andrzejshop.adapters.GenericModelAdapter;
import pl.szop.andrzejshop.data.criteria.Criteria;
import pl.szop.andrzejshop.data.criteria.Filter;
import pl.szop.andrzejshop.data.criteria.FilterGroup;
import pl.szop.andrzejshop.models.GenericModel;

public class FilterDialogFragment extends DialogFragment {

    private final String CATEGORY = "category";
    private final String PUBLISHER = "publisher";
    private final String LANGUAGE = "language";
    private final String PRICE = "price";

    public interface FilterListener{
        void onOk(Criteria criteria);
    }

    private Spinner cCategorySpinner;
    private Spinner cPublisherSpinner;
    private Spinner cLanguageSpinner;
    private EditText cMinPrice;
    private EditText cMaxPrice;
    private Button cFilterButton;
    private Button cClearButton;

    private Criteria mCurrentCriteria;
    private FilterListener mListener;

    public void setCriteria(Criteria criteria){
        mCurrentCriteria = criteria;

        // TODO zamiast tego mozna po prostu ustawic odpowiednio okna
    }

    private void setValues(Criteria criteria) {
        setSpinner(CATEGORY, cCategorySpinner, criteria);
        setSpinner(PUBLISHER, cPublisherSpinner, criteria);
        setSpinner(LANGUAGE, cLanguageSpinner, criteria);

        FilterGroup priceFilters = criteria.getFilter(PRICE);
        if(priceFilters != null){
            List<Object> priceValues = priceFilters.getValues();
            cMinPrice.setText(String.valueOf(priceValues.get(0)));
            cMaxPrice.setText(String.valueOf(priceValues.get(1)));
        }
    }

    private void setSpinner(String name, Spinner spinner, Criteria criteria){
        FilterGroup filters = criteria.getFilter(name);
        if(filters != null){
            List<Object> values = filters.getValues();
            Long id = (Long) values.get(0);
            for (int i=0; i<spinner.getCount(); i++){
                GenericModel model = (GenericModel) spinner.getItemAtPosition(i);
                if(model != null && model.getId().equals(id)){
                    spinner.setSelection(i);
                }
            }
        } else {
            spinner.setSelection(0);
        }
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
        cPublisherSpinner = view.findViewById(R.id.publisher_spinner);
        cLanguageSpinner = view.findViewById(R.id.language_spinner);

        cMinPrice = view.findViewById(R.id.min_price);
        cMaxPrice = view.findViewById(R.id.max_price);
        cFilterButton = view.findViewById(R.id.filter_button);
        cClearButton = view.findViewById(R.id.clear_button);
        cFilterButton.setOnClickListener(v->filter());
        cClearButton.setOnClickListener(v->clear());

        fillSpinner(cCategorySpinner, MyApplication.instance().getDataProvider().getCategories());
        fillSpinner(cPublisherSpinner, MyApplication.instance().getDataProvider().getPublishers());
        fillSpinner(cLanguageSpinner, MyApplication.instance().getDataProvider().getLanguages());

        setValues(mCurrentCriteria);
    }

    private void fillSpinner(Spinner spinner, List<? extends GenericModel> items){
        items.add(0, null);
        GenericModelAdapter adapter = new GenericModelAdapter(items, getActivity(), R.layout.item_filter_category);
        spinner.setAdapter(adapter);
    }

    private void filter(){
        Criteria resultCriteria = getCriteria();
        if(mListener != null){
            mListener.onOk(resultCriteria);
        }
        dismiss();
    }

    private void clear(){
        mCurrentCriteria = new Criteria();
        setValues(mCurrentCriteria);
    }

    private Criteria getCriteria(){
        Criteria criteria = mCurrentCriteria;

        setSpinnerFilter(cCategorySpinner, CATEGORY, criteria);
        setSpinnerFilter(cPublisherSpinner, PUBLISHER, criteria);
        setSpinnerFilter(cLanguageSpinner, LANGUAGE, criteria);

        Integer minPrice = cMinPrice.getText().length()!=0 ? Integer.parseInt(cMinPrice.getText().toString()) : null;
        Integer maxPrice = cMinPrice.getText().length()!=0 ? Integer.parseInt(cMaxPrice.getText().toString()) : null;
        Filter minPriceFilter = new Filter(PRICE, minPrice, Filter.Option.GREATER);
        Filter maxPriceFilter = new Filter(PRICE, maxPrice, Filter.Option.LESS);
        criteria.setFilter(PRICE, FilterGroup.Type.AND, minPriceFilter, maxPriceFilter);

        return criteria;
    }

    private void setSpinnerFilter(Spinner spinner, String name, Criteria criteria){
        Long id = spinner.getSelectedItem() != null ? spinner.getSelectedItemId() : null;
        Filter filter = new Filter(name, id, Filter.Option.EQUAL);
        criteria.setFilter(filter);
    }
}
