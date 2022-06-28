package com.example.noveltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookMark extends AppCompatActivity {
    Context context;DBHelper dbHelper;private ListView listView;private TextView textView;private Button button;
    private List<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_mark);
        context=this;
        dbHelper=new DBHelper(context);
        listView=(ListView)findViewById(R.id.ll);
        textView=(TextView)findViewById(R.id.textView4);
        button=(Button)findViewById(R.id.button);
        int count=dbHelper.countBooks();
        textView.setText("You have "+count +" books");
        books=new ArrayList<>();
        books=dbHelper.getAllBooks();
        BookAdapter bookAdapter=new BookAdapter(context,R.layout.single_book,books);
        listView.setAdapter(bookAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,AddBook.class));
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book=books.get(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                String finishRead=new SimpleDateFormat("dd/MM/yy").format(book.getStarted());
                if(book.getFinished()==0){
                    finishRead="Still Reading";

                }
                builder.setTitle(book.getTitle()).setMessage("By "+book.getAuthor()+
                        "\n\n\n" +new SimpleDateFormat("dd/MM/yy").format(book.getStarted())+"\n"+finishRead +"\n\n"+ book.getReview()+ "\n")
                        .setPositiveButton("Finished Reading", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                book.setFinished(System.currentTimeMillis());
                                dbHelper.updateBook(book);
                                startActivity(new Intent(context,BookMark.class));

                            }
                        })
                        .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.deleteBooks(book.getId());
                                startActivity(new Intent(context,BookMark.class));
                            }
                        }).setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(context,AddBook.class);
                        intent.putExtra("id",String.valueOf(book.getId()));
                        startActivity(intent);
                    }
                }).show();

            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.bookmark);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.novel:
                    startActivity(new Intent(getApplicationContext(),NovelActivity.class));
                    Toast.makeText(BookMark.this,"Read The Novel Stories...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.summary:
                    startActivity(new Intent(getApplicationContext(),SummaryActivity.class));
                    Toast.makeText(BookMark.this,"Read The Novel Summaries...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
                case R.id.bookmark:
                    return true;
                case R.id.audio:
                    startActivity(new Intent(getApplicationContext(),AudioActivity.class));
                    Toast.makeText(BookMark.this,"Listen to the Novel Summary ...",Toast.LENGTH_LONG).show();
                    overridePendingTransition(0,0);
                    return true;
            }
            return false; }});
    }
}