package com.example.assignment3_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {
    public ArrayList<String> AdapterList;
    private final Context context;

    public RecycleViewAdapter(Context context,ArrayList<String> ImportList){
        this.AdapterList = ImportList;
        this.context = context;

    }
    public RecycleViewAdapter(Context context){
        this.context = context;

    }
    @NonNull
    @Override
    public RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_item,parent));
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.MyViewHolder holder, int position) {
    //holder.textView.setText(AdapterList.get(position));
        holder.textView.setText("Hello world");
    }

    @Override
    public int getItemCount() {
        return 30;//AdapterList.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rv_textView);
        }
    }



}
