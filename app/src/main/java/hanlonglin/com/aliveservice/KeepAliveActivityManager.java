package hanlonglin.com.aliveservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import java.lang.ref.WeakReference;

public class KeepAliveActivityManager {
    private static KeepAliveActivityManager instance;
    private Context mContext;
    private WeakReference<Activity> activityWeakReference;

    public KeepAliveActivityManager(Context context) {
        mContext = context;
    }

    public static KeepAliveActivityManager getInstance(Context context) {
        if (instance == null)
            instance = new KeepAliveActivityManager(context.getApplicationContext());
        return instance;
    }

    public void setKeepAliveActivity(Activity keepAliveActivity) {
        this.activityWeakReference = new WeakReference<Activity>(keepAliveActivity);
    }

    public void startKeepAliveActivty() {
        Intent intent = new Intent(mContext, KeepAliveActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public void finishKeepAliveActivity() {
        Activity activity = activityWeakReference.get();
        if (activity != null)
            activity.finish();
    }
}
