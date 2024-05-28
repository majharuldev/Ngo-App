package majharul.soft.pkss;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import majharul.soft.pkss.adapter.InvesterAdapter;
import majharul.soft.pkss.adapter.MemberAdapter;
import majharul.soft.pkss.client.RetrofitClient;
import majharul.soft.pkss.model.InvesterHelp;
import majharul.soft.pkss.model.MemberHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvesterList extends AppCompatActivity {


    InvesterAdapter Adapter;
    FloatingActionButton fb;
    RecyclerView recyclerView;
    List<InvesterHelp> list = new ArrayList<>();
    String tag = "res";
    SearchView searchView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invester_list);

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


        RetrofitClient.getInstance().getApi().getInvester().enqueue(new Callback<List<InvesterHelp>>() {
            @Override
            public void onResponse(Call<List<InvesterHelp>> call, Response<List<InvesterHelp>> response) {


                if (response.isSuccessful() && response.body() != null) {

                    list = response.body();
                    Adapter = new InvesterAdapter(InvesterList.this, list);
                    Log.d("api", "onResponse: " + response.body());
                    recyclerView.setAdapter(Adapter);

                    int i = Adapter.getItemCount();
                    String str = Integer.toString(i);

                    Log.d("number", "onResponse: " + i);
                    textView.setText("মোট বিনিয়োগকারি :" + str);


                } else {

                    Log.d(TAG, "onResponse: " + response.body());

                }


            }

            @Override
            public void onFailure(Call<List<InvesterHelp>> call, Throwable t) {

            }
        });


    }

    private void filterList(String newText) {

        List<InvesterHelp> filterlist = new ArrayList<>();

        for (InvesterHelp item : list) {
            if (item.getName().toLowerCase().contains(newText.toLowerCase())) {

                filterlist.add(item);
            }


        }

        if (filterlist.isEmpty()) {

            Toast.makeText(this, "not found", Toast.LENGTH_SHORT).show();

        } else {

            Adapter.setFilterList(filterlist);


        }


    }
}