package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.dao.UserDao;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextB1;
    private EditText editTextB2;
    private EditText editTextB3;
    private Button buttonB1;
    private TextView tv_enter;
    private UserDao userDao;
    private ImageView iv_return_2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);



        userDao = new UserDao(this);
        editTextB1 = (EditText) findViewById(R.id.et_phone_num);
        editTextB2 = (EditText) findViewById(R.id.et_password_zhuce);
        editTextB3 = (EditText) findViewById(R.id.et_password_again);

        buttonB1 = (Button) findViewById(R.id.buttonB1);
        tv_enter=(TextView)findViewById(R.id.tv_enter);
        iv_return_2=(ImageView)findViewById(R.id.iv_return_2);


        buttonB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname = editTextB1.getText() + "";
                String upwd = editTextB2.getText() + "";
                if (uname.equals(null) || uname == "" || upwd.equals(null) || upwd == ""){
                    Toast.makeText(RegisterActivity.this,"用户名或密码不得为空！", Toast.LENGTH_SHORT).show();
                }else {
                    String confirmPwd = editTextB3.getText()+ "";
                    if (!upwd.equals(confirmPwd)){
                        Toast.makeText(RegisterActivity.this,"两次输入密码不一致", Toast.LENGTH_SHORT).show();
                    }else {
                        if (userDao.dbQueryOneByUsername(uname) == null){
                            userDao.dbInsert(uname,upwd);
                            Toast.makeText(RegisterActivity.this,"注册成功！用户名："+uname+
                                    ",密码："+upwd+",请牢记！", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent intent = new Intent(RegisterActivity.this,EnterActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(RegisterActivity.this,"该用户已被注册", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

        tv_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterActivity.this,EnterActivity.class);
                startActivity(intent);
            }
        });

        iv_return_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });


    }
}
