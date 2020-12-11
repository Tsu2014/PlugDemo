package com.tsu.develop.plugdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tsu.develop.plugdemo.common.Utils;
import com.tsu.develop.pluglib.PluginManager;
import com.tsu.develop.pluglib.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setListeners();
        init();
    }

    private void initViews(){
        button1 = findViewById(R.id.main_button1);
        button2 = findViewById(R.id.main_button2);
    }

    private void setListeners(){
        button1.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.main_button1:
                    loadPluginLib();
                    break;
                case R.id.main_button2:
                    startPluginActivity();
                    break;
            }
        }
    };

    private void loadPluginLib(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String apkPath = Utils.copyAssetAndWrite(MainActivity.this , "plugin.apk");
                PluginManager.getInstance().loadApk(apkPath);
            }
        }).start();

    }

    private void startPluginActivity(){
         Intent intent = new Intent();
         intent.setClass(this , ProxyActivity.class);
         intent.putExtra("className" , "com.tsu.develop.plugapk.MyPluginActivity");
         startActivity(intent);
    }

    private void init(){
        PluginManager.getInstance().init(this);
    }
}
