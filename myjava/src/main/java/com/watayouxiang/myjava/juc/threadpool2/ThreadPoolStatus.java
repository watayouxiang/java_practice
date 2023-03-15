package com.watayouxiang.myjava.juc.threadpool2;

/**
 线程池状态：

 RUNNING        接受新任务并处理排队任务
 SHUTDOWN       不接受新任务，但处理排队任务
 STOP           不接受新任务，也不处理排队任务，并中断正在运行的任务
 TIDYING        中文是整洁的意思，理解了中文就很容易理解这个状态了：所有任务都已终止，workerCount为零时，
                线程会转换到TIDYING状态，并将运行terminate()钩子方法。
 TERMINATED     terminate()运行完成



 */
public class ThreadPoolStatus {
}
