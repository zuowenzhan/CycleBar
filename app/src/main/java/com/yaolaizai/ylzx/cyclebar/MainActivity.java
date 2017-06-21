package com.yaolaizai.ylzx.cyclebar;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

public class MainActivity extends Activity {


    private CycleBar cbLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbLoading = (CycleBar) findViewById(R.id.cb_loading);

    }

    private boolean flag = false;

    public void startDownload(View v) {
        if(flag){
            return;
        }

        //在分线程中实现数据的扫描
        new Thread() {
            public void run() {
                flag = true;
                int count = 100;
                cbLoading.setMax(count);
                cbLoading.setProgress(0);

                for (int i = 0; i < count; i++) {
                    cbLoading.setProgress(cbLoading.getProgress() + 1);
                    //休眠
                    SystemClock.sleep(40);
                }
                flag = false;
            }
        }.start();


    }



}
