package com.tsu.develop.plugapk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tsu.develop.pluglib.PluginActivity;

public class MyPluginActivity extends PluginActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin);
    }
}
