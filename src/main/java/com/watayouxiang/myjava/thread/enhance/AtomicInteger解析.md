# AtomicInteger如何实现线程安全

### 参考来源

- Java多线程
	- [https://www.liangzl.com/get-article-detail-123358.html](https://www.liangzl.com/get-article-detail-123358.html)
- 深入解析Java AtomicInteger原子类型
	- [https://www.cnblogs.com/rever/p/8215743.html](https://www.cnblogs.com/rever/p/8215743.html)
- 原子操作类AtomicInteger详解
	- [https://blog.csdn.net/fanrenxiang/article/details/80623884](https://blog.csdn.net/fanrenxiang/article/details/80623884)

### i++为什么在多线程中是不安全的？

```
private static int unsafeCount = 0;

/**
 * 不安全的自增
 */
private static void unsafeIncrease() {
    Thread[] threads = new Thread[20];
    for (int i = 0; i < threads.length; i++) {
        threads[i] = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 1000; j++) {
                    unsafeCount++;
                }
            }
        });
        threads[i].start();
    }

    // 返回当前线程的线程组中活动线程的数目
    while (Thread.activeCount() > 1) {
        // 暂停当前正在执行的线程对象，并执行其他线程
        Thread.yield();
    }
    System.out.println("unsafeCount = " + unsafeCount);
}
```

- `i++;` 可以分成三步
	- 1. 获取变量当前值
	- 2. 给获取的当前变量值+1
	- 3. 写回新的值到变量
- 当多条语句在操作“同一个共享数据”时，一个线程对多条语句只执行了一部分，还没执行完成。此时，另一线程参与进来执行，导致数据错误。

### AtomicInteger如何实现线程安全？


```
private static AtomicInteger safeCount = new AtomicInteger(0);

/**
 * 安全的自增
 */
private static void safeIncrease() {
    Thread[] threads = new Thread[20];
    for (int i = 0; i < threads.length; i++) {
        threads[i] = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < 1000; j++) {
                    safeCount.incrementAndGet();
                }
            }
        });
        threads[i].start();
    }

    // 返回当前线程的线程组中活动线程的数目
    while (Thread.activeCount() > 1) {
        // 暂停当前正在执行的线程对象，并执行其他线程
        Thread.yield();
    }
    System.out.println("safeCount = " + safeCount);
}
```

- 只要保证 “有序性”，“可见性”，“原子性”。那么就能实现线程安全。
- AtomicInteger中用到了volatile修饰int类型的变量，使得该变量具备了“可见性”和“有序性”
	- 可见性：多线程操作变量时，都是等到线程代码执行完毕后才会把修改后的变量从工作内存更新到主内存中去，但是对 volatile修饰后的变量 操作，会立即更新到主内存中去。从而保证每次读取到volatile变量，一定是最新的数据，换句话说，volatile变量在各个线程中的值是一致的。
	- 有序性：为了获取更好的性能JVM会对指令进行重排序，使用volatile则会禁止指令重排序，从而确保了 “有序性”，遵循了java内存模型中的 “happens-before，即先行发生原则”，当然这也一定程度上降低了代码执行效率。
- AtomicInteger的同步实现主要基于“CAS机制”也就是所谓的乐观锁，从而具备了原子行。
	- 乐观锁是先假定没有冲突直接进行操作，如果因为有冲突而失败就重试，直到操作成功。其中CAS机制（Compare and Swap）就是 “乐观锁”。
	- AtomicInteger 中的CAS操作就是compareAndSet()，其作用是每次从内存中根据内存偏移量（valueOffset）取出数据，将取出的值跟expect 比较，如果数据一致就把内存中的值改为update。这样CAS就保证了原子操作。
