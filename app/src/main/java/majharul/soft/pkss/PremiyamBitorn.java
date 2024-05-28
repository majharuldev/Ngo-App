package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import majharul.soft.pkss.client.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremiyamBitorn extends AppCompatActivity {
    TextInputEditText etteam, etname, etMobile, etVill, etFatherName, etAmount, et_memberId, etLoan, etkistinumber,
            etPrimiyamPoriman, etPrimyamno;
    Button btnSave;
    private CheckBox cdmember, generalMember;
    String category;
    String userphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiyam_bitorn);

        etteam = findViewById(R.id.et_team_name);
        etname = findViewById(R.id.et_name);
        etMobile = findViewById(R.id.et_mobile);
        etVill = findViewById(R.id.et_vill);
        etFatherName = findViewById(R.id.et_father_name);
        etAmount = findViewById(R.id.et_primiyam_ammount);
        et_memberId = findViewById(R.id.et_member_no);
        etLoan = findViewById(R.id.et_primiyam_rate);
        etkistinumber = findViewById(R.id.et_primiyam_number);
        etPrimiyamPoriman = findViewById(R.id.et_primiyam_number);
        etPrimyamno = findViewById(R.id.et_prinumber);


        cdmember = findViewById(R.id.text_cd);
        generalMember = findViewById(R.id.text_general);
        btnSave = findViewById(R.id.btn_submit);


        Bundle bundle = getIntent().getExtras();
        String member = bundle.getString("id");
        String name = bundle.getString("name");
        String team = bundle.getString("team");
        String father = bundle.getString("father");
        String phone = bundle.getString("mobile");
        String village = bundle.getString("village");


        etLoan.setEnabled(false);
        et_memberId.setEnabled(false);
        Date currentDate = new Date();
        etname.setText(name);
        etteam.setText(team);
        etMobile.setText(phone);
        etFatherName.setText(father);
        etVill.setText(village);
        et_memberId.setText(member);


        cdmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generalMember.setChecked(false);
                etPrimyamno.setVisibility(View.VISIBLE);
                etPrimyamno.setText("145");
                category = "দৈনিক";

                if (etAmount.length() == 0) {


                    Toast.makeText(PremiyamBitorn.this, "লোণের পরিমান দিন", Toast.LENGTH_SHORT).show();


                } else {


                    String loan = etAmount.getText().toString();

                    int amount = Integer.parseInt(loan);

                    int lo = amount + (15 * amount) / 100;
                    int Kistnum = lo / 145;
                    String kistinumber = Integer.toString(Kistnum);
                    String data = Integer.toString(lo);
                    etLoan.setVisibility(View.VISIBLE);
                    etLoan.setText(data);
                    etkistinumber.setVisibility(View.VISIBLE);
                    etkistinumber.setEnabled(false);
                    etkistinumber.setText(kistinumber);


                }
            }
        });


        generalMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cdmember.setChecked(false);
                category = "মাসিক";
                etPrimyamno.setVisibility(View.VISIBLE);
                etPrimyamno.setText("12");

                if (etAmount.length() == 0) {


                    Toast.makeText(PremiyamBitorn.this, "লোণের পরিমান দিন", Toast.LENGTH_SHORT).show();


                } else {


                    String loan = etAmount.getText().toString();

                    int amount = Integer.parseInt(loan);

                    int lo = amount + (15 * amount) / 100;

                    int Kistnum = lo / 12;
                    String kistinumber = Integer.toString(Kistnum);
                    String data = Integer.toString(lo);
                    etLoan.setVisibility(View.VISIBLE);
                    etLoan.setText(data);
                    etkistinumber.setEnabled(false);

                    etkistinumber.setVisibility(View.VISIBLE);
                    etkistinumber.setText(kistinumber);
                }
            }
        });



        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        userphone = sp.getString("phone", null);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {


                String amount = etAmount.getText().toString();
                LocalDate dateObj = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
                String date = dateObj.format(formatter);
                Log.d(TAG, "onClick: " + date);
                String munafa_lon = etLoan.getText().toString();
                String primiyam_poriman = etPrimiyamPoriman.getText().toString();
                String primyam_number = etPrimyamno.getText().toString();
                String status="pending";


                RetrofitClient.getInstance().getApi().create(team, name, father, village, phone, amount, date, member, category, munafa_lon, primiyam_poriman, primyam_number,userphone,status).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        Log.d(TAG, "onResponse: " + response.body());
                        AlertDialog.Builder builder = new AlertDialog.Builder(PremiyamBitorn.this);
                        builder.setMessage("success");
                        builder.setCancelable(true);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        // Toast.makeText(PremiyamBitorn.this, "success", Toast.LENGTH_SHORT).show();

                        etteam.setText("");
                        etname.setText("");
                        etMobile.setText("");
                        etVill.setText("");
                        etFatherName.setText("");
                        etAmount.setText("");
                        et_memberId.setText("");
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        Log.d(TAG, "onResponse: "+response.body());

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