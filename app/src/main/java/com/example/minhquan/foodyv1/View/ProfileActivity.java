package com.example.minhquan.foodyv1.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minhquan.foodyv1.R;

/**
 * Created by MinhQuan on 4/17/2017.
 */

public class ProfileActivity extends AppCompatActivity {

    LinearLayout lnLogin;
    LinearLayout ln_edit_profile;
    TextView txtNameLogin;
    LinearLayout lnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);

        txtNameLogin = (TextView) findViewById(R.id.txtNameLogin) ;
        Intent intent = getIntent();
        if(intent!= null){
             txtNameLogin.setText(intent.getStringExtra("name"));
        }
        lnMap = (LinearLayout) findViewById(R.id.lnMap);
//        lnMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent myIntent = new Intent(v.getContext(), MapsActivity.class);
//                startActivity(myIntent);
//            }
//        });

        lnLogin = (LinearLayout) findViewById(R.id.lnLogin);
        ln_edit_profile = (LinearLayout) findViewById(R.id.ln_edit_profile);

        lnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), LoginActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        ln_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), EditAccountActivity.class);
                startActivity(myIntent);
            }
        });

    }
}
