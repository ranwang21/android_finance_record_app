package com.example.myfinancials;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfinancials.entities.User;
import com.example.myfinancials.managers.UserManager;
import com.example.myfinancials.services.ConnexionBd;


public class LoginActivity extends AppCompatActivity {

    Context ctx;
    Button btn_sign;
    EditText edt_email, edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            super.onCreate(savedInstanceState);
            ctx = this;
            // set context of db connection
            ConnexionBd.copyBdFromAssets(this);

            setContentView(R.layout.activity_login);

            // get the credentials by user
            btn_sign = findViewById(R.id.btn_signIn);
            edt_email = findViewById(R.id.edt_email);
            edt_password = findViewById(R.id.edt_password);
            // click sign in
            btn_sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean validUser = UserManager.checkUserLogin(ctx, edt_email.getText().toString(), edt_password.getText().toString());
                    if (validUser) {
                        // if login success, open main activity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    } else {
                        // if credentials are invalid or any field empty, prompt a toast
                        Toast.makeText(getApplicationContext(), "Invalid login", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
