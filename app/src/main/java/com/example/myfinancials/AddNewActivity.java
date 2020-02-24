package com.example.myfinancials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myfinancials.entities.Record;
import com.example.myfinancials.managers.RecordManager;
import com.example.myfinancials.services.ConnexionBd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;


public class AddNewActivity extends AppCompatActivity {
    Context ctx;
    FloatingActionButton fabSubmit, fabCancel;
    Spinner dropdown;
    DatePicker datePicker;
    EditText amount, description;
    Intent intent;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_add_new);

        // get login user's id
        intent = getIntent();
        userId = intent.getIntExtra("userId", -1);

        // submit and cancel button
        fabSubmit = findViewById(R.id.fab_submit);
        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long result;
                result = addRecord();
                // prompt a snackbar when successed
                System.out.println(result);
                if(result > 0){
                    Snackbar.make(view, "Record Added", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

            }
        });
        fabCancel = findViewById(R.id.fab_cancel);
        fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private long addRecord() {
        // init the views
        dropdown = findViewById(R.id.spinner_category);
        datePicker = findViewById(R.id.date_picker);
        amount = findViewById(R.id.edittext_amount);
        description = findViewById(R.id.edittext_description);
        // init result
        long result;
        // format the date picked
        String date = "" + datePicker.getYear() + "-" + datePicker.getMonth() + "-" + datePicker.getDayOfMonth();
        // format the category picked (retrieve its id)
        String category;
        int categoryId = 0;
        switch (dropdown.getSelectedItem().toString()){
            case "Charges":
                categoryId = 1;
            case "Clothes":
                categoryId = 2;
            case "Entertainment":
                categoryId = 3;
            case "Food":
                categoryId = 4;
            case "Installment":
                categoryId = 5;
            case "Miscellaneous":
                categoryId = 6;
            case "Transport":
                categoryId = 7;
        }
        // construct a record object
        Record record = new Record(Double.parseDouble(amount.getText().toString()), description.getText().toString(), date, categoryId, userId);
        // call the manager and add record to the database
        result = RecordManager.addOneRecord(this, record);
        return result;
    }
}
