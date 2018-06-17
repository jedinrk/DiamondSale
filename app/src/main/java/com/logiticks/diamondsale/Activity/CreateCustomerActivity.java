package com.logiticks.diamondsale.Activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.CustomerModelClass;

import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;

/**
 * Created by Naveen on 08-06-2018.
 */

public class CreateCustomerActivity extends AppCompatActivity {

    EditText customerName,customerEmail,customerPhone,customerAddress;

    CustomerModelClass customer;

    int custId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_customer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        customerName = (EditText) findViewById(R.id.editTextCustomerName);
        customerEmail = (EditText) findViewById(R.id.editTextCustomerEmail);
        customerPhone = (EditText) findViewById(R.id.editTextCustomerPhone);
        customerAddress = (EditText) findViewById(R.id.editTextCustomerAddress);

        Call<List<CustomerModelClass>> getCustomerList= getRestClient().getApiService().getCustomer();

        getCustomerList.enqueue(new Callback<List<CustomerModelClass>>() {
            @Override
            public void onResponse(Call<List<CustomerModelClass>> call, Response<List<CustomerModelClass>> response) {
                custId =0;
                for(CustomerModelClass c : response.body()){
                    if(Integer.parseInt(c.getCustomerId().substring(1))>custId){
                        custId = Integer.parseInt(c.getCustomerId().substring(1));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CustomerModelClass>> call, Throwable t) {

            }
        });

        customer = new CustomerModelClass();

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer.set$class("com.logiticks.diamond.lifecycle.merchant.Customer");
                customer.setCustomerId("c"+ String.valueOf(custId+1));
                customer.setName(customerName.getText().toString());
                customer.setEmail(customerEmail.getText().toString());
                customer.setPhone(customerPhone.getText().toString());
                customer.setState("Kerala");
                customer.setAddress(customerAddress.getText().toString());

                Call<CustomerModelClass> createDiamond = getRestClient().getApiService().createCustomer(customer);
                createDiamond.enqueue(new Callback<CustomerModelClass>() {
                    @Override
                    public void onResponse(Call<CustomerModelClass> call, Response<CustomerModelClass> response)
                    {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<CustomerModelClass> call, Throwable t)
                    {
                        Log.v("onFailure", t.toString());
                    }
                });

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
