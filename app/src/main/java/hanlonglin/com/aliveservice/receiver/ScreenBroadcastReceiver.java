package hanlonglin.com.aliveservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenBroadcastReceiver extends BroadcastReceiver {

    ScreenListener.ScreenStateListener mScreenStateListener;

    public void setmScreenStateListener(ScreenListener.ScreenStateListener mScreenStateListener) {
        this.mScreenStateListener = mScreenStateListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            //锁屏
            mScreenStateListener.onScreenOff();
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) {
            //开屏
            mScreenStateListener.onScreenOn();
        } else if(Intent.ACTION_USER_PRESENT.equals(action)){
            //开始
            mScreenStateListener.onScreenPresent();
        }
    }
}
