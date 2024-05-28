package majharul.soft.pkss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class bibidhjoma extends AppCompatActivity {

    String[] items = {"জমা", "খরচ"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    String category;
    String userphone;


    TextInputEditText etamount, etremakrs;
    Button btnsave;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibidhjoma);


        progressDialog = new ProgressDialog(this);

        autoCompleteTxt = (AutoCompleteTextView) findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item, items);
        autoCompleteTxt.setAdapter(adapterItems);


        etamount = findViewById(R.id.et_balance);
        etremakrs = findViewById(R.id.et_remaks);
        btnsave = findViewById(R.id.btn_save);

        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();


                if (item == "জমা") {

                    category = item;


                } else if (item == "খরচ") {

                    category = item;


                }


            }
        });


        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        userphone = sp.getString("phone", null);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String amount = etamount.getText().toString();
                String remarks = etremakrs.getText().toString();


                progressDialog.show();
                progressDialog.setTitle("loading....");

                RetrofitClient.getInstance().getApi().primiyam(category, amount, remarks, userphone).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        progressDialog.dismiss();
                        Toast.makeText(bibidhjoma.this, "success", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(getApplicationContext(), Dashboard.class));

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.dismiss();
                        //Toast.makeText(bibidhjoma.this, "", Toast.LENGTH_SHORT).show();


                    }
                });


            }
        });


    }
}