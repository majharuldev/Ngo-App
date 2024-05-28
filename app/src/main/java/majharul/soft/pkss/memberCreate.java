package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Random;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class memberCreate extends AppCompatActivity {


    TextInputEditText etteam, etname, etMobile, etVillPresent, etFatherName,etNid,etPermnt,etmemberId,etsaving;
    private CheckBox cdmember, generalMember;
    Button btnSave;
    ProgressDialog progressDialog;

    String category;
    String id;
    String userphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_create);

        etteam = findViewById(R.id.et_team_name);
        etname = findViewById(R.id.et_name);
        etMobile = findViewById(R.id.et_mobile);
        etVillPresent = findViewById(R.id.et_vill_present);
        etFatherName = findViewById(R.id.et_father_name);
        etNid = findViewById(R.id.et_nid);
        etPermnt = findViewById(R.id.et_vill_parmanent);
        etmemberId = findViewById(R.id.et_sodso);
        etsaving = findViewById(R.id.et_savings);



        btnSave = findViewById(R.id.btn_submit);
        cdmember = findViewById(R.id.text_cd);
        generalMember = findViewById(R.id.text_general);
        progressDialog = new ProgressDialog(this);




//        StringBuilder result = new StringBuilder();
//        result.append("type:");

        cdmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generalMember.setChecked(false);
                category = "সিডি সদস্য";
                etmemberId.setVisibility(View.VISIBLE);
                final int min = 381500001;
                final int max = 381500080;
                final int random = new Random().nextInt((max - min) + 1) + min;
                String data=Integer.toString(random);
                etmemberId.setText(data);

            }
        });


        generalMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdmember.setChecked(false);
                category = "সাধারন সদস্য";
                etmemberId.setVisibility(View.VISIBLE);
                final int min = 1000;
                final int max = 1099;
                final int random = new Random().nextInt((max - min) + 1) + min;
                String data=Integer.toString(random);
                etmemberId.setText(data);

            }
        });
        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        userphone = sp.getString("phone", null);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String team = etteam.getText().toString();
                String name = etname.getText().toString();
                String village = etVillPresent.getText().toString();
                String father = etFatherName.getText().toString();
                String phone = etMobile.getText().toString();
                String nid=etNid.getText().toString();
                String paddress=etPermnt.getText().toString();
                String member=etmemberId.getText().toString();
                String savings=etsaving.getText().toString();






              //  Toast.makeText(memberCreate.this, userphone, Toast.LENGTH_SHORT).show();
                //Toast.makeText(memberCreate.this, category, Toast.LENGTH_SHORT).show();


                if (TextUtils.isEmpty(team)) {
                    etteam.setError("দলের নাম পুরন করতে হবে");
                    etteam.requestFocus();
                } else if (TextUtils.isEmpty(name)) {
                    etname.setError("নাম পুরন করতে হবে");
                    etname.requestFocus();
                } else if (TextUtils.isEmpty(village)) {
                    etVillPresent.setError("গ্রামের নাম পুরন করতে হবে");
                    etVillPresent.requestFocus();
                } else if (TextUtils.isEmpty(father)) {
                    etFatherName.setError("পিতার নাম পুরন করতে হবে");
                    etFatherName.requestFocus();
                } else if (TextUtils.isEmpty(phone)) {
                    etMobile.setError("মোবাইল নাম্বার  পুরন করতে হবে");
                    etMobile.requestFocus();
                } else if (!(cdmember.isChecked() || generalMember.isChecked())) {

                    Toast.makeText(memberCreate.this, "সদস্য ধরন বাছাই করুন", Toast.LENGTH_SHORT).show();


                } else {
                    progressDialog.setTitle("অপেক্ষা করুন....");
                    progressDialog.show();


                    RetrofitClient.getInstance().getApi().newMember(id, team,member, name, father, village, phone, category,userphone,paddress,nid,savings).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            progressDialog.dismiss();

                            Log.d("api", "onResponse: " + response.body());
                            //  startActivity(new Intent(getApplicationContext(), MemberList.class));

                            AlertDialog.Builder builder = new AlertDialog.Builder(memberCreate.this);
                            builder.setMessage("success");
                            builder.setCancelable(false);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                            etteam.setText("");
                            etname.setText("");
                            etVillPresent.setText("");
                            etFatherName.setText("");
                            etMobile.setText("");
                            etNid.setText("");
                            etPermnt.setText("");
                            etsaving.setText("");

                            startActivity(new Intent(getApplicationContext(),Dashboard.class));

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {


                            Log.d("fail", "onFailure: " + t.getLocalizedMessage().toString());

                        }
                    });


                }

            }
        });


    }
}