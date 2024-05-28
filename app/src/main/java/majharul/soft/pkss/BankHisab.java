package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankHisab extends AppCompatActivity {

    TextInputEditText etAccNo, etAccName, etbankName, etTaka;
    Button btnSubmit;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_hisab);


        etAccName = findViewById(R.id.et_account_name);
        etAccNo = findViewById(R.id.et_accnumber);
        etbankName = findViewById(R.id.et_bank_name);
        etTaka = findViewById(R.id.et_balance);
        btnSubmit = findViewById(R.id.btn_submit);

        progressDialog = new ProgressDialog(this);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account_no = etAccNo.getText().toString();
                String account_name = etAccName.getText().toString();
                String bank_name = etbankName.getText().toString();
                String balance = etTaka.getText().toString();


                progressDialog.show();
                progressDialog.setTitle("loading...");

                RetrofitClient.getInstance().getApi().bankhisab(account_no, account_name, bank_name, balance).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.dismiss();
                        Log.d(TAG, "onResponse: " + response.body());

                        Toast.makeText(BankHisab.this, "success", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),Dashboard.class));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        progressDialog.dismiss();


                    }
                });


            }
        });


    }
}