package com.example.assignment3_01;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.Serializable;
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
    private List<String> Detected_Code;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        activity = this;
        setContentView(R.layout.activity_scanning);
        mQRCodeView =  findViewById(R.id.zxingview);
        mQRCodeView.changeToScanQRCodeStyle();
        mQRCodeView.setDelegate(new QRCodeView.Delegate() {

            @Override
            public void onScanQRCodeSuccess(String result) {
                Log.d("Result", "result:" + result);//checking the result is correct or not
                Toast.makeText(activity, "Result: "+result+" Saved", Toast.LENGTH_LONG).show();
                Detected_Code.add(result);//adding data to list
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
        findViewById(R.id.btn_show_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        //启动警告，点击返回键保存的数据将会丢失
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