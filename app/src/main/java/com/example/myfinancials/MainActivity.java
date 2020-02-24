package com.example.myfinancials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myfinancials.adapters.RecordListAdapter;
import com.example.myfinancials.entities.Record;
import com.example.myfinancials.managers.RecordManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Intent intent;
    FloatingActionButton fabPlus, fabChart;
    int id_user;
    ArrayList<Record> records;
    TextView expense, income, balance;
    ListView list;
    List<Map<String, Object>> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        // get userId
        intent = getIntent();
        id_user = intent.getIntExtra("userId", -1);
        System.out.println(id_user);
        // get all records of the login user
        records = RecordManager.getRecordsByUserId(MainActivity.this, id_user);
        // get total expenses and income, balance
        double totalExpenses = 0;
        double totalAmount = 0;
        double totalIncome = 0;
        double totalBalance = 0;
        for (Record record : records) {
            totalAmount += record.getAmount();
            if (record.getId_category() != 8) {
                totalExpenses += record.getAmount();
            } else {
                totalIncome += record.getAmount();
            }
        }
        totalBalance = totalIncome - totalExpenses;

        expense = findViewById(R.id.total_expense);
        income = findViewById(R.id.total_income);
        balance = findViewById(R.id.total_balance);

        expense.setText(Double.toString(totalExpenses));
        income.setText(Double.toString(totalIncome));
        balance.setText(Double.toString(totalBalance));


        // show all records of the user
        initData(records);

        // plus button
        fabPlus = findViewById(R.id.fab_plus);
        fabPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), AddNewActivity.class);
                intent.putExtra("id_user", id_user);
                startActivity(intent);
            }
        });

        // chart button
        fabChart = findViewById(R.id.fab_chart);
        fabChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), ChartActivity.class);
                intent.putExtra("id_user", id_user);
                startActivity(intent);
            }
        });
    }

    private void initData(ArrayList<Record> records) {
        list = findViewById(R.id.record_list);
        String[] from = {"date", "genre", "category", "description", "amount"};
        int[] to = {R.id.singlelist_date, R.id.singlelist_genre, R.id.singlelist_category, R.id.singlelist_description, R.id.singlelist_amount};

        for(Record record : records){
            Map<String, Object> map = new HashMap<>();
            map.put("date", record.getDate());
            map.put("genre", "Expense");
            map.put("category", "category");
            map.put("description", record.getDescription());
            map.put("amount", record.getAmount());

            data.add(map);
        }
        
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.single_record, from, to);
        list.setAdapter(adapter);
    }
}
