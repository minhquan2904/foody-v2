package com.example.minhquan.foodyv1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.minhquan.foodyv1.R;

public class FlashScreen extends AppCompatActivity {
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        progressBar = (ProgressBar) findViewById(R.id.pg_flash_screen);

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                }catch (Exception e){

                }finally{
                    Intent intent=new Intent(FlashScreen.this,MainActivity.class);
                    startActivity(intent); finish();
                }
            }
        });
        thread.start();
    }
}
