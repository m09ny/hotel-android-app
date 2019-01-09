package hotel.hotelapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import entities.Department;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    ArrayList<Department> departments = new ArrayList<>();

    public RecyclerAdapter(ArrayList<Department> departments){
        this.departments = departments;
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.Id.setText((String.valueOf(departments.get(position).getId())));
        holder.Name.setText((String.valueOf(departments.get(position).getName())));
    }

    @Override
    public int getItemCount() {
        return departments.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Id, Name;

        public MyViewHolder(View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.id);
            Name = itemView.findViewById(R.id.name);
        }
    }
}
