package com.example.assignment3_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;
import java.util.List;

public class DataProcessingActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private Toolbar mToolbar;
private ListView listView;
private ListViewAdapter listViewAdapter;
public  ArrayList<String> SaveList,thisList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        SaveList = (ArrayList<String>) intent.getSerializableExtra("saved_list");//work
        thisList = SaveList;
        setContentView(R.layout.activity_processing_data);
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DataProcessingActivity.this);
                builder.setTitle("Hint").setMessage("Long Click to delete data").setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
        listView = findViewById(R.id.lv_View);
        listViewAdapter = new ListViewAdapter(DataProcessingActivity.this,SaveList);
        listView.setAdapter(listViewAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DataProcessingActivity.this);
                builder.setTitle("Warning").setMessage("Item:< "+SaveList.get(position)+" >Will be delete").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DataProcessingActivity.this,"Item :<"+SaveList.get(position)+"> Removed",Toast.LENGTH_LONG).show();
                        SaveList.remove(position);
                        listViewAdapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
                return false;
            }
        });

    }
    public void init(){

    }
}