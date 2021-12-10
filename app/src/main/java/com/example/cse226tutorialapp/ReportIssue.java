package com.example.cse226tutorialapp;



import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

    public class ReportIssue extends AppCompatActivity {
        TextView tv;
        EditText s1,e;

        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.reportissue);
            tv = findViewById(R.id.tv4);
            SQLiteDatabase database = openOrCreateDatabase("issue.db", this.MODE_PRIVATE, null);
            database.execSQL("CREATE TABLE IF NOT EXISTS Issue(em TEXT, iss TEXT PRIMARY KEY);");
            database.close();
            s1=findViewById(R.id.is);
            e=findViewById(R.id.ea);
        }

        public void issue(View v)
        {
            Toast.makeText(getApplicationContext(),"Thank You....You have reported an issue in our app.",Toast.LENGTH_LONG).show();
            SQLiteDatabase database1 = openOrCreateDatabase("issue.db", this.MODE_PRIVATE, null);
            ContentValues values = new ContentValues();
            values.put("iss", s1.getText().toString());
            values.put("em", e.getText().toString());
            database1.insert("Issue", null, values);
            database1.close();
            this.finish();
        }
    }





