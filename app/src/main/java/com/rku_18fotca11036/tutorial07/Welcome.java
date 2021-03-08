package com.rku_18fotca11036.tutorial07;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    TextView txtId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtId = findViewById(R.id.txtId);
        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("username");
        txtId.setText(str);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreatePanelMenu(featureId, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.logout:
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
                builder.setTitle("Confirm");
                builder.setMessage("are you sure you want to logout");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(Welcome.this, "Logout", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

//
//                startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
//                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}