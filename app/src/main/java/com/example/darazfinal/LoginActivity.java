package com.example.darazfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.darazfinal.bll.LoginBll;
import com.example.darazfinal.strictmode.StrictModeClass;

public class LoginActivity extends AppCompatActivity {

    Button btnExit,btnMain;
    EditText etNumber,etPassword;
    TextView tvAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        btnExit=findViewById(R.id.btnExit);
        btnMain=findViewById(R.id.btnMain);
        etNumber=findViewById(R.id.etNumber);
        etPassword=findViewById(R.id.etPassword);
        tvAccount=findViewById(R.id.tvAccount);

        tvAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,NavigationActivity.class);
                startActivity(intent);
            }
        });

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = etNumber.getText().toString();
        String password = etPassword.getText().toString();

        LoginBll loginBLL = new LoginBll();

        StrictModeClass.StrictMode();
        if (loginBLL.checkUser(username, password)) {
            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etNumber.requestFocus();
        }
    }
}

