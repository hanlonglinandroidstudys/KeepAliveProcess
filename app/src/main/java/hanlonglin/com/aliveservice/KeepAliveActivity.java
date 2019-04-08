package hanlonglin.com.aliveservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class KeepAliveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_keep_alive);

        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.height = 1;
        attributes.width = 1;
        attributes.x=0;
        attributes.y=0;
        window.setAttributes(attributes);

        Log.e("KeepAliveActivity","开启看不见的KeepAliveActivity");

        KeepAliveActivityManager.getInstance(this).setKeepAliveActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("KeepAliveActivity","onDestroy关闭看不见的activity ");
    }
}
