package majharul.soft.pkss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Admission extends AppCompatActivity {

    TextInputEditText etMemberId, etbalance, etbookSell, etElements, etShare;

    Button btnSave;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission);


        progressDialog = new ProgressDialog(this);


        etMemberId = findViewById(R.id.et_memberid);
        etbalance = findViewById(R.id.et_balance);
        etbookSell = findViewById(R.id.et_book_sell);
        etElements = findViewById(R.id.et_elements);
        etShare = findViewById(R.id.et_share);
        btnSave = findViewById(R.id.btn_submit);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                progressDialog.setTitle("loading...");

                String memberId = etMemberId.getText().toString();
                String book = etbookSell.getText().toString();
                String element = etElements.getText().toString();
                String balance = etbalance.getText().toString();
                String share = etShare.getText().toString();


                RetrofitClient.getInstance().getApi().vortiFee(memberId, book, element, balance, share).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        progressDialog.dismiss();

                        Toast.makeText(Admission.this, "success", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(),Dashboard.class));

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        progressDialog.dismiss();
                      //  Toast.makeText(Admission.this, "success", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });


    }
}