package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import majharul.soft.pkss.adapter.MemberAdapter;
import majharul.soft.pkss.adapter.PremiyamAdayAdapter;
import majharul.soft.pkss.client.RetrofitClient;
import majharul.soft.pkss.model.Member;
import majharul.soft.pkss.model.MemberHelper;
import majharul.soft.pkss.model.PrimiyamAdayHelper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PremiyamAday extends AppCompatActivity {


    PremiyamAdayAdapter memberAdapter;
    FloatingActionButton fb;
    RecyclerView recyclerView;
    List<PrimiyamAdayHelper> listdata = new ArrayList<>();
    String tag = "res";
    SearchView searchView;
    TextView textView;
    ProgressDialog pd;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiyam_aday);


        searchView = findViewById(R.id.searchview);
        pd = new ProgressDialog(this);
        textView = findViewById(R.id.text_id);
        searchView.clearFocus();
        swipeRefreshLayout = findViewById(R.id.swiptlayout);
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
        String status = "accept";

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                RearrangeItems();
            }


        });
        Toast.makeText(this, userphone, Toast.LENGTH_SHORT).show();
        pd.show();
        pd.setTitle("loading...");


        RetrofitClient.getInstance().getApi().getmember(userphone).enqueue(new Callback<List<PrimiyamAdayHelper>>() {
            @Override
            public void onResponse(Call<List<PrimiyamAdayHelper>> call, Response<List<PrimiyamAdayHelper>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    pd.dismiss();

                    listdata = response.body();
                    memberAdapter = new PremiyamAdayAdapter(PremiyamAday.this, listdata);
                    Log.d("api", "onResponse: " + response.body());
                    recyclerView.setAdapter(memberAdapter);
                    //  recyclerView.setAdapter(memberAdapter);

                    int i = memberAdapter.getItemCount();
                    String str = Integer.toString(i);

                    Log.d("number", "onResponse: " + i);
                    textView.setText("মোট সদসঃ" + str);

                } else {

                    Log.d(TAG, "error: " + response.body());
                }

            }

            @Override
            public void onFailure(Call<List<PrimiyamAdayHelper>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                pd.dismiss();
            }
        });

    }

    private void RearrangeItems() {


        swipeRefreshLayout.setRefreshing(false);
    }


    private void filterList(String newText) {

        List<PrimiyamAdayHelper> filterlist = new ArrayList<>();

        for (PrimiyamAdayHelper item : listdata) {
            if (item.getName().toLowerCase().contains(newText.toLowerCase())) {

                filterlist.add(item);
            }

        }

        if (filterlist.isEmpty()) {

            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();

        } else {

            memberAdapter.setFilterList(filterlist);

        }

    }


}