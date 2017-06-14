package com.example.minhquan.foodyv1.View;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minhquan.foodyv1.Database.DBWebservices;
import com.example.minhquan.foodyv1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ChangePasswordActivity extends AppCompatActivity {
    Button btn_luumatkhaumoi;
    TextView txt_cur_pass,txt_matkhaumoi,txt_nhaplaimatkhaumoi,txt_emaildadangky;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btn_luumatkhaumoi = (Button) findViewById(R.id.btn_luumatkhaumoi);
        txt_cur_pass = (TextView) findViewById(R.id.txt_cur_pass);
        txt_matkhaumoi = (TextView) findViewById(R.id.txt_matkhaumoi);
        txt_nhaplaimatkhaumoi = (TextView) findViewById(R.id.txt_nhaplaimatkhaumoi);
        txt_emaildadangky = (TextView) findViewById(R.id.txt_emaildadangky);
        btn_luumatkhaumoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curPass = txt_cur_pass.getText().toString();
                String newPass = txt_matkhaumoi.getText().toString();
                String confirm = txt_nhaplaimatkhaumoi.getText().toString();
                String email = txt_emaildadangky.getText().toString();

                if(!newPass.equals(confirm))
                {
                    Toast.makeText(v.getContext(),"Mật khẩu mới và mật khẩu xác nhận không đúng",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean rs;
                    rs=changePass(email,newPass);
                    if(rs==true)
                    {
                        Toast.makeText(v.getContext(),"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(v.getContext(),"Lỗi",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    DBWebservices dbWebservices;
    private Boolean checkPass(String email,String pass)
    {
        String url = "login?email="+email+"&password="+pass+"";
        dbWebservices = new DBWebservices(url);
        dbWebservices.execute();
        try {
            String JSON = dbWebservices.get();
            JSONObject object = new JSONObject(JSON);
            if(JSON != null)
            {
               return true;

            }
            else
            {
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
    private Boolean changePass(String email,String pass)
    {
        dbWebservices = new DBWebservices("update?email="+email+"&password="+pass+"");
        dbWebservices.execute();
        String JSON = null;
        try {
            String data = dbWebservices.get();

            if(data != null)
            {

                return true;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return false;
    }
}
