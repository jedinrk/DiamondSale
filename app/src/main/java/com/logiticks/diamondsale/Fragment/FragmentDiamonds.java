package com.logiticks.diamondsale.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.DiamondModelClass;
import com.logiticks.diamondsale.rest.service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;


/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentDiamonds extends Fragment {

    private View mRootView;
    private ApiService mApiService;
    List<DiamondModelClass> mDiamondList;
    private RelativeLayout progressBar;

    public FragmentDiamonds() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static FragmentDiamonds newInstance() {
        FragmentDiamonds fragment = new FragmentDiamonds();
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_main, container, false);


        progressBar = mRootView.findViewById(R.id.progressBar);
        ListView diamondList = mRootView.findViewById(R.id.diamondList);

        diamondList.setAdapter(mDiamondListAdapter);

        loadDiamondList();


        return mRootView;
    }

    void loadDiamondList(){
        progressBar.setVisibility(View.VISIBLE);
        Call<List<DiamondModelClass>> getDiamondsList= getRestClient().getApiService().getDiamondList();

        getDiamondsList.enqueue(new Callback<List<DiamondModelClass>>() {
            @Override
            public void onResponse(Call<List<DiamondModelClass>> call, Response<List<DiamondModelClass>> response) {
                mDiamondList = response.body();
                mDiamondListAdapter.notifyDataSetChanged();

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<DiamondModelClass>> call, Throwable t) {

            }
        });
    }

    private BaseAdapter mDiamondListAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return mDiamondList != null? mDiamondList.size():0;
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
                v = LayoutInflater.from(ctx).inflate(R.layout.diamond_list_item, null);
                TextView productId = v.findViewById(R.id.productId);
                productId.setText(mDiamondList.get(i).getProductID());

                TextView carat = v.findViewById(R.id.carat);
                carat.setText(mDiamondList.get(i).getDiamondDetails().getCarat().toString());

                TextView color = v.findViewById(R.id.color);
                color.setText(mDiamondList.get(i).getDiamondDetails().getColor());

                TextView clarity = v.findViewById(R.id.clarity);
                clarity.setText(mDiamondList.get(i).getDiamondDetails().getClarity());

                TextView cut = v.findViewById(R.id.cut);
                cut.setText(mDiamondList.get(i).getDiamondDetails().getCut());

                TextView price = v.findViewById(R.id.price);
                price.setText("$40");
            }

            return v;
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        loadDiamondList();
    }
}
