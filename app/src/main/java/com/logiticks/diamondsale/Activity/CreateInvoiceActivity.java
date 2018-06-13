package com.logiticks.diamondsale.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.CustomerModelClass;
import com.logiticks.diamondsale.rest.model.DiamondModelClass;
import com.logiticks.diamondsale.rest.model.MerchantModelClass;
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

public class CreateInvoiceActivity extends AppCompatActivity {

    PlaceOrderModelClass invoice;
    DiamondModelClass diamond;
    CustomerModelClass customer;
    MerchantModelClass merchant;

    TextView date;
    Spinner spinnerCustomer;
    Spinner spinnerProduct;

    boolean customerLoaded = false;
    boolean productLoaded = false;

    EditText price;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_invoice);

        price = (EditText) findViewById(R.id.editTextAmount);

        spinnerCustomer = (Spinner) findViewById(R.id.spinnerCustomer);

        Call<List<CustomerModelClass>> getCustomerList= getRestClient().getApiService().getCustomer();
        getCustomerList.enqueue(new Callback<List<CustomerModelClass>>() {
            @Override
            public void onResponse(Call<List<CustomerModelClass>> call, Response<List<CustomerModelClass>> response) {

                final List<CustomerModelClass> customerList=response.body();
                final List<String> customers = new ArrayList<>();
                for(CustomerModelClass c: customerList){
                    customers.add(c.getName());
                }

                ArrayAdapter<String> custAdapter = new ArrayAdapter<String>(CreateInvoiceActivity.this,
                        android.R.layout.simple_spinner_item,customers);
                custAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCustomer.setAdapter(custAdapter);

                spinnerCustomer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        customer = customerList.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                customerLoaded = true;
            }

            @Override
            public void onFailure(Call<List<CustomerModelClass>> call, Throwable t) {

            }
        });

        Call<List<DiamondModelClass>> getDiamondList= getRestClient().getApiService().getDiamondList();
        getDiamondList.enqueue(new Callback<List<DiamondModelClass>>() {
            @Override
            public void onResponse(Call<List<DiamondModelClass>> call, Response<List<DiamondModelClass>> response) {
                final List<DiamondModelClass> diamondList=response.body();

                final List<String> diamonds = new ArrayList<>();
                for(DiamondModelClass c: diamondList){
                    diamonds.add(c.getProductID());
                }

                ArrayAdapter<String> diamondAdapter = new ArrayAdapter<String>(CreateInvoiceActivity.this,
                        android.R.layout.simple_spinner_item,diamonds);
                diamondAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProduct.setAdapter(diamondAdapter);

                spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        diamond = diamondList.get(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

                productLoaded = true;
            }

            @Override
            public void onFailure(Call<List<DiamondModelClass>> call, Throwable t) {

            }
        });

        Call<List<MerchantModelClass>> getMerchantList = getRestClient().getApiService().getMerchants();
        getMerchantList.enqueue(new Callback<List<MerchantModelClass>>() {
            @Override
            public void onResponse(Call<List<MerchantModelClass>> call, Response<List<MerchantModelClass>> response) {
                merchant = response.body().get(0);
            }

            @Override
            public void onFailure(Call<List<MerchantModelClass>> call, Throwable t) {

            }
        });


        spinnerProduct = (Spinner) findViewById(R.id.spinnerProduct);

        invoice = new PlaceOrderModelClass();
        diamond = new DiamondModelClass();

        Button btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnSubmit = (Button) findViewById(R.id.buttonSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //invoice.set$class("com.logiticks.diamond.lifecycle.merchant.PlaceOrder");
                invoice.setOrderId(String.valueOf((int)(Math.random()*((1001)))));
                invoice.setAmount(Double.parseDouble(price.getText().toString()));
                invoice.setBuyer(customer);
                invoice.setDiamond(diamond);
                invoice.setMerchant(merchant);
                //invoice.setOrderStatus("PLACED");

                Call<PlaceOrderModelClass> createInvoice = getRestClient().getApiService().createInvoice(invoice);
                createInvoice.enqueue(new Callback<PlaceOrderModelClass>() {
                    @Override
                    public void onResponse(Call<PlaceOrderModelClass> call, Response<PlaceOrderModelClass> response)
                    {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PlaceOrderModelClass> call, Throwable t)
                    {
                        Log.v("onFailure", t.toString());
                    }
                });

            }
        });

    }
}
