package com.example.tma4_2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private  Context mContext;
    private  List<Student> mValues;
    private MyListner listner;


    public MyItemRecyclerViewAdapter(List<Student> items, Context mContext) {
        mValues = items;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.fragment_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(holder.getAdapterPosition());
        holder.tvName.setText(mValues.get(holder.getAdapterPosition()).getName());
        holder.tvId.setText("ID: " +mValues.get(holder.getAdapterPosition()).getId()+"");
        holder.tvYear.setText(mValues.get(holder.getAdapterPosition()).getMajor() +" Year "+ mValues.get(holder.getAdapterPosition()).getYear()+"");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
               builder.setMessage("Delete student with ID "+ mValues.get(holder.getAdapterPosition()).getId())
                       .setTitle("Confirm Deletion")
                       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.dismiss();
                               listner.getItemId(mValues.get(holder.getAdapterPosition()));
                           }
                       })
                       .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                                 dialogInterface.dismiss();
                           }
                       }).show();

            }

        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
          TextView tvName;
          TextView tvId;
          TextView tvYear;

        public Student mItem;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvId = itemView.findViewById(R.id.tv_id);
            tvYear = itemView.findViewById(R.id.tv_year);

        }

    }

    public void update(List<Student> students){
        this.mValues = students;
        notifyDataSetChanged();
    }

    public void setListner(MyListner listner){
        this.listner = listner;
    }

  public  interface  MyListner{
        void getItemId(Student student);
  }
}