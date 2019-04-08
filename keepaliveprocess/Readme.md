# 双进程守护Service

## 核心思想：
* 在app中开启两个进程的service, 进行互相绑定；通信方式采用aidl；当其中一个service监听到连接断开的时候，启动对方；

## 模块
>service
>>LocalService 本地服务，和app在同一进程
>>RemoteSerive 远程服务，在另一个进程中运行

>MainActivity 负责开始启动两个service


  