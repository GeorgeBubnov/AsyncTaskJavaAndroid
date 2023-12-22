package com.bubnov.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.bubnov.lab5.database.DatabaseContentProvider;
import com.bubnov.lab5.database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<String> arr = new ArrayList<String>();
    public static String last = "";
    public static int NOTIFICATION_ID = 101;
    public static String CHANNEL_ID = "channelID";
    ListView listView;
    Button buttonSearch;
    Button buttonView;
    Button buttonAdd;
    Button buttonUpdate;
    Button buttonDelete;
    public DatabaseHelper helper;
    public static DatabaseContentProvider provider = new DatabaseContentProvider();
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSearch = findViewById(R.id.buttonSearch);
        buttonView = findViewById(R.id.buttonView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);

        helper = new DatabaseHelper(this);
        provider.todo(this);

        context = this;

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view) != null)
                    getSupportFragmentManager().beginTransaction()
                            .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view)).commit();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view, SearchFragment.class, null)
                        .commit();
            }
        });buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view) != null)
                    getSupportFragmentManager().beginTransaction()
                            .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view)).commit();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view, FirstFragment.class, null)
                        .commit();
            }
        });
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view) != null)
                        getSupportFragmentManager().beginTransaction()
                                .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view)).commit();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view, SecondFragment.class, null)
                        .commit();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view) != null)
                    getSupportFragmentManager().beginTransaction()
                            .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view)).commit();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view, ThirdFragment.class, null)
                        .commit();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view) != null)
                    getSupportFragmentManager().beginTransaction()
                            .remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view)).commit();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_view, FourthFragment.class, null)
                        .commit();
            }
        });
    }
}