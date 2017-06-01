package com.example.ganesh.bindservice;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyService myService;
    boolean status;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.et_number);
    }

    public void  bindMethod(View v){
        Intent i = new Intent(this,MyService.class);
        bindService(i,sc, Context.BIND_AUTO_CREATE);
        status = true;
        Toast.makeText(getBaseContext(), "Bind Service Successfully", Toast.LENGTH_SHORT).show();
    }

    public void unbindMethod(View v){
        if(status){
            unbindService(sc);
            Toast.makeText(getBaseContext(), "UnBind Service Successfully", Toast.LENGTH_SHORT).show();
            status = false;
        }else
        {
            Toast.makeText(getBaseContext(), "Already UnBind Service Successfully", Toast.LENGTH_SHORT).show();
        }

    }

    public void factorialMethod(View v)
    {
        if(status){
            int num = Integer.parseInt(editText.getText().toString());
            int result = myService.findFactorial(num);
            Toast.makeText(getBaseContext(), "Factorial = "+result, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getBaseContext(), "First Bind the Service", Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.LocalBinder binder = (MyService.LocalBinder) service;
            myService = binder.getService();
            status = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

}
