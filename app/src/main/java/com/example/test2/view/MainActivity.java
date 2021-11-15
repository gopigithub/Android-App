package com.example.test2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.test2.R;
import com.example.test2.model.CountryModel;
import com.example.test2.viewmodel.CountryViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CountryViewModel viewModel;
    private List<CountryModel> countryList = new ArrayList<>();
    private MainAdapter adapter;

    private TextView textView;
    private RecyclerView recyclerView;
    private List<CountryModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.message);
        textView.setText("Test Message");
        textView.setVisibility(View.INVISIBLE);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        viewModel =ViewModelProviders.of(this).get(CountryViewModel.class);
        viewModel.refresh();

        adapter = new MainAdapter(this, countryList);
        recyclerView.setAdapter(adapter);


        observeModel();

    }
    private void observeModel(){

        viewModel.countryLiveDataList.observe(this, CountryModels ->{
                    countryList = CountryModels;
                    adapter.updatecountryModelList(countryList);
                    adapter.notifyDataSetChanged();
                });
    }
}