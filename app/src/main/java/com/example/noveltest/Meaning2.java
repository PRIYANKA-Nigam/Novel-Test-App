package com.example.noveltest;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Locale;

public class Meaning2 extends AppCompatActivity {
    String url;
    TextView textView;
    EditText e;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meaning2);
        e=(EditText)findViewById(R.id.editTextTextPersonName2);
        textView=(TextView)findViewById(R.id.textView2);
    }

    public void bSpeech(View view) {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hi Speak Something.");
        try { startActivityForResult(intent,1);
        }catch (ActivityNotFoundException e){ Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show(); } }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if (resultCode==RESULT_OK && data!=null){
                    ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    e.setText(result.get(0)); }
                break; }
    }
    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = e.getText().toString();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
    public void sendRequestOnClick(View v){
        DictionaryRequest dr=new DictionaryRequest(this,textView);
        url=dictionaryEntries();
        dr.execute(url);
    }
}