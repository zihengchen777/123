package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WifiActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_send;
    private Button bt_send;
    private TextView tv_recv;
    private Button bt_guanbi;

    private String send_buff=null;
    private String recv_buff=null;

    private Handler handler = null;

    Socket socket = null;

    int flag=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);

        initView();

        handler = new Handler();

        //单开一个线程来进行socket通信
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket("192.168.12.1" , 7654);
                    if (socket!=null) {
                        System.out.println("###################");
                        while (true) {

                            //循环进行收发
                            recv();
                            if(flag==1) {
                                //  send();
                            } else if(flag==2) {//这里没有问题
                                // guanbi();
                            }

                            flag=-1;
                            //发送关闭信息，如果错误直接删除
                            Thread.sleep(50);
                        }
                    }
                    else
                        System.out.println("socket is null");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        /** View v = null;
         switch (v.getId()){
         case R.id.bt_send:
         send();
         break;
         case R.id.bt_guanbi:
         guanbi();
         break;

         }*/



//
//       View vi
//       ew = View.inflate()
//       onClick(view);

    }


    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.bt_send:
                flag=1;
                send();
                break;

            case R.id.bt_guanbi:
                flag=2;
                guanbi();
                break;
            default:
        }
    }

    private void recv() {

        //单开一个线程循环接收来自服务器端的消息
        InputStream inputStream = null;
        try {
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputStream!=null){
            try {
                byte[] buffer = new byte[1024];
                int count = inputStream.read(buffer);//count是传输的字节数
                recv_buff = new String(buffer);//socket通信传输的是byte类型，需要转为String类型
                System.out.println(recv_buff);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将受到的数据显示在TextView上
        if (recv_buff!=null){
            handler.post(runnableUi);

        }
    }

    //不能在子线程中刷新UI，应为textView是主线程建立的
    Runnable runnableUi = new Runnable() {
        @Override
        public void run() {
            tv_recv.append("\n"+recv_buff);
        }
    };

    private void send() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                send_buff = "11111";
                //向服务器端发送消息
                System.out.println("------------------------");
                OutputStream outputStream=null;
                try {
                    outputStream = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(outputStream!=null){
                    try {
                        outputStream.write(send_buff.getBytes());
                        System.out.println("1111111111111111111111");
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }



    //发送关闭信息
    private void guanbi(){

        new  Thread(new Runnable() {
            @Override
            public void run() {
                send_buff="12312";
                System.out.println("---------------");

                OutputStream outputStream=null;
                try {
                    outputStream = socket.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if(outputStream!=null){
                    try {
                        outputStream.write(send_buff.getBytes());
                        System.out.println("1111111111111111111111");
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private void initView() {
        et_send = (EditText) findViewById(R.id.et_send);
        et_send.setText(send_buff);
        bt_send = (Button) findViewById(R.id.bt_send);
        tv_recv = (TextView) findViewById(R.id.tv_recv);
        bt_guanbi=(Button)findViewById(R.id.bt_guanbi);

        bt_send.setOnClickListener(this);
        bt_guanbi.setOnClickListener(this);
    }
}