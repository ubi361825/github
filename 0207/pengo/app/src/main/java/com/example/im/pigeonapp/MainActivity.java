package com.example.im.pigeonapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    DataBaseHelper helper = new DataBaseHelper(this);
    StringRequest sr;
    RequestQueue rq;
    String showurl = "http://192.168.1.102/pigeon/users.php";
    Spinner sss;
    String acc2 = "";
    String pwd2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rq = Volley.newRequestQueue(this);
        sss = (Spinner) findViewById(R.id.s1);
    }

    public void RegisterActivity(View v){
        Intent it = new Intent(this, Register.class);

        startActivity(it);
    }

    public void loginA(View v){
        EditText acc1 = (EditText)findViewById(R.id.acc);
        EditText pwd1 = (EditText)findViewById(R.id.pwd);
        acc2 = acc1.getText().toString();
        pwd2 = pwd1.getText().toString();
        String passchk = helper.searchPass(acc2);

        String[] ssss = getResources().getStringArray(R.array.sql);
        int ccc = sss.getSelectedItemPosition();

        if(ccc == 0) {
            sr = new StringRequest(Request.Method.POST, showurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {

                        JSONObject jsonObject = new JSONObject(response);
                        if(jsonObject.names().get(0).equals("success")){
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }else{
                            Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError{
                    HashMap<String, String> hashMap = new HashMap<String, String>();
                    hashMap.put("account", acc2);
                    hashMap.put("password", pwd2);
                    return hashMap;
                }
            };
            rq.add(sr);

        }else if(ccc == 1){
            if(pwd2.equals(passchk)){
                Intent i = new Intent(this, Login.class);
                i.putExtra("Username", acc2);
                startActivity(i);
            }else{
                Toast.makeText(MainActivity.this, "密碼不相符", Toast.LENGTH_SHORT).show();
            }
        }



    }
}