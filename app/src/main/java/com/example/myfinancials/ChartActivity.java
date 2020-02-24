package com.example.myfinancials;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfinancials.entities.Record;
import com.example.myfinancials.managers.RecordManager;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.zxing.oned.rss.AbstractRSSReader;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


public class ChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    private Legend legend;
    private PieDataSet pieDataSet;
    private ArrayList<Record> records;
    int id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_chart);
        setContentView(R.layout.activity_chart);
        initPieChart();
        fillTable();
    }

    private ArrayList getData(){
        Intent intent = getIntent();
        id_user = intent.getIntExtra("id_user", -1);
        records = RecordManager.getRecordsByUserId(this, id_user);
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Record record : records){
            if(record.getId_category() != 8){
                String category = "Unknown";
                switch (record.getId_category()){
                    case 1:
                        category = "Charges";
                        break;
                    case 2:
                        category = "Entertainment";
                        break;
                    case 3:
                        category = "Food";
                        break;
                    case 4:
                        category = "Clothes";
                        break;
                    case 5:
                        category = "installment";
                        break;
                    case 6:
                        category = "Miscellaneous";
                        break;
                    case 7:
                        category = "Transport";
                        break;
                }
                entries.add(new PieEntry((float)record.getAmount(), category));
            }
        }
        return entries;
    }

    private void fillTable(){
        TextView charges = findViewById(R.id.table_charges);
        TextView entertainment = findViewById(R.id.table_entertainment);
        TextView food = findViewById(R.id.table_food);
        TextView clothes = findViewById(R.id.table_clothes);
        TextView installment = findViewById(R.id.table_installment);
        TextView miscellaneous = findViewById(R.id.table_miscellaneous);
        TextView transport = findViewById(R.id.table_transport);
        TextView income = findViewById(R.id.table_income);
        TextView expense = findViewById(R.id.table_expense);
        TextView balance = findViewById(R.id.table_balance);

        double valueCharges = 0;
        double valueEntertainment = 0;
        double valueFood = 0;
        double valueClothes = 0;
        double valueInstallment = 0;
        double valueMiscellaneous = 0;
        double valueTransport = 0;
        double valueIncome = 0;
        double valueExpense = 0;
        double valueBalance = 0;

        records = RecordManager.getRecordsByUserId(this, id_user);

        for (Record record : records){
            switch (record.getId_category()){
                case 1:
                    valueCharges += record.getAmount();
                    valueExpense += record.getAmount();
                    break;
                case 2:
                    valueEntertainment += record.getAmount();
                    valueExpense += record.getAmount();
                    break;
                case 3:
                    valueFood += record.getAmount();
                    valueExpense += record.getAmount();
                    break;
                case 4:
                    valueClothes += record.getAmount();
                    valueExpense += record.getAmount();
                    break;
                case 5:
                    valueInstallment += record.getAmount();
                    valueExpense += record.getAmount();
                    break;
                case 6:
                    valueMiscellaneous += record.getAmount();
                    valueExpense += record.getAmount();
                    break;
                case 7:
                    valueTransport += record.getAmount();
                    valueExpense += record.getAmount();
                    break;
                case 8:
                    valueIncome += record.getAmount();
            }
            valueBalance = valueIncome - valueExpense;
        }

        // set text values
        charges.setText(Double.toString(valueCharges));
        entertainment.setText(Double.toString(valueEntertainment));
        food.setText(Double.toString(valueFood));
        clothes.setText(Double.toString(valueClothes));
        installment.setText(Double.toString(valueInstallment));
        miscellaneous.setText(Double.toString(valueMiscellaneous));
        transport.setText(Double.toString(valueTransport));
        income.setText(Double.toString(valueIncome));
        expense.setText(Double.toString(valueExpense));
        balance.setText(Double.toString(valueBalance));
    }

    private void initPieChart(){
        // pie chart with mock data
        // prepare data for the pie (fill data set and format data)
        pieChart = (PieChart)findViewById(R.id.pie_chat);
        pieDataSet = new PieDataSet(getData(),"");
        pieDataSet.setValueTextSize(14);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueFormatter(new PercentFormatter(pieChart));
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        // style the pie colors, its animation and other parameters
        pieChart.setTouchEnabled(true);
        int[] colors = new int[]{Color.rgb(69,1,105), Color.rgb(96,0,116), Color.rgb(131,21,116), Color.rgb(82,46,8), Color.rgb(119,17,34), Color.rgb(79,45,78), Color.rgb(190,182,174)};
        pieDataSet.setColors(ColorTemplate.createColors(colors));
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.animateXY(1000, 1000);
        pieChart.setDrawSliceText(false);
        // style the legend
        legend = pieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.CENTER);
        pieChart.invalidate();
    }
}
