package hanlonglin.com.keepaliveprocess.service;

import android.app.ActivityManager;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class JobHandleService extends JobService {

    int kjobid = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("JobHandleService", "onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("JobHandleService", "onStartCommand()");
        scheduleJob(getJobInfo());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("JobHandleService", "onDestroy()");
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.e("JobHandleService", "onStartJob()");
        boolean isLocalServiceWork=isServiceWork(this,LocalService.class.getName());
        boolean isRemoteServiceWork=isServiceWork(this,RemoteService.class.getName());
        if(!isLocalServiceWork||!isRemoteServiceWork){
            //启动两个服务
            startService(new Intent(this,LocalService.class));
            startService(new Intent(this,RemoteService.class));
            Toast.makeText(this, "onStartJob启动两个服务", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.e("JobHandleService", "onStopJob()");
        scheduleJob(getJobInfo());
        return false;
    }


    /**
     * 构建jobInfo
     *
     * @return
     */
    private JobInfo getJobInfo() {
        JobInfo.Builder builder = new JobInfo.Builder(kjobid++, new ComponentName(this, JobHandleService.class));
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPersisted(true);  //重启之后生效
        //builder.setRequiresCharging(true);
        builder.setPeriodic(10); //周期 间隔时间
        return builder.build();
    }

    /**
     * 判断服务是否在运行
     *
     * @param context
     * @param mServiceName
     * @return
     */
    private boolean isServiceWork(Context context, String mServiceName) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(100);
        for(int i=0;i<runningServices.size();i++){
            String serviceName = runningServices.get(i).service.getClassName().toString();
            if(serviceName.equals(mServiceName))
                return true;
        }
        return false;
    }

    /**
     * 执行job
     *
     * @param jobInfo
     */
    private void scheduleJob(JobInfo jobInfo) {
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(jobInfo);
    }


}
