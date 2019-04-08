# 添加看不见的KeepAliveActivity 
* 开启一个service,注册广播，用于监听屏幕的开启和关闭；必须动态注册才能监听到，静态注册不能收到广播；
* 屏幕息屏时开启KeepAliveActivity  屏幕亮时关闭KeepAliveActivity;

