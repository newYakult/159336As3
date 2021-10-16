package com.example.assignment3_01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.qrcode.core.QRCodeView;

/*
* This activity basing QRCodeView to development
* There are a lot of Chinese in third-party dependent library class comments
*
*
*
* */

public class ScanningActivity extends AppCompatActivity {
    private QRCodeView mQRCodeView;
    private Activity activity;
    private Button btn_ViewData;



    @Override
    public void onCreate(Bundle state) {
        ArrayList<String> Detected_Code= new ArrayList<String>() ;
        Detected_Code.clear();
        super.onCreate(state);
        activity = this;
        setContentView(R.layout.activity_scanning);
        mQRCodeView =  findViewById(R.id.zxingview);
        btn_ViewData = findViewById(R.id.btn_show_data);
        mQRCodeView.changeToScanQRCodeStyle();

            btn_ViewData.setEnabled(false);


        mQRCodeView.setDelegate(new QRCodeView.Delegate() {

            @Override
            public void onScanQRCodeSuccess(String result) {
                Log.d("Result", "result:" + result);//checking the result is correct or not
                btn_ViewData.setEnabled(true);
                Detected_Code.add(result);//adding data to list
                Toast.makeText(activity, "Result: "+result+" Saved", Toast.LENGTH_SHORT).show();
                vibrate();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mQRCodeView.startSpot();
                    }
                }, 3000);
            }
            @Override
            public void onScanQRCodeOpenCameraError() {
                Toast.makeText(activity, "open camera error！", Toast.LENGTH_SHORT).show();
            }
        });
        findViewById(R.id.start_spot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQRCodeView.startSpot();
                Toast.makeText(activity, "startSpot", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.stop_spot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQRCodeView.stopSpot();
                Toast.makeText(activity, "stopSpot", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.open_flashlight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQRCodeView.openFlashlight();
                Toast.makeText(activity, "openFlashlight", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.close_flashlight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQRCodeView.closeFlashlight();
                Toast.makeText(activity, "closeFlashlight", Toast.LENGTH_SHORT).show();
            }
        });
       // findViewById(R.id.btn_show_data).setOnClickListener(new View.OnClickListener() {
        btn_ViewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQRCodeView.stopSpot();
                mQRCodeView.closeFlashlight();
                Intent intent = new Intent(ScanningActivity.this,DataProcessingActivity.class);
                intent.putExtra("saved_list",(Serializable)Detected_Code);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();

        mQRCodeView.showScanRect();
    }

    @Override
    public void onBackPressed(){

        super.onBackPressed();
    }
    @Override
    protected void onStop() {
        //stop the camera
        mQRCodeView.stopCamera();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

}