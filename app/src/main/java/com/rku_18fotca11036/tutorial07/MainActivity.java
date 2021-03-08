package com.rku_18fotca11036.tutorial07;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.file.WatchEvent;

import static android.app.ActionBar.DISPLAY_SHOW_CUSTOM;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUsername, edtPassword;
    DatabaseHelper db;
    SharedPreferences preferences;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayOptions(DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        db = new DatabaseHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String valUsername = edtUsername.getText().toString();
                String valPassword = edtPassword.getText().toString();



                Boolean up = db.match(valUsername,valPassword);
                if(up==true)
                {

                    Intent intent = new Intent(getApplicationContext(),Welcome.class);
                    intent.putExtra("username",valUsername);
                    startActivity(intent);

                    Toast.makeText(MainActivity.this, "Login Succssful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, Welcome.class));


                }
                else
                {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void register(View view) {
        startActivity(new Intent(MainActivity.this, Register_Activity.class));
    }
}