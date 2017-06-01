package com.example.ganesh.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;


/**
 * Created by kvana on 5/30/17.
 */

public class MyService extends Service {
    private IBinder mBidnder = new LocalBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBidnder;
    }

    public class LocalBinder extends Binder{

        public MyService getService()
        {
            return MyService.this;
        }

    }

    public int findFactorial(int x){
       int fact =1;
        for (int i = 1; i <= x ; i++) {
            fact = fact * i;
        }
        return fact;
    }
}
