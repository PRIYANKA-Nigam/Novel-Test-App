package com.example.noveltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AudioActivity extends AppCompatActivity {
    private ListView listView; private ArrayList<Music> my_main_list;
    private MusicAdapter musicAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        listView=(ListView)findViewById(R.id.ll);
        my_main_list=new ArrayList<>();
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        my_main_list.add(new Music("Half GirlFriend","Chetan Bhagat",R.raw.half_girl_audio));
        MusicAdapter adapter=new MusicAdapter(this,R.layout.songs_item_list,my_main_list);
        listView.setAdapter(adapter);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.audio);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.novel:
                    startActivity(new Intent(getApplicationContext(),NovelActivity.class));
                    Toast.makeText(AudioActivity.this,"Read The Novel Stories...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.summary:
                    startActivity(new Intent(getApplicationContext(),SummaryActivity.class));
                    Toast.makeText(AudioActivity.this,"Read The Novel Summaries...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.bookmark:
                    startActivity(new Intent(getApplicationContext(),BookMark.class));
                    Toast.makeText(AudioActivity.this,"Add BookMark for Important Content ...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.audio:
                    return true;
            }
            return false; }});
    }
}