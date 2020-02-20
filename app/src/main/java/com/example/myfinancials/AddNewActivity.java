package com.example.myfinancials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class AddNewActivity extends AppCompatActivity {

    FloatingActionButton fabSubmit, fabCancel;
    Spinner dropdown;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_add_new);
        setContentView(R.layout.activity_add_new);

        // submit and cancel button
        fabSubmit = findViewById(R.id.fab_submit);
        fabSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Record Added", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fabCancel = findViewById(R.id.fab_cancel);
        fabCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // drop-down category
        dropdown = findViewById(R.id.spinner_category);
    }
}
