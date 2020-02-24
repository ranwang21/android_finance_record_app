package com.example.myfinancials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myfinancials.entities.Record;
import com.example.myfinancials.managers.RecordManager;
import com.example.myfinancials.services.ConnexionBd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Intent intent;
    FloatingActionButton fabPlus, fabChart;
    int userId;
    ArrayList<Record> records;
    double income;
    double expenses;
    double balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // copy database
        ConnexionBd.copyBdFromAssets(this);
        this.setContentView(R.layout.activity_main);


        // get login user's id
        intent = getIntent();
        userId = intent.getIntExtra("userId", -1);

        // get all records from user login
        records = RecordManager.getRecordsByUserId(this ,userId);

        // calculate his income amount
        double totalAmount = 0;
        for (Record record : records){
            totalAmount += record.getAmount();
        }
        System.out.println("Total amount: " + totalAmount);
        // calculate his expenses total
        double totalExpenses = 0;
        for (Record record : records){
            if(record.getId_category() != 8){
                totalExpenses += record.getAmount();
            }
        }
        System.out.println("Total expenses: " + totalExpenses);
        // calculate his total income
        // calculate his balance

        // set plus button link
        fabPlus = findViewById(R.id.fab_plus);
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), AddNewActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        // set chart button link
        fabChart = findViewById(R.id.fab_chart);
        fabChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), ChartActivity.class);
                startActivity(intent);
            }
        });


    }
}
