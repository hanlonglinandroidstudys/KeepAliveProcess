package hanlonglin.com.keepaliveprocess.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import hanlonglin.com.keepaliveprocess.RemoteConnection;

public class RemoteService extends Service {
    RemoteService.MyBinder myBinder = null;
    RemoteService.mServiceConnection serviceConnection = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (myBinder == null)
            myBinder = new MyBinder();
        if (serviceConnection == null)
            serviceConnection = new RemoteService.mServiceConnection();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //绑定
        RemoteService.this.bindService(new Intent(RemoteService.this,LocalService.class),serviceConnection, Context.BIND_IMPORTANT);

        return super.onStartCommand(intent, flags, startId);
    }

    class MyBinder extends RemoteConnection.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }
    }

    class mServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("RemoteService", "RemoteService 绑定 LocalService 成功！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("RemoteService", "RemoteService 断开 LocalService ！");
            //重新启动和绑定
            RemoteService.this.startService(new Intent(RemoteService.this,LocalService.class));
            RemoteService.this.bindService(new Intent(RemoteService.this,LocalService.class), serviceConnection,Context.BIND_IMPORTANT);
        }
    }
}
