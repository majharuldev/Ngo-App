package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import majharul.soft.pkss.model.LoginHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class login extends AppCompatActivity {


    private TextView mtext, tvregister;

    private Button btnLogin;
    TextInputEditText etMobile, etPass;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mtext = findViewById(R.id.marqueeText);
        tvregister = findViewById(R.id.tvRegisterHere);
        etMobile = findViewById(R.id.et_mobile);
        etPass = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        progressDialog = new ProgressDialog(this);


        mtext.setSelected(true);


        tvregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String phone = etMobile.getText().toString();
                String password = etPass.getText().toString();


                if (TextUtils.isEmpty(phone)) {
                    etMobile.setError("মোবাইল নাম্বার পুরন করতে হবে");
                    etMobile.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    etPass.setError("পাসওয়ার্ড পুরন করতে হবে");
                    etPass.requestFocus();
                } else {
                    progressDialog.setTitle("অপেক্ষা করুন....");
                    progressDialog.show();


                    RetrofitClient.getInstance().getApi().loginUser(phone, password).enqueue(new Callback<LoginHelper>() {
                        @Override
                        public void onResponse(Call<LoginHelper> call, Response<LoginHelper> response) {
                            progressDialog.dismiss();

                            Log.d("api", "onResponse: " + response.body().getMessage());

                            LoginHelper obj = response.body();

                            String output = obj.getMessage();

                            if (output.equals("failed")) {

                                etMobile.setText("");
                                etPass.setText("");
                                Toast.makeText(login.this, "invalid password", Toast.LENGTH_SHORT).show();


                            }
                            if (output.equals("exit")) {
                                SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("phone", etMobile.getText().toString());
                                editor.putString("password", etPass.getText().toString());
                                editor.commit();
                                editor.apply();
                                finish();
                                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                            }


                        }

                        @Override
                        public void onFailure(Call<LoginHelper> call, Throwable t) {
                            progressDialog.dismiss();

                            Log.d(TAG, "onFailure: " + t.getLocalizedMessage());

                        }
                    });

                }
            }
        });

    }
}