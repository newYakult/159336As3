package com.example.assignment3_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class DataProcessingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        List<String> SaveList = (List<String>) intent.getSerializableExtra("saved_list");
        setContentView(R.layout.activity_data_processing);
    }
}