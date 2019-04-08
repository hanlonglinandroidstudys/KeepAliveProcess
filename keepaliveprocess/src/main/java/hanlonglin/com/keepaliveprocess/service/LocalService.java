package hanlonglin.com.keepaliveprocess.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import hanlonglin.com.keepaliveprocess.MainActivity;
import hanlonglin.com.keepaliveprocess.R;
import hanlonglin.com.keepaliveprocess.RemoteConnection;

/**
 * 和RemoteService进行绑定
 * 监听 如果服务断开 重新启动RemoteService 重新绑定
 */

public class LocalService extends Service {
    MyBinder myBinder = null;
    mServiceConnection serviceConnection = null;

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
            serviceConnection = new mServiceConnection();

        //开启前台服务
        Log.e("LocalService","开启前台服务");
        startForeground(1,getNotification());
    }

    private Notification getNotification() {
        Notification.Builder builder=new Notification.Builder(this);
        builder.setContentText("I am localService");
        builder.setAutoCancel(false);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setTicker("localService is running");
        builder.setContentTitle("this is title");
        Intent intent=new Intent(LocalService.this, MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,0);
        builder.setContentIntent(pendingIntent);
        return builder.getNotification();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //绑定
        LocalService.this.bindService(new Intent(LocalService.this,RemoteService.class),serviceConnection,Context.BIND_IMPORTANT);

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
            Log.e("LocalService", "LocalService 绑定 RemoteService 成功！");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("LocalService", "LocalService 断开 RemoteService ！");
            //重新启动和绑定
            LocalService.this.startService(new Intent(LocalService.this,RemoteService.class));
            LocalService.this.bindService(new Intent(LocalService.this,RemoteService.class), serviceConnection,Context.BIND_IMPORTANT);
        }
    }
}
