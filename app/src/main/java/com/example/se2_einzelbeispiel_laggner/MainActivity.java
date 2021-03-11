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
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText input = findViewById(R.id.matrInput);
        TextView serverRes = findViewById(R.id.serverResponse);
        Button btn1 = (Button) findViewById(R.id.abschicken);
        Button btn2 = (Button) findViewById(R.id.buttonGemeinsamerNenner);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Socket input
                PrimeRun p = new PrimeRun(serverRes, input);
                p.start();

                serverRes.setText(p.getServerRes().getText().toString());

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            //Teil 2. 01627809 % 7 =1
            //Berechnung eines gemeinsamen Nenners zweier Ziffern
            @Override
            public void onClick(View v) {
                ArrayList<Integer> Indexlist = new ArrayList<Integer>();

                String[] matrNrArray = input.getText().toString().split("");

                //Prüfe, ob es sich um eine 8 stellige Zahl handelt. Sonst -> keine Matr.Nr.
                if (matrNrArray.length == 8) {
                    int[] matrNrArrayInt = new int[matrNrArray.length];

                    //String zu Int Array
                    for (int i = 0; i < matrNrArray.length; i++) {
                        matrNrArrayInt[i] = Integer.parseInt(matrNrArray[i]);
                    }


                    //I wird als Teiler benutzt: Da nur einzelne Ziffern betrachtet werden, ist der höchstmögliche
                    //Teiler 9
                    //Schleife wird abgebrochen, wenn Die Liste von Indizes bereits ausreichend bei einem früheren Durchlauf
                    //befüllt wurde; sollte dies nicht der Fall sein wird sie in jedem Durchlauf neu instanziert

                    for (int i = 2; i < 10; i++) {
                        Indexlist = new ArrayList<Integer>();
                        //Sämtliche Ziffern werden durchlaufen
                        for (int j = 0; j < matrNrArrayInt.length; j++) {
                            if (matrNrArrayInt[j] % i == 0) {
                                Indexlist.add(j);
                            }
                            if (Indexlist.size() > 1) {
                                i = 10;
                            }
                        }
                    }
                    if (Indexlist.size() < 2) {
                        serverRes.setText("Kein gemeinsamer Teiler (>1) vorhanden.");
                    } else {
                        serverRes.setText("Mindestens 2 Ziffern mit gemeinsamen Teiler gefunden: \nIndex 1 = " + Indexlist.get(0) + "\nIndex 2 = " + Indexlist.get(1));
                    }
                } else {
                    serverRes.setText("Keine gültige Matrikelnummer.");
                }
            }
        });
    }
}
