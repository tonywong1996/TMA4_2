package com.example.tma4_2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    public static  ExecutorService dbExecutor = Executors.newFixedThreadPool(4);

    public AppDatabase appDatabase;
    public StudentDao studentDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         appDatabase = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class, "MessageDB").build();

        studentDao = appDatabase.studentDao();


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ListFragment())
                    .commit();
        }



    }

}