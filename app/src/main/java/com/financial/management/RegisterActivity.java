package com.financial.management;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

//注册页面逻辑
public class RegisterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        final EditText edt_rid =findViewById(R.id.edt_rid);
        final EditText edt_rpwd =findViewById(R.id.edt_rpwd);
        //注册按键
        Button btn_registerf=findViewById(R.id.btn_registeruser);
        btn_registerf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setUserId(edt_rid.getText().toString());
                user.setUserPwd(edt_rpwd.getText().toString());

                DBHelper dbUserHelper = new DBHelper(getApplicationContext());
                if (dbUserHelper.registerUser(user) > 0) {
                    Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent;
                    ArrayList<User> list = new ArrayList<>();
                    list.add(user);

                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(), "您已经注册过此账户，请返回登录", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
