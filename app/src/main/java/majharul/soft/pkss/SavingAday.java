package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavingAday extends AppCompatActivity {


    TextInputEditText etname, etToatal, etToday;
    MaterialButton btnsav;
    String savings;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_aday);

        etname = findViewById(R.id.et_name);
        etToatal = findViewById(R.id.et_total);
        etToday = findViewById(R.id.et_today);
        btnsav = findViewById(R.id.btn_save);

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

        Log.d(TAG, "onCreate: " + savings);


        String total = etToatal.getText().toString();


        btnsav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String today = etToday.getText().toString();
                int number = Integer.parseInt(today);
                int joma = Integer.parseInt(total);
                int to = number + joma;

                String savings = Integer.toString(to);


                RetrofitClient.getInstance().getApi().saving(id, savings).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()) {

                            Toast.makeText(SavingAday.this, "success", Toast.LENGTH_SHORT).show();

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