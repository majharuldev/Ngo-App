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

public class PremiyamInput extends AppCompatActivity {

    TextInputEditText etName, etLoan, etPrimiyam,etPrimiyamNumber;
    Button btnSave;

    String id;
    int pnum=0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiyam_input);


        etName = findViewById(R.id.et_name);
        etLoan = findViewById(R.id.et_loan);
        etPrimiyam = findViewById(R.id.et_primiyam);
        etPrimiyamNumber = findViewById(R.id.et_primiyamNumber);
        btnSave = findViewById(R.id.btn_save);
        progressDialog = new ProgressDialog(this);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getString("id");
        String name = bundle.getString("name");
        String munafa_loan = bundle.getString("munafa_loan");
        String primyam_poriman = bundle.getString("primyam_poriman");
        String primyamNumber = bundle.getString("primyamNumber");

        etName.setText(name);
        etLoan.setText(munafa_loan);
        etPrimiyam.setText(primyam_poriman);
        etPrimiyamNumber.setText(primyamNumber);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                progressDialog.setTitle("loading...");
                String loan = etLoan.getText().toString();
                String today = etPrimiyam.getText().toString();

                int loans = Integer.parseInt(loan);
                int todayp = Integer.parseInt(today);

                int due = loans - todayp;
                String munafa_lon = Integer.toString(due);
                pnum=Integer.parseInt(primyamNumber);
                pnum=pnum-1;
                String primyam_number=Integer.toString(pnum);



                RetrofitClient.getInstance().getApi().primiyam(id, munafa_lon,primyam_number).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {

                            progressDialog.dismiss();
                            Toast.makeText(PremiyamInput.this, "success", Toast.LENGTH_SHORT).show();

                            Log.d(TAG, "onResponse: " + response.body().toString());
                            etLoan.setText("");
                            etName.setText("");
                            etPrimiyam.setText("");
                            startActivity(new Intent(getApplicationContext(), PremiyamAday.class));

                        }


                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d(TAG, "onFailure: " + t.getMessage());
                    }
                });


            }
        });


    }
}