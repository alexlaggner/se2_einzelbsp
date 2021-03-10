package com.example.se2_einzelbeispiel_laggner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.matrInput);
        TextView serverRes= findViewById(R.id.serverResponse);
        Button btn1 = (Button) findViewById(R.id.abschicken);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Socket input
                PrimeRun p = new PrimeRun(serverRes,input);
                p.start();

                serverRes.setText(p.getServerRes().getText().toString());

            }
        });

    }


}
