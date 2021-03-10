package com.example.se2_einzelbeispiel_laggner;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class PrimeRun extends Thread {
    private EditText input;
    private TextView serverRes;
    public PrimeRun(TextView serverRes, EditText input){
        this.input=input;
        this.serverRes=serverRes;
    }
@Override
    public void run(){
    try {
        Socket clientSocket = new Socket("se2-isys.aau.at",53212);
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outToServer.writeBytes(this.input.getText().toString()+"\n");
        this.serverRes.setText(inFromServer.readLine());
        clientSocket.close();


    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public TextView getServerRes(){
        return this.serverRes;
    }
    public EditText getInput(){
        return this.input;
    }
}
