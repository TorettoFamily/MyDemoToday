package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button signup,signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB =new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(TextUtils.isEmpty(user)|| TextUtils.isEmpty(pass))
                    Toast.makeText(MainActivity.this,"todas letras requeridas",Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = DB.checkusername(user);
                    if(checkuser==false){
                        Boolean insert = DB.insertData(user,pass);
                        if(insert==true){
                            Toast.makeText(MainActivity.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),Mapa.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this,"Registro fallido",Toast.LENGTH_SHORT).show();
                        }
                    }else{Toast.makeText(MainActivity.this,"Usuario existente",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}