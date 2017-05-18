package com.example.minhquan.foodyv1.View;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.minhquan.foodyv1.R;

public class LoginActivity extends AppCompatActivity {
    Toolbar tb_login_main;
    Button btn_dangnhapbangemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        btn_dangnhapbangemail = (Button) findViewById(R.id.btn_dangnhapbangemail);
        tb_login_main = (Toolbar) findViewById(R.id.tb_login_main) ;

        //Set back arrow
        setSupportActionBar(tb_login_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        tb_login_main.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_dangnhapbangemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), LoginEmailActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}
