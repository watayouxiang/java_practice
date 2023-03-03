# `sleep(int)`和`wait()`区别？

## 相同点

- 可以让线程处于“冻结”状态

## 不同点

- 在同步代码中，wait会释放锁，sleep则会一直持有锁。
- wait用于线程交互，sleep用于暂停执行
- sleep时间到，线程处于“阻塞”或者“运行”。wait如果没有指定时间，必须要通过notify或者notifyAll唤醒。
- sleep不一定要定义在同步中，wait必须定义在同步中