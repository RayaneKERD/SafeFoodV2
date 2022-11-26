package com.example.safefood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView identifiant = (TextView) findViewById(R.id.username);
        TextView motdepasse = (TextView) findViewById(R.id.password);

        MaterialButton connectionbtn = (MaterialButton) findViewById(R.id.connectionbtn);


        // Creation d'un compte ADMIN ADMIN

        connectionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                if (identifiant.getText().toString().equals("admin") && motdepasse.getText().toString().equals("admin")){
                    ;
                    // TOUT EST OK
                    Toast.makeText(LoginActivity.this, "Connection réussie", Toast.LENGTH_SHORT).show();

                    startActivity(i);
                }else
                    // INCORRECT
                    Toast.makeText(LoginActivity.this, "Identifant ou Mot de passe incorrect", Toast.LENGTH_SHORT).show();
            }
        });



    }

}