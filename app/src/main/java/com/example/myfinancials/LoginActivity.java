package com.example.myfinancials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {

    Button btn_sign;
    EditText edt_email, edt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            btn_sign = findViewById(R.id.btn_signIn);

        }
    }
}
