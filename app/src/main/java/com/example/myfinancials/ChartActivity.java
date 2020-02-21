package com.example.myfinancials;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

import java.util.ArrayList;
import java.util.List;


public class ChartActivity extends AppCompatActivity {
    private PieChart pieChart;
    private Legend legend;
    private PieDataSet pieDataSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_chart);
        setContentView(R.layout.activity_chart);
        initPieChart();

    }

    private ArrayList getData(){
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(945f, "Charges"));
        entries.add(new PieEntry(1030f, "Clothes"));
        entries.add(new PieEntry(1143f, "Entertainment"));
        entries.add(new PieEntry(1250f, "Food"));
        entries.add(new PieEntry(1121f, "Installment"));
        entries.add(new PieEntry(1721f, "Miscellaneous"));
        entries.add(new PieEntry(2102f, "Transport"));
        return entries;
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
