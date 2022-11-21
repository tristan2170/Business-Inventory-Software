package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username_Box);
        password = (EditText) findViewById(R.id.password_Box);
        btnlogin = (Button) findViewById(R.id.buttonLogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(btnlogin);
            }
        });

    }

    public void login(View view) {
        if (username.getText().toString().equals("") || password.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "Please Enter a Username and Password",
                    Toast.LENGTH_SHORT).show();
        }
        else if (username.getText().toString().equals("admin") &&
                password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Redirecting...",
                    Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, AdminHome.class);
            startActivity(i);
        }
        else if (username.getText().toString().equals("customer") &&
                password.getText().toString().equals("customer")) {
            Toast.makeText(getApplicationContext(), "Redirecting...",
                    Toast.LENGTH_SHORT).show();
            Intent j = new Intent(LoginActivity.this, CustHome.class);
            startActivity(j);
        }
        else if (username.getText().toString()!= "admin" && password.getText().toString() != "admin") {
            Toast.makeText(getApplicationContext(), "Invalid Username or Password",
                    Toast.LENGTH_SHORT).show();
        }
        else if (username.getText().toString()!= "customer" && password.getText().toString() != "customer") {
            Toast.makeText(getApplicationContext(), "Invalid Username or Password",
                    Toast.LENGTH_SHORT).show();
        }

    }

}