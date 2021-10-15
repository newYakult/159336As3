package com.example.assignment3_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DataProcessingActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private ListView listView;
private ListViewAdapter listViewAdapter;
private RecycleViewAdapter recycleViewAdapter;
public  ArrayList<String> SaveList,thisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        SaveList = (ArrayList<String>) intent.getSerializableExtra("saved_list");//work
        setContentView(R.layout.activity_processing_data);
        listView = findViewById(R.id.lv_View);
        listView.setAdapter(listViewAdapter = new ListViewAdapter(DataProcessingActivity.this,SaveList));
        //recyclerView = findViewById(R.id.recycleView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(DataProcessingActivity.this));
        //Toast.makeText(DataProcessingActivity.this, SaveList.get(0)  , Toast.LENGTH_SHORT).show();
        //recyclerView.setAdapter(new RecycleViewAdapter(DataProcessingActivity.this));
    }
    public void init(){

    }
}