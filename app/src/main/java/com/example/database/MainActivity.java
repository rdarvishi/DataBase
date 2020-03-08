package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FlowerDBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dBhelper = new FlowerDBhelper(this);
        SQLiteDatabase db = dBhelper.getWritableDatabase();
        Log.i("database", "onCreate: db create");

        if (db != null && db.isOpen()) {
            db.close();
            Log.i("database", "onCreate: close");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(" import flowers from json").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                importJson();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void importJson() {

        InputStream input = getResources().openRawResource(R.raw.flowers_json);
        List<Flower> flowers = FlowerJsonParser.parsJson(input);

        Log.i("parsJson", "FlowerJson : return "+flowers.size()+ "item ");

        dBhelper.insertFlower(flowers.get(1));
    }
}
