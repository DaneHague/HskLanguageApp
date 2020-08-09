package com.dane.hsklanguageapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnHsk1 = findViewById(R.id.hsk1);
        Button btnHsk2 = findViewById(R.id.hsk2);
        Button btnHsk3 = findViewById(R.id.hsk3);
        Button btnHsk4 = findViewById(R.id.hsk4);
        Button btnHsk5 = findViewById(R.id.hsk5);
        Button btnHsk6 = findViewById(R.id.hsk6);
        Button btnDictionary = findViewById(R.id.btnDictionary);

        btnHsk1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHsk1();
            }
        });

        btnHsk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHsk2();
            }
        });

        btnHsk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHsk3();
            }
        });

        btnHsk4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHsk4();
            }
        });

        btnHsk5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHsk5();
            }
        });

        btnHsk6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHsk6();
            }
        });

        btnDictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String s = "你好， 我会说中文";
//                t1.speak(s,TextToSpeech.QUEUE_FLUSH, null);
                openDictionary();
            }
        });

    }

    public void openHsk1 (){
        Intent openHsk1Intent = new Intent(this, HskHanziChoiceGame.class);
        openHsk1Intent.putExtra("hsk", "hsk1.json");
        startActivity(openHsk1Intent);
    }

    public void openHsk2 (){
        Intent openHsk2Intent = new Intent(this, HskHanziChoiceGame.class);
        openHsk2Intent.putExtra("hsk", "hsk2.json");
        startActivity(openHsk2Intent);
    }

    public void openHsk3 (){
        Intent openHsk3Intent = new Intent(this, HskHanziChoiceGame.class);
        openHsk3Intent.putExtra("hsk", "hsk3.json");
        startActivity(openHsk3Intent);
    }

    public void openHsk4 (){
        Intent openHsk4Intent = new Intent(this, HskHanziChoiceGame.class);
        openHsk4Intent.putExtra("hsk", "hsk4.json");
        startActivity(openHsk4Intent);
    }

    public void openHsk5 (){
        Intent openHsk5Intent = new Intent(this, HskHanziChoiceGame.class);
        openHsk5Intent.putExtra("hsk", "hsk5.json");
        startActivity(openHsk5Intent);
    }

    public void openHsk6 (){
        Intent openHsk6Intent = new Intent(this, HskHanziChoiceGame.class);
        openHsk6Intent.putExtra("hsk", "hsk6.json");
        startActivity(openHsk6Intent);
    }

    public void openDictionary(){
        Intent openDictionary = new Intent(this, DictionaryActivity.class);
        startActivity(openDictionary);
    }

}