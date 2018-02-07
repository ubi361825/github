package com.example.im.pigeonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void RegisterActivity(View v){
        Intent it = new Intent(this, Register.class);

        startActivity(it);
    }

    public void loginA(View v){
        EditText acc1 = (EditText)findViewById(R.id.acc);
        EditText pwd1 = (EditText)findViewById(R.id.pwd);
        String acc2 = acc1.getText().toString();
        String pwd2 = pwd1.getText().toString();
        String passchk = helper.searchPass(acc2);

        if(pwd2.equals(passchk)){
            Intent i = new Intent(this, Login.class);
            i.putExtra("Username", acc2);
            startActivity(i);
        }else{
            Toast.makeText(MainActivity.this, "密碼不相符", Toast.LENGTH_SHORT).show();
        }

    }
}
