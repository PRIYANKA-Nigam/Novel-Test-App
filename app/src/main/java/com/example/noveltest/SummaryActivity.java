package com.example.noveltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class SummaryActivity extends AppCompatActivity {
    RecyclerView.LayoutManager layoutManager;RecyclerView recyclerView;
    List<Model> list; Adapter2 adapter2;

    String[] text={"Gulliver's Travel","Attitude is Everything","Black Sheep","Boundaries when to say Yes",
            "Call of Wild","Harry Potter and the Philospher's Stone","Growing up Spiritually",
            "Half GirlFriend","One Indian Girl","Rich Dad Poor Dad","The Alchemist",
            "The Monk who sold his Ferrari"
    };
    int[] image={R.drawable.half_girl, R.drawable.p1,R.drawable.half_girl, R.drawable.p1,R.drawable.half_girl, R.drawable.p1,
            R.drawable.half_girl, R.drawable.p1,R.drawable.half_girl, R.drawable.p1,R.drawable.half_girl,
            R.drawable.p1};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        list = new ArrayList<>(); adapter2 = new Adapter2(this, list.size(), list);
        layoutManager = new LinearLayoutManager(this); recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); int count = 0;
        for (String Name : text) { list.add(new Model(Name, image[count++]));
            recyclerView.setAdapter(adapter2); }
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.summary);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.novel:
                    startActivity(new Intent(getApplicationContext(),NovelActivity.class));
                    Toast.makeText(SummaryActivity.this,"Read The Novel Stories...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.summary:
                    return true;
                case R.id.bookmark:
                    startActivity(new Intent(getApplicationContext(),BookMark.class));
                    Toast.makeText(SummaryActivity.this,"Add BookMark for Important Content...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.audio:
                    startActivity(new Intent(getApplicationContext(),AudioActivity.class));
                    Toast.makeText(SummaryActivity.this,"Listen to the Novel Summary ...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
            }
            return false; }});
    }
}