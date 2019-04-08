package hanlonglin.com.aliveservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hanlonglin.com.aliveservice.service.ScreenService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_goto=(Button)findViewById(R.id.btn_go);
        btn_goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,KeepAliveActivity.class));
                finish();
            }
        });

        //开启service监听
        startService(new Intent(MainActivity.this, ScreenService.class));
    }
}
