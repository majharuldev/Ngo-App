package majharul.soft.pkss.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.color.utilities.CorePalette;

import java.util.List;

import majharul.soft.pkss.MemberList;
import majharul.soft.pkss.PremiyamInput;
import majharul.soft.pkss.R;
import majharul.soft.pkss.SavingAday;
import majharul.soft.pkss.SavingList;
import majharul.soft.pkss.model.MemberHelper;

public class SavingAdayAdapter extends RecyclerView.Adapter<SavingAdayAdapter.ViewHolder> {

   SavingList savingList;
    List<MemberHelper> listdata;

    public SavingAdayAdapter(SavingList savingList, List<MemberHelper> listdata) {
        this.savingList = savingList;
        this.listdata = listdata;
    }

    public void setFilterList(List<MemberHelper> filterList) {

        this.listdata = filterList;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public SavingAdayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_model, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SavingAdayAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.team.setText(listdata.get(position).getTeam());
        holder.id.setText(listdata.get(position).getMember());
        holder.name.setText(listdata.get(position).getName());
        holder.vill.setText(listdata.get(position).getVillage());
        holder.mobile.setText(listdata.get(position).getPhone());
        holder.type.setText(listdata.get(position).getCategory());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(view.getContext());
                builder.setMessage("আপনি কি নিশ্চিত");
                builder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                        String id = listdata.get(position).getId();
                        String name = listdata.get(position).getName();
                        String team = listdata.get(position).getTeam();
                        String village = listdata.get(position).getVillage();
                        String mobile = listdata.get(position).getPhone();
                        String father = listdata.get(position).getFather();
                        String savings = listdata.get(position).getSavings();

                        //Toast.makeText(memberList, name, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), SavingAday.class);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        intent.putExtra("team", team);
                        intent.putExtra("village", village);
                        intent.putExtra("mobile", mobile);
                        intent.putExtra("father", father);
                        intent.putExtra("savings", savings);

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

        TextView team, name, vill, type, mobile,id;
        ImageView image, edit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            team = itemView.findViewById(R.id.teamName);
            name = itemView.findViewById(R.id.nameMember);
            vill = itemView.findViewById(R.id.village);
            type = itemView.findViewById(R.id.type);
            mobile = itemView.findViewById(R.id.mobile);
            image = itemView.findViewById(R.id.imageView);
            edit = itemView.findViewById(R.id.edit);
            id = itemView.findViewById(R.id.text_id);


        }
    }
}
