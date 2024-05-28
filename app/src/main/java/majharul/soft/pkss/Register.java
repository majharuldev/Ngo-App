package majharul.soft.pkss;

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
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {


    private TextInputEditText nameText, phoneText, passText;
    Button btnsave;


    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameText = findViewById(R.id.et_name);
        phoneText = findViewById(R.id.et_mobile);
        passText = findViewById(R.id.et_pass);
        btnsave = findViewById(R.id.btn_submit);

        progressDialog = new ProgressDialog(this);


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameText.getText().toString();
                String phone = phoneText.getText().toString();
                String password = passText.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    nameText.setError("নাম পুরন করতে হবে");
                    nameText.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    phoneText.setError("মোবাইল পুরন করতে হবে");
                    phoneText.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    passText.setError("পাসওয়ার্ড পুরন করতে হবে");
                    passText.requestFocus();
                } else {
                    progressDialog.setTitle("অপেক্ষা করুন....");
                    progressDialog.show();

                    RetrofitClient.getInstance().getApi().CreateUser(name, phone, password).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                            progressDialog.dismiss();


                            AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                            builder.setMessage("success");
                            builder.setCancelable(true);
                            SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name", nameText.getText().toString());
                            editor.commit();
                            editor.apply();
                            finish();
                          //  editor.putString("password", etPass.getText().toString());


                            AlertDialog dialog = builder.create();
                            dialog.show();
                            startActivity(new Intent(getApplicationContext(), login.class));
                            nameText.setText("");
                            phoneText.setText("");
                            passText.setText("");


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {


                            Log.d("api", "onFailure: " + t.getLocalizedMessage().toString());

                        }
                    });

                }
            }
        });


    }
}