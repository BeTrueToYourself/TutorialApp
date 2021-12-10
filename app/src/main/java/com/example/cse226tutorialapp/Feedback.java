package com.example.cse226tutorialapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {
    TextView tv1;
    EditText s2,n;
    ListView listView;
    TextView tv;
    ArrayAdapter<String> adapter;
    String contacts[]={"Open URL Or Copy The URL"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        tv1 = findViewById(R.id.tv5);
        SQLiteDatabase database = openOrCreateDatabase("feedback.db", this.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Feed(nm TEXT, fd TEXT PRIMARY KEY);");
        database.close();
        s2=findViewById(R.id.fk);
        n=findViewById(R.id.na);
        listView=findViewById(R.id.listView);
        tv=findViewById(R.id.tv4);
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,contacts);
        listView.setAdapter(adapter);
        // Register the ListView  for Context menu
        registerForContextMenu(listView);
    }

    public void fdk(View v)
    {
        Toast.makeText(getApplicationContext(),"Thank You....You have given your feedback to our app.",Toast.LENGTH_LONG).show();
        SQLiteDatabase database1 = openOrCreateDatabase("feedback.db", this.MODE_PRIVATE, null);
        ContentValues values = new ContentValues();
        values.put("fd", s2.getText().toString());
        values.put("nm", n.getText().toString());
        database1.insert("Feed", null, values);
        database1.close();
        this.finish();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
        menu.setHeaderTitle("Select From The Menu");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getItemId()==R.id.ou){
            Toast.makeText(getApplicationContext(),"Open The URL",Toast.LENGTH_LONG).show();
            gotoUrl("https://github.com/BeTrueToYourself/TutorialApp/tree/master");

        }
        else if(item.getItemId()==R.id.cu){
            ClipboardManager clipboard=(ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip=ClipData.newPlainText("TextView", tv.getText().toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(getApplicationContext(),"URL is copied.",Toast.LENGTH_LONG).show();
        }else{
            return false;
        }
        return true;

    }
    private void gotoUrl(String s)
    {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}





