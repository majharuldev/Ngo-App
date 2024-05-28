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

import java.util.List;

import majharul.soft.pkss.PremiyamAday;
import majharul.soft.pkss.PremiyamBitorn;
import majharul.soft.pkss.PremiyamInput;
import majharul.soft.pkss.R;
import majharul.soft.pkss.model.Member;
import majharul.soft.pkss.model.MemberHelper;
import majharul.soft.pkss.model.PrimiyamAdayHelper;

public class PremiyamAdayAdapter extends RecyclerView.Adapter<PremiyamAdayAdapter.ViewHolder> {

    PremiyamAday premiyamAday;
    List<PrimiyamAdayHelper> listdata;

    public PremiyamAdayAdapter(PremiyamAday premiyamAday, List<PrimiyamAdayHelper> listdata) {
        this.premiyamAday = premiyamAday;
        this.listdata = listdata;
    }

    public void setFilterList(List<PrimiyamAdayHelper> filterList) {

        this.listdata = filterList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.primiyamaday, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemview);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.team.setText(listdata.get(position).getTeam());
        holder.id.setText(listdata.get(position).getMember());
        holder.name.setText(listdata.get(position).getName());
        holder.vill.setText(listdata.get(position).getVillage());
        holder.mobile.setText(listdata.get(position).getPrimiyam_poriman());
        holder.type.setText(listdata.get(position).getCategory());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("আপনি কি নিশ্চিত?");
                builder.setPositiveButton("হ্যাঁ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String id = listdata.get(position).getId();
                        String name = listdata.get(position).getName();
                        String team = listdata.get(position).getTeam();
                        String village = listdata.get(position).getVillage();
                        String primiyamNumber = listdata.get(position).getPrimyam_number();
                        String father = listdata.get(position).getFather();
                        String munafa_loan = listdata.get(position).getMunafa_lon();
                        String primyam_poriman = listdata.get(position).getPrimiyam_poriman();


                        Intent intent = new Intent(view.getContext(), PremiyamInput.class);
                        intent.putExtra("id", id);
                        intent.putExtra("name", name);
                        intent.putExtra("team", team);
                        intent.putExtra("village", village);
                        intent.putExtra("primyamNumber", primiyamNumber);
                        intent.putExtra("father", father);
                        intent.putExtra("munafa_loan", munafa_loan);
                        intent.putExtra("primyam_poriman", primyam_poriman);


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
