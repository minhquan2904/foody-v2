package com.example.minhquan.foodyv1.View;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TabHost;

import com.example.minhquan.foodyv1.Custom.BottomNavigationViewEx;
import com.example.minhquan.foodyv1.Database.DBWebservices;
import com.example.minhquan.foodyv1.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends TabActivity {

    TabHost tabHostMain;

    public final static String DATABASE_NAME="Foody.sqlite";
    private final String DB_PATH_SUFFIX = "/databases/";

    public static SQLiteDatabase database = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SetupDatabase();
        database = this.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE,null);

        // khoi tao thanh dieu huong duoi
        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setTextVisibility(false);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        tabHostMain = getTabHost();
        //tabHostMain.setup();

        TabHost.TabSpec tab1 = tabHostMain.newTabSpec("Home");
        TabHost.TabSpec tab2 = tabHostMain.newTabSpec("Collection");
        TabHost.TabSpec tab3 = tabHostMain.newTabSpec("Search");
        TabHost.TabSpec tab4 = tabHostMain.newTabSpec("Notification");
        TabHost.TabSpec tab5 = tabHostMain.newTabSpec("Account");

        tab1.setIndicator("Tab1");
        tab1.setContent(new Intent(this,HomeActivity.class));

        tab2.setIndicator("Tab2");
        tab2.setContent(new Intent(this,CollectionActivity.class));

        tab3.setIndicator("Tab3");
        tab3.setContent(new Intent(this,SearchActivity.class));

        tab4.setIndicator("Tab4");
        tab4.setContent(new Intent(this,NotifyActivity.class));

        tab5.setIndicator("Tab5");
        tab5.setContent(new Intent(this,ProfileActivity.class));

        tabHostMain.addTab(tab1);
        tabHostMain.addTab(tab2);
        tabHostMain.addTab(tab3);
        tabHostMain.addTab(tab4);
        tabHostMain.addTab(tab5);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    tabHostMain.setCurrentTab(0);
                    return true;
                case R.id.navigation_collection:
                    tabHostMain.setCurrentTab(1);
                    return true;
                case R.id.navigation_search:
                    tabHostMain.setCurrentTab(2);
                    return true;
                case R.id.navigation_notification:
                    tabHostMain.setCurrentTab(3);
                    return true;
                case R.id.navigation_account:
                    tabHostMain.setCurrentTab(4);
                    return true;
            }
            return false;
        }

    };


    private void SetupDatabase(){
        File file = getDatabasePath(DATABASE_NAME);
        if(!file.exists()){
            try {
                CopyDatabaseFromAsset();
                Log.w("DBLog","Setup database SUCCESS !!");
            }catch (Exception e){
                Log.w("DBLog","Setup database FAIL !!");
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try{
            InputStream myInput = getAssets().open(DATABASE_NAME);
            String outFile = getApplicationInfo().dataDir + DB_PATH_SUFFIX +DATABASE_NAME;

            File f = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);

            if(!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer))>0){
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();

        }catch (Exception ex){
            Log.d("Error",ex.toString());
        }
    }
}
