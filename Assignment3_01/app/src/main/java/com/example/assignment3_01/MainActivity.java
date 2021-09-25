package com.example.assignment3_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
* This application using camera
* I suggest using real phone
* The demo video be put on submit zip file
* password and name can be anything
* */
public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private EditText et_id,et_ps;
    private boolean identify = false;
    private Editable Login_name;
    private void identify_server(){
        identify = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = findViewById(R.id.btn);
        et_id = findViewById(R.id.et_id);
        et_ps = findViewById(R.id.et_ps);
        btn_login.setEnabled(false);
        et_ps.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btn_login.setEnabled(true);

            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ScanningActivity.class);
                identify_server();
                /*Connected to server to identify password and ID, but Not have server yet, typing anything to login*/
                if (identify) {
                    Login_name = et_id.getText();
                    if (MainActivity.this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                        Log.i("info", "landscape");
                        //doing something...
                    }else {
                        //doing something...
                        Log.i("info", "portrait");
                    }
                    Toast.makeText(MainActivity.this,"Welcome "+ Login_name,Toast.LENGTH_SHORT).show();

                    startActivity(intent);
                }
            }
        });
    }
}