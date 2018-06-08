package com.logiticks.diamondsale.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.CustomerModelClass;
import com.logiticks.diamondsale.rest.model.DiamondModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;

/**
 * Created by Naveen on 08-06-2018.
 */

public class FragmentCustomer extends Fragment {

    private View mRootView;

    List<CustomerModelClass> mCustomerList;
    private RelativeLayout progressBar;

    public FragmentCustomer() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FragmentCustomer newInstance() {
        FragmentCustomer fragment = new FragmentCustomer();
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_customer, container, false);

        progressBar = mRootView.findViewById(R.id.progressBar);
        ListView customerList = mRootView.findViewById(R.id.customerList);

        customerList.setAdapter(mCustomerListAdapter);

        loadCustomerList();

        return mRootView;
    }

    void loadCustomerList(){
        progressBar.setVisibility(View.VISIBLE);
        Call<List<CustomerModelClass>> getCustomerList= getRestClient().getApiService().getCustomer();

        getCustomerList.enqueue(new Callback<List<CustomerModelClass>>() {
            @Override
            public void onResponse(Call<List<CustomerModelClass>> call, Response<List<CustomerModelClass>> response) {
                mCustomerList = response.body();
                mCustomerListAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<CustomerModelClass>> call, Throwable t) {

            }
        });
    }

    private BaseAdapter mCustomerListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mCustomerList != null? mCustomerList.size():0;
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
                v = LayoutInflater.from(ctx).inflate(R.layout.customer_list_item, null);

                TextView customerId = v.findViewById(R.id.customerId);
                customerId.setText(String.valueOf(i));

                TextView customerName = v.findViewById(R.id.customerName);
                customerName.setText(mCustomerList.get(i).getName());

                TextView customerEmail = v.findViewById(R.id.customerEmail);
                customerEmail.setText(mCustomerList.get(i).getEmail());
            }

            return v;
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        loadCustomerList();
    }
}
