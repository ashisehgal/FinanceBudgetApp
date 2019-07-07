package com.example.budgetapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText etUsername;
    EditText etPassword;
    EditText etConfirm;
    Button etButton;
    TextView etRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirm = (EditText) findViewById(R.id.etConfirm);
        etButton = (Button) findViewById(R.id.etButton);
        etRegister = (TextView) findViewById(R.id.etRegister);
        etRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent LoginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        etButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etUsername.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                String cnf_pwd = etConfirm.getText().toString().trim();

                if(pwd.equals(cnf_pwd))
                {
                    long val = db.addUser(user,pwd);
                    if(val>0)
                    {
                        Toast.makeText(RegisterActivity.this,"You have registered",Toast.LENGTH_LONG).show();
                        Intent movetoMain = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(movetoMain);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}



