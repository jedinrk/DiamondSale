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
import android.widget.TextView;

import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.TransactionModelClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;

/**
 * Created by Naveen on 13-06-2018.
 */

public class FragmentTransactions extends Fragment {

    View mRootView;
    List<TransactionModelClass> transList;

    public FragmentTransactions(){

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FragmentTransactions newInstance() {
        FragmentTransactions fragment = new FragmentTransactions();
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_transaction, container, false);
        getActivity().setTitle("Transactions");

        ListView transactionList = mRootView.findViewById(R.id.transactionList);
        transactionList.setAdapter(mTransListAdapter);

        Call<List<TransactionModelClass>> getTransactionList= getRestClient().getApiService().getTransactions();
        getTransactionList.enqueue(new Callback<List<TransactionModelClass>>() {
            @Override
            public void onResponse(Call<List<TransactionModelClass>> call, Response<List<TransactionModelClass>> response) {

                transList = response.body();
                mTransListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<TransactionModelClass>> call, Throwable t) {

            }
        });


        return mRootView;
    }

    private BaseAdapter mTransListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return transList != null? transList.size():0;
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
                v = LayoutInflater.from(ctx).inflate(R.layout.trans_list_item, null);

                TextView date = v.findViewById(R.id.date);
                date.setText(transList.get(i).getTransactionTimestamp());

                TextView entryType = v.findViewById(R.id.entryType);
                entryType.setText(transList.get(i).getTransactionType());

                TextView participant = v.findViewById(R.id.participant);
                participant.setText(transList.get(i).getParticipantInvoking());


            }

            return v;
        }
    };
}
