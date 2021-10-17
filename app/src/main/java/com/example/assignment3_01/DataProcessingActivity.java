package com.example.assignment3_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
                builder.setTitle("Hint").setMessage("Long Click to operate data").setNegativeButton("OK", new DialogInterface.OnClickListener() {
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
                builder.setTitle("What do you want to process this data?");
                builder.setItems(new CharSequence[]
                                {"Delete", "Copy", "View in browser", "Cancel"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Toast.makeText(DataProcessingActivity.this,"Item :<"+SaveList.get(position)+"> Removed",Toast.LENGTH_LONG).show();
                                        SaveList.remove(position);
                                        listViewAdapter.notifyDataSetChanged();
                                        break;
                                    case 1:
                                        ClipboardManager clipboardManager =(ClipboardManager) getSystemService(DataProcessingActivity.this.CLIPBOARD_SERVICE);
                                        clipboardManager.setPrimaryClip(ClipData.newPlainText(null,SaveList.get(position)));
                                        Toast.makeText(DataProcessingActivity.this, "Item :<"+SaveList.get(position)+"> Coped", Toast.LENGTH_SHORT).show();
                                        break;
                                    case 2:
                                        Uri url = Uri.parse(SaveList.get(position));
                                        Intent intent1 = new Intent(Intent.ACTION_VIEW,url);
                                        startActivity(intent1);
                                        break;
                                    case 3:
                                        break;
                                }
                            }
                        });
                builder.create().show();
                return false;
            }
        });

    }
    public void init(){

    }
}