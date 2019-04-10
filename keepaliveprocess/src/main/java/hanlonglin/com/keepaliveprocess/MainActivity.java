package hanlonglin.com.keepaliveprocess;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RemoteViewsService;

import hanlonglin.com.keepaliveprocess.service.JobHandleService;
import hanlonglin.com.keepaliveprocess.service.LocalService;
import hanlonglin.com.keepaliveprocess.service.RemoteService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //启动两个服务
        startService(new Intent(MainActivity.this, LocalService.class));
        startService(new Intent(MainActivity.this, RemoteService.class));

        startService(new Intent(MainActivity.this, JobHandleService.class));
    }
}
