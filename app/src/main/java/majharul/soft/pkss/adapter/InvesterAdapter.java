package majharul.soft.pkss.adapter;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import majharul.soft.pkss.Dashboard;
import majharul.soft.pkss.InvesterList;
import majharul.soft.pkss.R;
import majharul.soft.pkss.model.InvesterHelp;
import majharul.soft.pkss.model.MemberHelper;

public class InvesterAdapter extends RecyclerView.Adapter<InvesterAdapter.ViewHolder> {
    InvesterList investerList;
    List<InvesterHelp> list;

    public InvesterAdapter(InvesterList investerList, List<InvesterHelp> list) {
        this.investerList = investerList;
        this.list = list;
    }

    public void setFilterList(List<InvesterHelp> filterlist) {
        this.list = filterlist;
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
        holder.team.setText(list.get(position).getTeam());
        holder.id.setText(list.get(position).getFather());
        holder.name.setText(list.get(position).getName());
        holder.vill.setText(list.get(position).getVillage());
        holder.mobile.setText(list.get(position).getPhone());
        holder.type.setText(list.get(position).getLicense());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: " + position);


                view.getContext().startActivity(new Intent(view.getContext(), Dashboard.class));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
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
