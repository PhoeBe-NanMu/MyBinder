package com.example.mybinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by LeiYang on 2016/10/3.
 */

public class AIDLService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        /*
         *IBinder 好比是一个遥控器，
         * 如果需要方便地控制 Service 内部运作，就造个遥控器返回，
         * 上面放几个方法（相当于按钮），大都数情况下都用不到，所以返回 null。
         */
        return myBinder;
    }

    private IBinder myBinder =  new PersonQueryBinder();

    private final class PersonQueryBinder extends IPerson.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public String queryPerson(int num) throws RemoteException {
            return query(num);
        }
    }



    private String[] names = {"张三","李四","王五","李峰","李珊"};
    private String query(int num) {

        if (num > 0 && num < 6) {
            return names[num-1];
        } else {
            return "无此信息";
        }
    }
}
