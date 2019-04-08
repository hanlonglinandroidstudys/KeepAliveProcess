package hanlonglin.com.aliveservice.receiver;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.ScrollView;

public class ScreenListener {

    Context mContext;
    ScreenBroadcastReceiver mScreenBroadcastReceiver;
    ScreenStateListener mScreenStateListener;

    public ScreenListener(Context context) {
        this.mContext = context;
        mScreenBroadcastReceiver = new ScreenBroadcastReceiver();
    }

    public void begin(ScreenStateListener screenStateListener) {
        this.mScreenStateListener = screenStateListener;
        mScreenBroadcastReceiver.setmScreenStateListener(screenStateListener);
        registerReceiver();
        getScreenState();
    }

    private void getScreenState() {

    }

    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        mContext.registerReceiver(mScreenBroadcastReceiver, intentFilter);
    }

    public void unRegsiterReceiver(){
        mContext.unregisterReceiver(mScreenBroadcastReceiver);
    }


    public interface ScreenStateListener {
        void onScreenOn();

        void onScreenOff();

        void onScreenPresent();
    }

}
