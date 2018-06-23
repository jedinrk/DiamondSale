package com.logiticks.diamondsale.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.CustomerModelClass;
import com.logiticks.diamondsale.rest.model.DiamondDetails;
import com.logiticks.diamondsale.rest.model.DiamondModelClass;
import com.logiticks.diamondsale.rest.model.LogEntry;
import com.logiticks.diamondsale.rest.model.MerchantModelClass;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;


/**
 * Created by Naveen on 06-06-2018.
 */

public class CreateDiamondActivity extends AppCompatActivity {

    EditText pid;
    Spinner spinCarat;
    Spinner spinnerClarity;
    Spinner spinnerColor;
    Spinner spinnerCut;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diamond);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final DiamondModelClass diamond = new DiamondModelClass();
        final DiamondDetails details = new DiamondDetails();

        pid = (EditText) findViewById(R.id.editTextProductId);

        spinCarat = (Spinner) findViewById(R.id.spinnerCarat);

        ArrayAdapter<String> caratAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{"0.25","0.50","1.00","1.25","1.50","1.75","2.00","2.50","3.00"});
        caratAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinCarat.setAdapter(caratAdapter);

        spinnerClarity = (Spinner) findViewById(R.id.spinnerClarity);

        ArrayAdapter<String> clarityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{"FL/1F","VVS1/VVS2","VS1/VS2","SI1/SI2","I1","I2-I3"});
        clarityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClarity.setAdapter(clarityAdapter);



        spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
        ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{"Colorless","Near Colorless","Faint Yellow","Very Light Yellow"});
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerColor.setAdapter(colorAdapter);

        spinnerCut = (Spinner) findViewById(R.id.spinnerCut);
        ArrayAdapter<String> cutAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, new String[]{"Emerald","Heart","Marquise","Oval","Pear","Princess","Round"});
        cutAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCut.setAdapter(cutAdapter);

        Call<List<DiamondModelClass>> getDiamondsList= getRestClient().getApiService().getDiamondList();

        getDiamondsList.enqueue(new Callback<List<DiamondModelClass>>() {
            @Override
            public void onResponse(Call<List<DiamondModelClass>> call, Response<List<DiamondModelClass>> response) {
                int diamondId = 0;
                for(DiamondModelClass d: response.body()){
                    if(Integer.parseInt(d.getProductID().substring(1))>diamondId){
                        diamondId=Integer.parseInt(d.getProductID().substring(1));
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DiamondModelClass>> call, Throwable t) {

            }
        });

        Button btn = findViewById(R.id.buttonSave);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diamond.setProductID(pid.getText().toString());
                diamond.set$class("com.logiticks.diamondsale.dda.Diamond");

                details.set$class("com.logiticks.diamondsale.dda.DiamodDetails");

                details.setCarat(Double.parseDouble(spinCarat.getItemAtPosition(spinCarat.getSelectedItemPosition()).toString()));

                String clarity = spinnerClarity.getItemAtPosition(spinnerClarity.getSelectedItemPosition()).toString();
                details.setClarity(clarity);

                String color = spinnerColor.getItemAtPosition(spinnerColor.getSelectedItemPosition()).toString();
                details.setColor(color);

                String cut = spinnerCut.getItemAtPosition(spinnerColor.getSelectedItemPosition()).toString();
                details.setCut(cut);
                diamond.setDiamondDetails(details);

                SharedPreferences pref = getSharedPreferences("LOGIN_STATUS", Context.MODE_PRIVATE);
                diamond.setMerchant(pref.getString("merchantId",null));

                diamond.setOwner(null);

                diamond.setLogEntries(null);

                Call<DiamondModelClass> createDiamond = getRestClient().getApiService().createDiamond(diamond);
                createDiamond.enqueue(new Callback<DiamondModelClass>() {
                    @Override
                    public void onResponse(Call<DiamondModelClass> call, Response<DiamondModelClass> response)
                    {
                        finish();
                    }

                    @Override
                    public void onFailure(Call<DiamondModelClass> call, Throwable t)
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
