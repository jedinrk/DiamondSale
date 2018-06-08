package com.logiticks.diamondsale;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.logiticks.diamondsale.rest.model.CustomerModelClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;

/**
 * Created by Naveen on 08-06-2018.
 */

public class CreateCustomerActivity extends AppCompatActivity {

    EditText customerName,customerEmail;

    CustomerModelClass customer;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_customer);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        customerName = (EditText) findViewById(R.id.editTextCustomerName);
        customerEmail = (EditText) findViewById(R.id.editTextCustomerEmail);

        customer = new CustomerModelClass();

        Button buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer.set$class("com.logiticks.diamond.lifecycle.Customer");
                customer.setName(customerName.getText().toString());
                customer.setEmail(customerEmail.getText().toString());

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
}
