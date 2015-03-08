package com.example.administrator.iot;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.os.AsyncTask;

import java.io.OutputStream;
import java.net.Socket;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.UnknownHostException;

public class MainActivity extends ActionBarActivity {



    OutputStream os;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onCPOnClicked(View v) throws Exception {

        Toast.makeText(getApplicationContext(), "시작", Toast.LENGTH_SHORT).show();

       NetworkTask nt = new NetworkTask("52.11.52.152",9000,0);
        nt.execute();

        Toast.makeText(getApplicationContext(), "컴퓨터가 켜졌습니다!", Toast.LENGTH_SHORT).show();
    }
    public void onCPOffClicked(View v){

        NetworkTask nt = new NetworkTask("52.11.52.152",9000,1);
        nt.execute();
        Toast.makeText(getApplicationContext(), "컴퓨터가 꺼졌습니다!", Toast.LENGTH_SHORT).show();
    }
    public void onLPOnClicked(View v){

        NetworkTask nt = new NetworkTask("52.11.52.152",9000,2);
        nt.execute();
        Toast.makeText(getApplicationContext(), "전등이 켜졌습니다!", Toast.LENGTH_SHORT).show();
    }
    public void onLPOffClicked(View v){

        NetworkTask nt = new NetworkTask("52.11.52.152",9000,3);
        nt.execute();
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

    public class NetworkTask extends AsyncTask<Void, Void, Void> {

        String dstAddress;
        int dstPort;

        int dstcmd;

        NetworkTask(String addr, int port, int cmd) {
            dstAddress = addr;
            dstPort = port;
           dstcmd = cmd;
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            try {
                Socket socket = new Socket(dstAddress, dstPort);
                OutputStream os = socket.getOutputStream();
                byte[] buffer = new byte[1024];

                if( dstcmd == 0)
                {
                    buffer = "p1".getBytes();
                    os.write(buffer);
                    buffer = "exit".getBytes();
                    os.write(buffer);

                }
                else if ( dstcmd == 1)
                {
                    buffer = "p0".getBytes();
                    os.write(buffer);
                    buffer = "exit".getBytes();
                    os.write(buffer);
                }
                else if ( dstcmd == 2)
                {
                    buffer = "l1".getBytes();
                    os.write(buffer);
                    buffer = "exit".getBytes();
                    os.write(buffer);
                }
                else
                {
                    buffer = "l0".getBytes();
                    os.write(buffer);
                    buffer = "exit\0".getBytes();
                    os.write(buffer);
                }

                socket.close();


            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            super.onPostExecute(result);
        }

    }
}

