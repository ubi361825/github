package com.example.im.pigeonapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    DataBaseHelper helper = new DataBaseHelper(this);
    String job="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner classes = (Spinner) findViewById(R.id.classes);
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, new String[]{"種鴿場", "作出者", "教練", "鴿友"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classes.setAdapter(adapter);
        classes.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            public void onItemSelected(AdapterView adapterView, View view, int position, long id) {
               job=adapter.getItem(position).toString();
            }

            public void onNothingSelected(AdapterView arg0) {
                Toast.makeText(Register.this, "您沒有選擇任何項目", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void SignUp(View v) {
        if (v.getId() == R.id.cfmbtn) {
            EditText account = (EditText) findViewById(R.id.accinput);
            EditText pass1 = (EditText) findViewById(R.id.pwdinput);
            EditText pass2 = (EditText) findViewById(R.id.cfminput);
            EditText nickname = (EditText) findViewById(R.id.nnameinput);
            EditText realname = (EditText) findViewById(R.id.rnameinput);
            EditText phonenum = (EditText) findViewById(R.id.pnuminput);
            EditText address = (EditText) findViewById(R.id.addinput);
            EditText email = (EditText) findViewById(R.id.emailinput);
            RadioGroup gender = (RadioGroup) findViewById(R.id.gendergrp);
            EditText birthday = (EditText) findViewById(R.id.bdayinput);


            String sex = "男";
            switch (gender.getCheckedRadioButtonId()) {
                case R.id.rmale:
                    sex = "男";

                    break;
                case R.id.rfemale:
                    sex = "女";
                    break;
            }//進度：spinner取值未作


            String accstr = account.getText().toString();
            String pwd1 = pass1.getText().toString();
            String pwd2 = pass2.getText().toString();
            String nname = nickname.getText().toString();
            String rname = realname.getText().toString();
            String pn = phonenum.getText().toString();
            String add = address.getText().toString();
            String mail = email.getText().toString();

            String bday = birthday.getText().toString();

            if(!pwd1.equals(pwd2)){
                Toast.makeText(Register.this, "密碼不相符", Toast.LENGTH_SHORT).show();
            }else{
                contact c = new contact();
                c.setAccount(accstr);
                c.setPassword(pwd1);
                c.setNickname(nname);
                c.setName(rname);
                c.setPhone(pn);
                c.setAddress(add);
                c.setEmail(mail);
                c.setGender(sex);
                c.setBdate(bday);
                c.setStatus(job);
                helper.insertContact(c);
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
            }

        }

    }
}
