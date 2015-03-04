package com.example.administrator.iot;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
import java.net.Socket;


public class MainActivity extends ActionBarActivity {

    private Socket socket;
    private BufferedReader networkReader;
    private BufferedWriter networkWriter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /*public void setSocket(String ip, int port) throws IOException {
        try {
            socket = new Socket(ip, port);
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }*/

    public void onCPOnClicked(View v){
        try {
            Socket socket = new Socket("52.11.52.152", 9000);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        //try {
         //   OutputStream os = socket.getOutputStream();
            //OutputStreamWriter osw = new OutputStreamWriter(os);
           // BufferedWriter bw = new BufferedWriter(osw);

            //String str = "p1";

           // while(true)
           //     bw.write(str, 0, 2);

       // } catch (IOException e) {
       //     e.printStackTrace();
      //  }

        Toast.makeText(getApplicationContext(), "컴퓨터가 켜졌습니다!", Toast.LENGTH_SHORT).show();
    }
    public void onCPOffClicked(View v){

        Toast.makeText(getApplicationContext(), "컴퓨터가 꺼졌습니다!", Toast.LENGTH_SHORT).show();
    }
    public void onLPOnClicked(View v){

        Toast.makeText(getApplicationContext(), "전등이 켜졌습니다!", Toast.LENGTH_SHORT).show();
    }
    public void onLPOffClicked(View v){

        Toast.makeText(getApplicationContext(), "전등이 꺼졌습니다!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
