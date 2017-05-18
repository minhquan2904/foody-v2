package com.example.minhquan.foodyv1.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.minhquan.foodyv1.Adapter.ListViewCityAdapter;
import com.example.minhquan.foodyv1.Model.ModelCity;
import com.example.minhquan.foodyv1.Object.City;
import com.example.minhquan.foodyv1.R;

import java.util.ArrayList;

public class SelectCityActivity extends AppCompatActivity {
    //private int selectedCountryId;
    private City selectedCity;
    private int selectedCityId;

    ArrayList<City> cityList;
    ModelCity cityDB;
    ListView listViewCity;
    ListViewCityAdapter listViewCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);

       // cityDB = new ModelCity(MainActivity.database);

        ModelCity modelCity = new ModelCity();
        selectedCityId = getIntent().getIntExtra("selectedCityId",0);

        // lay danh sach tinh/thanh pho

        cityList = modelCity.getAllCities();
        listViewCity = (ListView) findViewById(R.id.listView_city);
        listViewCityAdapter = new ListViewCityAdapter(getApplicationContext(), cityList, selectedCityId);
        listViewCity.setAdapter(listViewCityAdapter);

        // xu li chon tinh/thanh pho
        listViewCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedCity = (City) listViewCity.getAdapter().getItem(position);
                view.setSelected(true);
                ((ListViewCityAdapter) listViewCity.getAdapter()).setSelectedIndex(position);
                ((ListViewCityAdapter) listViewCity.getAdapter()).notifyDataSetChanged();
            }
        });

        // xu li tim kiem
        EditText editTextSearch = (EditText) findViewById(R.id.editText_search);
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                cityList = cityDB.getCityList(String.valueOf(s));
                listViewCityAdapter = new ListViewCityAdapter(getApplicationContext(), cityList, selectedCityId);
                listViewCity.setAdapter(listViewCityAdapter);
            }
        });

        // xu li tu dong lay vi tri
        RelativeLayout buttonAutoLocation = (RelativeLayout) findViewById(R.id.button_autoLocation);
        buttonAutoLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectCityActivity.this, "Tính năng chưa hoàn thiện", Toast.LENGTH_SHORT).show();
            }
        });

        // xu li doi quoc gia
        RelativeLayout buttonChangeCountry = (RelativeLayout) findViewById(R.id.button_changeCountry);
        buttonChangeCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectCityActivity.this, "Tính năng chưa hoàn thiện", Toast.LENGTH_SHORT).show();
            }
        });

        // xong:
        Button buttonDone = (Button) findViewById(R.id.button_done);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("isCityChanged",true);
                intent.putExtra("selectedCityId",selectedCity.getId());
                intent.putExtra("selectedCityName",selectedCity.getName());
                setResult(11,intent);   // 11: // request code tu HomeWhereFragment
                finish();
            }
        });

        // huy
        ImageButton buttonBack = (ImageButton) findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("isCityChanged",false);
                setResult(11,intent);   // 11: // request code tu HomeWhereFragment
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("isCityChanged",false);
        setResult(11,intent);   // 11: // request code tu HomeWhereFragment
        finish();
    }
}
