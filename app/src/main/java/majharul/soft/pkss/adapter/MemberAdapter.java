package majharul.soft.pkss.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import majharul.soft.pkss.MemberList;
import majharul.soft.pkss.PremiyamAday;
import majharul.soft.pkss.PremiyamBitorn;
import majharul.soft.pkss.R;
import majharul.soft.pkss.model.Member;
import majharul.soft.pkss.model.MemberHelper;
import okhttp3.ResponseBody;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {

    Context context;
    List<MemberHelper> listdata;

    public MemberAdapter(Context context, List<MemberHelper> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    public void setFilterList(List<MemberHelper> filterlist) {
        this.listdata = filterlist;
        notifyDataSetChanged();


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_model, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.team.setText(listdata.get(position).getTeam());
        holder.id.setText(listdata.get(position).getMember());
        holder.name.setText(listdata.get(position).getName());
        holder.vill.setText(listdata.get(position).getVillage());
        holder.mobile.setText(listdata.get(position).getPhone());
        holder.type.setText(listdata.get(position).getCategory());

        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("আপনি কি নিশ্চিত");
                builder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        String id = listdata.get(position).getMember();
                        String name = listdata.get(position).getName();
                        String team = listdata.get(position).getTeam();
                        String village = listdata.get(position).getVillage();
                        String mobile = listdata.get(position).getPhone();
                        String father = listdata.get(position).getFather();

                        Intent intent = new Intent(view.getContext(), PremiyamBitorn.class);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        intent.putExtra("team", team);
                        intent.putExtra("village", village);
                        intent.putExtra("mobile", mobile);
                        intent.putExtra("father", father);

                        view.getContext().startActivity(intent);


                    }
                });

                builder.setNegativeButton("না", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });


    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView team, name, vill, type, mobile, id;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            team = itemView.findViewById(R.id.teamName);
            id = itemView.findViewById(R.id.text_id);
            name = itemView.findViewById(R.id.nameMember);
            vill = itemView.findViewById(R.id.village);
            type = itemView.findViewById(R.id.type);
            mobile = itemView.findViewById(R.id.mobile);
            image = itemView.findViewById(R.id.edit);


        }
    }
}
