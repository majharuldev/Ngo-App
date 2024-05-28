package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavingWithdrawGeneral extends AppCompatActivity {

    TextInputEditText etname, etToatal, etToday;
    MaterialButton btnsav;
    String savings;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_withdraw_general);

        etname = findViewById(R.id.et_name);
        etToatal = findViewById(R.id.et_account_joma);
        etToday = findViewById(R.id.et_balance_withdraw);
        btnsav = findViewById(R.id.btn_submit);


        Bundle bundle = getIntent().getExtras();

        id = bundle.getString("id");
        String name = bundle.getString("name");
        String team = bundle.getString("team");
        String father = bundle.getString("father");
        String phone = bundle.getString("mobile");
        String village = bundle.getString("village");
        savings = bundle.getString("savings");

        etname.setEnabled(false);
        etToatal.setEnabled(false);

        etname.setText(name);
        etToatal.setText(savings);

        String total = etToatal.getText().toString();


        btnsav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String today = etToday.getText().toString();
                int number = Integer.parseInt(today);
                int joma = Integer.parseInt(total);
                int to = joma - number;

                String savings = Integer.toString(to);

                Log.d(TAG, "onClick: " + to);


                RetrofitClient.getInstance().getApi().saving(id, savings).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {

                            Toast.makeText(SavingWithdrawGeneral.this, "success", Toast.LENGTH_SHORT).show();

                            etname.setText("");
                            etToatal.setText("");
                            etToday.setText("");

                            startActivity(new Intent(getApplicationContext(), Dashboard.class));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
    }
}
