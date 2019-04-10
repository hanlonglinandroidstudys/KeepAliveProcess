# 双进程守护Service

## 核心思想：
* 在app中开启两个进程的service, 进行互相绑定；通信方式采用aidl；当其中一个service监听到连接断开的时候，启动对方；

## 模块
>service
>>LocalService 本地服务，和app在同一进程
>>RemoteSerive 远程服务，在另一个进程中运行

>MainActivity 负责开始启动两个service

* * 上诉方法可以在成第三方软件清理时（比如360）等，防止后台service被清理掉，因为其杀进程是一个个杀的，杀进程的速度一般没有我们启动的快！
但是如果在设置里强制停止整个app，那么就没办法了，我们的两个服务都会被杀掉。

* * 解决办法很多，JobSchedule，AlarmManager 等， 这里使用的是JobSchedule,在合适的时机，定时启动任务，检查服务是否开启，如果没有则开启；
具体实现见:
>> JobHandleService


  