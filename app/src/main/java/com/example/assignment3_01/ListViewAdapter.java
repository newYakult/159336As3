package com.example.assignment3_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {
    private ArrayList<String> AdapterList;
    private Context mContext;
    private LayoutInflater layoutInflater;
    public ListViewAdapter(Context context, ArrayList<String> List){
        this.mContext = context;
        layoutInflater = LayoutInflater.from(context);
        this.AdapterList = List;
    }
    @Override
    public int getCount() {
        return AdapterList.size();
    }

    @Override
    public Object getItem(int i) {
        return AdapterList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view==null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.layout_item,viewGroup,false);
            viewHolder.textView = view.findViewById(R.id.rv_textView);
            view.setTag(viewHolder);
        }
        else{
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(AdapterList.get(i));
        return view;
    }
    private class ViewHolder{
        public TextView textView;
    }
}
