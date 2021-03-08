package com.rku_18fotca11036.tutorial07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Register_Activity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    EditText edtFname, edtLname, edtUsername, edtPassword;
    Button btnRegistration;
    TextView txtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setTitle("Registration Here");
        setContentView(R.layout.activity_register_);

        databaseHelper = new DatabaseHelper(this);


        txtDisplay = findViewById(R.id.txtDisplay);
        edtFname = findViewById(R.id.edtFname);
        edtLname = findViewById(R.id.edtLname);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegistration = findViewById(R.id.btnRegistration);
    }

    public void insertvalue(View view) {
        String valFname = edtFname.getText().toString();
        String valLname = edtLname.getText().toString();
        String valUsername = edtUsername.getText().toString();
        String valPassword = edtPassword.getText().toString();

        if(valFname.equals(""))
        {
            Toast.makeText(this, "Please, insert your data", Toast.LENGTH_SHORT).show();
            return;
        }

        if(valLname.equals(""))
        {
            Toast.makeText(this, "Please, insert your data", Toast.LENGTH_SHORT).show();
            return;
        }

        if(valUsername.equals(""))
        {
            Toast.makeText(this, "Please, insert your data", Toast.LENGTH_SHORT).show();
            return;
        }

        if(valPassword.equals(""))
        {
            Toast.makeText(this, "Please, insert your data", Toast.LENGTH_SHORT).show();
            return;
        }

        if(databaseHelper.insertData(valFname,valLname,valUsername,valPassword))
        {
            Toast.makeText(this, "Insert Data", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, "Operation Failed", Toast.LENGTH_SHORT).show();
        }
    }

         

    public void getData(View view) {

        Cursor cursor = databaseHelper.getData();
        String data = "";

        if(cursor != null && cursor.getCount()>0)
        {
            cursor.moveToFirst();
            do{
                String valUsername = cursor.getString(1);
                String valPassword = cursor.getString(4);
                data += valUsername +" "+ valPassword +"\n";

            }
            while (cursor.moveToNext());

            txtDisplay.setText(data);
        }
        else
        {
            Toast.makeText(this, "Not record found", Toast.LENGTH_SHORT).show();
        }
    }
}