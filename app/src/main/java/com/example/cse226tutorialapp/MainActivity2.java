package com.example.cse226tutorialapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    EditText q,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        tv = findViewById(R.id.tv4);
        SQLiteDatabase database = openOrCreateDatabase("query.db", this.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Query(cn TEXT, qu TEXT PRIMARY KEY);");
        database.close();
        q=findViewById(R.id.qr);
        p=findViewById(R.id.pn);
    }

    public void post(View v)
    {
        Toast.makeText(getApplicationContext(),"Thank You....You have posted your query.",Toast.LENGTH_LONG).show();
        SQLiteDatabase database1 = openOrCreateDatabase("query.db", this.MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("qu", q.getText().toString());
        values.put("cn", p.getText().toString());

        database1.insert("Query", null, values);
        database1.close();
        this.finish();
    }
}

