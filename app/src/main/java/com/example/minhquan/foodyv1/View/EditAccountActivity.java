package com.example.minhquan.foodyv1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;

import com.example.minhquan.foodyv1.R;

public class EditAccountActivity extends AppCompatActivity {
    Toolbar tb_edit_main;
    LinearLayout lnPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        lnPass = (LinearLayout) findViewById(R.id.lnPass);
        tb_edit_main = (Toolbar) findViewById(R.id.tb_edit_main);
        //Set back arrow
        setSupportActionBar(tb_edit_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        tb_edit_main.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lnPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(),ChangePasswordActivity.class);
                startActivity(myIntent);
            }
        });
    }

}
