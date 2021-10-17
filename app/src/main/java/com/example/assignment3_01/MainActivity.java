package com.example.assignment3_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
/*
* Writing by:
* Yining Gan 19033174
* Yuting Zhao 20003036
* This application using camera
* we suggest using real phone to run it
* The demo video be put on submit zip file
* password and name can be anything
*
*/
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
        btn_login.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,ScanningActivity.class);
            identify_server();
            /*Connected to server to identify password and ID, but Not have server yet, typing anything to login*/
            if (identify) {
                Login_name = et_id.getText();
                if (MainActivity.this.getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
                    Log.i("info", "landscape");
                    //doing something...
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                            .setTitle("")
                            .setMessage("We suggest using portrait to continue")
                            .setIcon(R.drawable.a3)
                            .setPositiveButton("Continue", (dialogInterface, i) -> startActivity(intent))
                            .setNegativeButton("OK", (dialogInterface, i) -> {
                                return;
                            }).create();
                    alertDialog.show();
                }else {
                    //doing something...
                    Log.i("info", "portrait");
                    if (Login_name.equals("")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("").setMessage("").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
                    }
                    startActivity(intent);
                }
                Toast.makeText(MainActivity.this,"Welcome "+ Login_name,Toast.LENGTH_SHORT).show();
            }
        });
    }
}