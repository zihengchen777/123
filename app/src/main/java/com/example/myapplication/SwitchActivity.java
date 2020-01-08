package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class SwitchActivity extends AppCompatActivity {
    private ImageView iv_return_switch;
    private TextView tv_connect_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        iv_return_switch=(ImageView)findViewById(R.id.iv_return_switch);
        tv_connect_switch=(TextView)findViewById(R.id.tv_connect_switch);

        iv_return_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        tv_connect_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SwitchActivity.this,ConnectActivity.class);
                startActivity(intent);
            }
        });



    }



}
