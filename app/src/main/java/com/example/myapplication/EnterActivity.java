package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dao.UserDao;
import com.example.myapplication.entity.User;


public class EnterActivity extends AppCompatActivity {
    private EditText editTextA1;
    private EditText editTextA2;
    private Button buttonA1;
    private UserDao userDao;
    private TextView tv_register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_enter);



        userDao = new UserDao(this);
        editTextA1 = (EditText) findViewById(R.id.et_username);
        editTextA2 = (EditText) findViewById(R.id.et_password);

        buttonA1 = (Button) findViewById(R.id.bn_common_login);
        tv_register=(TextView)findViewById(R.id.tv_register);

        // 用户登录
        buttonA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = editTextA1.getText() + "";
                String upwd = editTextA2.getText() + "";
//一个是输入不能为空，一个是不能只打空格null
                if (uname.equals(null) || uname == "" || upwd.equals(null) || upwd == "") {
                    Toast.makeText(EnterActivity.this, "用户名或密码不得为空！", Toast.LENGTH_SHORT).show();
                } else {
                    User user = userDao.dbQueryOneByUsername(uname);
                    if (userDao.dbQueryOneByUsername(uname) == null) {
                        Toast.makeText(EnterActivity.this, "此用户不存在！", Toast.LENGTH_SHORT).show();
                    } else {
                        if (!user.getUpwd().equals(upwd)) {
                            Toast.makeText(EnterActivity.this, "密码错误！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(EnterActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EnterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EnterActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });





    }
}
