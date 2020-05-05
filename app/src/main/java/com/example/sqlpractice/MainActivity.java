package com.example.sqlpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    EditText etName,etCell;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etCell = findViewById(R.id.etCell);
        btn = findViewById(R.id.button);
        btn.setVisibility(View.GONE);
        etCell.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                btn.setVisibility(View.VISIBLE);
            }
        });
    }

    public  void btnSubmit(View v)
    {
        String name,cell;
        name = etName.getText().toString().trim();
        cell = etCell.getText().toString().trim();

            ContactsDB db = new ContactsDB(this);
            db.open();
            db.createEntry(name, cell);
            etName.setText("");
            etCell.setText("");
            db.close();
    }
    public void btnShowData(View v)
    {
        startActivity(new Intent(MainActivity.this, Data.class));
    }
    public void btnEditData(View v)
    {
        ContactsDB db = new ContactsDB(this);
        db.open();
        db.updateEntry("3", "Abdullah", "03234689546");
        db.close();
    }
    public void btnDeleteData(View v)
    {
        ContactsDB db = new ContactsDB(this);
        db.open();
        long number = db.deleteEntry("2");
        Toast.makeText(this, ""+number, Toast.LENGTH_SHORT).show();
        db.close();
    }
}
