package majharul.soft.pkss;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Invester extends AppCompatActivity {

    TextInputEditText etname, etvill, etfather, etlicense, etPhone;
    Button btnSave;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invester);

        etname = findViewById(R.id.et_name);
        etvill = findViewById(R.id.et_vill);
        etfather = findViewById(R.id.et_father_name);
        etlicense = findViewById(R.id.et_lisence);
        etPhone = findViewById(R.id.et_mobile);
        btnSave = findViewById(R.id.btn_save);
        progressDialog=new ProgressDialog(Invester.this);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etname.getText().toString();
                String village=etvill.getText().toString();
                String father=etfather.getText().toString();
                String license=etlicense.getText().toString();
                String phone=etPhone.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    etname.setError(" নাম পুরন করতে হবে");
                    etname.requestFocus();
                } else if (TextUtils.isEmpty(village)) {
                    etvill.setError("গ্রামের নাম পুরন করতে হবে");
                    etvill.requestFocus();
                } else if (TextUtils.isEmpty(father)) {
                    etfather.setError("পিতার নাম পুরন করতে হবে");
                    etfather.requestFocus();
                }  else if (TextUtils.isEmpty(phone)) {
                    etPhone.setError("মোবাইল নাম্বার  পুরন করতে হবে");
                    etPhone.requestFocus();
                }
                else if (TextUtils.isEmpty(license)) {
                    etlicense.setError("লাইসেঞ্চ নাম্বার  পুরন করতে হবে");
                    etlicense.requestFocus();
                }



                else {

                    progressDialog.setTitle("অপেক্ষা করুন....");
                    progressDialog.show();

                    RetrofitClient.getInstance().getApi().CreateInvest(name,village,father,license,phone).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            progressDialog.dismiss();
                            AlertDialog.Builder builder = new AlertDialog.Builder(Invester.this);
                            builder.setMessage("নিবন্ধন সফল হয়েছে ")
                                    .setTitle("")
                                    .setPositiveButton(android.R.string.ok, null);
                            AlertDialog dialog = builder.create();
                            dialog.show();

                            etfather.setText("");
                            etname.setText("");
                            etvill.setText("");
                            etPhone.setText("");
                            etlicense.setText("");
                            Log.d("api", "onResponse: "+response.body());
                            startActivity(new Intent(getApplicationContext(), MemberList.class));
                           // dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });




                }
            }
        });





    }
}