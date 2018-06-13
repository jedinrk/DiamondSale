package com.logiticks.diamondsale.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.logiticks.diamondsale.Activity.CreateInvoiceActivity;
import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.CustomerModelClass;
import com.logiticks.diamondsale.rest.model.DiamondModelClass;
import com.logiticks.diamondsale.rest.model.OrderModelClass;
import com.logiticks.diamondsale.rest.model.PlaceOrderModelClass;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;

/**
 * Created by Naveen on 09-06-2018.
 */

public class FragmentInvoices extends Fragment {

    private View mRootView;

    List<PlaceOrderModelClass> mInvoiceList;
    private RelativeLayout progressBar;
    List<CustomerModelClass> customerList;
    List<DiamondModelClass> diamondList;

    public FragmentInvoices(){

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FragmentInvoices newInstance() {
        FragmentInvoices fragment = new FragmentInvoices();
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_invoices, container, false);

        progressBar = mRootView.findViewById(R.id.progressBar);
        ListView invoiceList = mRootView.findViewById(R.id.invoiceList);

        invoiceList.setAdapter(mInvoiceListAdapter);

        return mRootView;
    }

    void loadInvoiceList(){
        progressBar.setVisibility(View.VISIBLE);

        Call<List<CustomerModelClass>> getCustomerList= getRestClient().getApiService().getCustomer();
        getCustomerList.enqueue(new Callback<List<CustomerModelClass>>() {
            @Override
            public void onResponse(Call<List<CustomerModelClass>> call, Response<List<CustomerModelClass>> response) {

                customerList = response.body();

            }

            @Override
            public void onFailure(Call<List<CustomerModelClass>> call, Throwable t) {

            }
        });

        Call<List<DiamondModelClass>> getDiamondList= getRestClient().getApiService().getDiamondList();
        getDiamondList.enqueue(new Callback<List<DiamondModelClass>>() {
            @Override
            public void onResponse(Call<List<DiamondModelClass>> call, Response<List<DiamondModelClass>> response) {
                diamondList = response.body();


            }

            @Override
            public void onFailure(Call<List<DiamondModelClass>> call, Throwable t) {

            }
        });


        Call<List<PlaceOrderModelClass>> getOrderList= getRestClient().getApiService().getInvoices();

        getOrderList.enqueue(new Callback<List<PlaceOrderModelClass>>() {
            @Override
            public void onResponse(Call<List<PlaceOrderModelClass>> call, Response<List<PlaceOrderModelClass>> response) {
                mInvoiceList = response.body();
                mInvoiceListAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<PlaceOrderModelClass>> call, Throwable t) {

            }
        });
    }

    private BaseAdapter mInvoiceListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mInvoiceList != null? mInvoiceList.size():0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View converView, ViewGroup parent) {
            View v = converView;
            if(v == null){
                Context ctx = parent.getContext();
                v = LayoutInflater.from(ctx).inflate(R.layout.invoice_list_item, null);


                TextView productId = v.findViewById(R.id.productId);
                productId.setText(mInvoiceList.get(i).getDiamond().substring(46));

                /*TextView carat = v.findViewById(R.id.carat);
                carat.setText(String.valueOf(mInvoiceList.get(i).getDiamond().getDiamondDetails()));

                TextView cut = v.findViewById(R.id.cut);
                cut.setText(mInvoiceList.get(i).getDiamond().getDiamondDetails().getCut());*/

                TextView customer = v.findViewById(R.id.customer);
                customer.setText(mInvoiceList.get(i).getBuyer().substring(58));


                TextView price = v.findViewById(R.id.price);
                price.setText(String.valueOf(mInvoiceList.get(i).getAmount()));

                /*TextView customer = v.findViewById(R.id.customer);
                customer.setText(mInvoiceList.get(i).);*/
            }

            return v;
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        loadInvoiceList();
    }
}
