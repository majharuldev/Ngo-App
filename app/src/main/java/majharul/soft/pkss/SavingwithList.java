package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import majharul.soft.pkss.adapter.SavingAdayAdapter;
import majharul.soft.pkss.adapter.SavingwithAdapter;
import majharul.soft.pkss.client.RetrofitClient;
import majharul.soft.pkss.model.MemberHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SavingwithList extends AppCompatActivity {

    SavingwithAdapter savingwithAdapter;

    RecyclerView recyclerView;
    List<MemberHelper> listdata = new ArrayList<>();
    String tag = "res";
    SearchView searchView;
    TextView textView;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savingwith_list);
        progressDialog = new ProgressDialog(SavingwithList.this);
        searchView = findViewById(R.id.searchview);
        textView = findViewById(R.id.text_id);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);

                return true;
            }
        });
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String userphone = sp.getString("phone", null);


        progressDialog.show();
        progressDialog.setTitle("loading");


        RetrofitClient.getInstance().getApi().getMember(userphone).enqueue(new Callback<List<MemberHelper>>() {
            @Override
            public void onResponse(Call<List<MemberHelper>> call, Response<List<MemberHelper>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressDialog.dismiss();
                    listdata = response.body();
                    savingwithAdapter = new SavingwithAdapter(SavingwithList.this, listdata);
                    Log.d("api", "onResponse: " + response.body());
                    recyclerView.setAdapter(savingwithAdapter);
                    // recyclerView.setAdapter(memberAdapter);

                    int i = savingwithAdapter.getItemCount();
                    String str = Integer.toString(i);

                    Log.d("number", "onResponse: " + i);
                    textView.setText("মোট সদসঃ" + str);
                    // Toast.makeText(MemberList.this, , Toast.LENGTH_SHORT).show();
                    // textView.setText(""+i);


                } else {

                    Log.d(TAG, "onResponse: " + response.body());

                }


            }

            @Override
            public void onFailure(Call<List<MemberHelper>> call, Throwable t) {
                progressDialog.dismiss();
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });


    }


    private void filterList(String newText) {

        List<MemberHelper> filterlist = new ArrayList<>();

        for (MemberHelper item : listdata) {
            if (item.getName().toLowerCase().contains(newText.toLowerCase())) {

                filterlist.add(item);
            }


        }

        if (filterlist.isEmpty()) {

            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();

        } else {

            savingwithAdapter.setFilterList(filterlist);


        }


    }

}
