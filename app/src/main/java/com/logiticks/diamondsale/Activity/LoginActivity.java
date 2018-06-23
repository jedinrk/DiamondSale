package com.logiticks.diamondsale.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.logiticks.diamondsale.BCrypt;
import com.logiticks.diamondsale.R;
import com.logiticks.diamondsale.rest.model.MerchantModelClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.logiticks.diamondsale.DiamondApp.getRestClient;

public class LoginActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    View progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        sharedPref = getSharedPreferences("LOGIN_STATUS", Context.MODE_PRIVATE);

        if(sharedPref.getBoolean("status", false)){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }

        progressBar = (View) findViewById(R.id.progressBar);


        final TextView txtViewError = (TextView) findViewById(R.id.textViewLoginError);
        txtViewError.setText("");

        final EditText merchantId = (EditText) findViewById(R.id.merchantId);
        final EditText password = (EditText) findViewById(R.id.password);


        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtViewError.setText("");
                showProgress(true);

                Call<MerchantModelClass> getMerchant =getRestClient().getApiService().getMerchantById(merchantId.getText().toString());
                getMerchant.enqueue(new Callback<MerchantModelClass>() {
                    @Override
                    public void onResponse(Call<MerchantModelClass> call, Response<MerchantModelClass> response) {
                        if(response.code()==200) {
                            MerchantModelClass merchant = response.body();
                            if (BCrypt.checkpw(password.getText().toString(), merchant.getPassword())) {
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putBoolean("status", true);
                                editor.putString("merchantId",merchant.getCompanyId());
                                editor.commit();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                showProgress(false);
                            } else {
                                txtViewError.setText("Invalid Credentials");
                                password.setText("");
                                password.requestFocus();
                                showProgress(false);

                            }
                        }else {
                            txtViewError.setText("Invalid User Id");
                            password.setText("");
                            merchantId.setText("");
                            merchantId.requestFocus();
                        }
                    }

                    @Override
                    public void onFailure(Call<MerchantModelClass> call, Throwable t) {
                        showProgress(false);
                        txtViewError.setText("Invalid User Id");
                        password.setText("");
                        merchantId.setText("");
                        merchantId.requestFocus();
                    }
                });
            }
        });
    }

    private void showProgress(boolean visible){
        if(visible) {
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
