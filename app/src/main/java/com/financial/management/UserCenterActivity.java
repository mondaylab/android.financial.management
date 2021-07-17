package com.financial.management;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

//用户个人中心页面逻辑
public class UserCenterActivity extends AppCompatActivity {
    ArrayList<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);

        Intent intent=getIntent();
        list =intent.getParcelableArrayListExtra("LoginUser");
        User user=list.get(0);
        final String username=user.getUserId();
        TextView tv_welcome=findViewById(R.id.tv_welcome);
        tv_welcome.setText("欢迎您 ,用户"+username);

        //收支管理
        ImageView btn_recordmanage =findViewById(R.id.btn_recordmanage);
        btn_recordmanage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(), ManageActivity.class);
                startActivity(intent1);
            }
        });
        //收支查询
        ImageView btn_searchrecord=findViewById(R.id.btn_searchrecord);
        btn_searchrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(getApplicationContext(),SearchRecordActivity.class);
                startActivity(intent2);
            }
        });
        //收支统计
        ImageView btn_calcmoney=findViewById(R.id.btn_calcmoney);
        btn_calcmoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(getApplicationContext(),SearchRecordActivity.class);
                startActivity(intent3);
            }
        });
        //退出按键
        ImageView btn_exit=findViewById(R.id.btn_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(UserCenterActivity.this).setTitle("退出操作")
                        .setMessage("确定退出，不继续玩玩？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            }
                        }).setNegativeButton("继续留下！", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).create();
                dialog.show();

            }
        });
    }
}
