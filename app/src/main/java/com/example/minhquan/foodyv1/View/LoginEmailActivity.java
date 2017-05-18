package com.example.minhquan.foodyv1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.minhquan.foodyv1.Database.DBWebservices;
import com.example.minhquan.foodyv1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginEmailActivity extends AppCompatActivity {
    Button btn_login;
    EditText edt_login_email;
    EditText edt_login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_email);
        btn_login = (Button) findViewById(R.id.btn_login);
        edt_login_email = (EditText) findViewById(R.id.edt_login_email);
        edt_login_password = (EditText) findViewById(R.id.edt_login_password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "login?email="+edt_login_email.getText().toString()+"&password="+edt_login_password.getText().toString()+"";

                DBWebservices webservices = new DBWebservices(url);
                webservices.execute();
                try {
                    String JSON = webservices.get();
                    JSONObject object = new JSONObject(JSON);
                    if(JSON != null)
                    {
                        Intent intent = new Intent(LoginEmailActivity.this,ProfileActivity.class);

                       intent.putExtra("name",object.getString("name"));

                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(LoginEmailActivity.this,"Error password",Toast.LENGTH_LONG).show();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
