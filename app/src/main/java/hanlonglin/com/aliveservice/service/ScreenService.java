package hanlonglin.com.aliveservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import hanlonglin.com.aliveservice.KeepAliveActivityManager;
import hanlonglin.com.aliveservice.receiver.ScreenListener;

public class ScreenService extends Service {
    ScreenListener mScreenListener;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("ScreenService","服务被启动");
        mScreenListener=new ScreenListener(this);
        mScreenListener.begin(new ScreenListener.ScreenStateListener() {
            @Override
            public void onScreenOn() {
                Log.e("ScreenService","屏幕开启onScreenOn()");
                KeepAliveActivityManager.getInstance(ScreenService.this).finishKeepAliveActivity();
            }

            @Override
            public void onScreenOff() {
                Log.e("ScreenService","屏幕关闭onScreenOff()");
                KeepAliveActivityManager.getInstance(ScreenService.this).startKeepAliveActivty();
            }

            @Override
            public void onScreenPresent() {
                Log.e("ScreenService","屏幕用户打开onScreenPresent()");
                KeepAliveActivityManager.getInstance(ScreenService.this).finishKeepAliveActivity();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mScreenListener.unRegsiterReceiver();
        Log.e("ScreenService","服务被销毁");

    }
}
