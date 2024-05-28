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

public class Cdabedonform extends AppCompatActivity {


    TextInputEditText etname, etteam, etvill, etphone, etdailyjoma;
    Button btnSave;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cdabedonform);

        pd = new ProgressDialog(this);
        etname = findViewById(R.id.et_name);
        etteam = findViewById(R.id.et_team);
        etvill = findViewById(R.id.et_vill);
        etphone = findViewById(R.id.et_mobile);
        etdailyjoma = findViewById(R.id.et_daily_amount);
        btnSave = findViewById(R.id.btn_submit);


        Bundle bundle = getIntent().getExtras();
        String member = bundle.getString("id");
        String name = bundle.getString("name");
        String team = bundle.getString("team");
        String father = bundle.getString("father");
        String phone = bundle.getString("mobile");
        String village = bundle.getString("village");
        String category = bundle.getString("category");
        String userphone = bundle.getString("userphon");
        String savings = bundle.getString("savings");


        etname.setText(name);
        etteam.setText(team);
        etvill.setText(village);
        etphone.setText(phone);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();
                pd.setTitle("অপেক্ষা করুন");
                String dailyJoma = etdailyjoma.getText().toString();
                String status = "pending";


                RetrofitClient.getInstance().getApi().add(name, team, village, father, phone, category, member, userphone, savings, status).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        pd.dismiss();


                        if (response.isSuccessful()) {

                            Toast.makeText(Cdabedonform.this, "Success", Toast.LENGTH_SHORT).show();

                        }
                        etname.setText("");
                        etteam.setText("");
                        etvill.setText("");
                        etphone.setText("");

                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
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