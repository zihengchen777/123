package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView connect;
    private TextView about;
    private TextView mine;
    private TextView switch_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connect=(TextView)findViewById(R.id.connect);
        about=(TextView)findViewById(R.id.about);
        mine=(TextView)findViewById(R.id.mine);
        switch_1=(TextView)findViewById(R.id.switch_1);

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WifiActivity.class);
                startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });

        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,MineActivity.class);
                startActivity(intent);
            }
        });
        switch_1.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SwitchActivity.class);
                startActivity(intent);
            }
        });

    }
}
