![image](http://ohqvqufyf.bkt.clouddn.com/blockingqueue.png)

## JAVA中的几种主要的阻塞队列

1. ArrayBlockingQueue: 基于数组实现的一个阻塞队列，在创建ArrayBlockingQueue对象必须指定容量大小。并且可以指定公平性和非公平性，默认情况下为非公平性，即不保存等待时间最长的队列有限能够访问队列。
2. LinkedBlockingQueue: 基于链表实现的一个阻塞队列，在创建LinkedBlockingQueue对象如果不指定容量大小，则为Integer.MAX_VALUE.
3. PriorityBlockingQueue: 以上2种队列都是先进先出队列，而PriorityBlockQueue却不是，它会按照元素的优先级对元素进行排序，按照优先级顺序出队，每次出队的元素都是优先级最高的元素。注意，此阻塞队列为无界阻塞队列，即容量没有上限。
4. DelayQueue: 基于PriorityBlockingQueue: 基于PriortyQueue, 一种延时阻塞队列，DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。DelayQueue也是一种无界队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞。

## 阻塞队列的几个方法

1. put(E e): 向队尾存入元素，如果队列满了，则等待。
2. tack(): 从队首取元素，如果队伍为空，则等待。
3. offer(E e, long timeout, TimeUnil unit): 如果队列满了则等待一定时间，没有插入成功则返回false,成功则返回true.
4. poll(long timeout, TimeUnit unit): 如果队列为空，则等待一定时间，当时间期限达到时，如果未取到，则返回null.


## 实现

- 阻塞队列实现

```java
public class BlockingQueue {
    private List<Object> queue = new LinkedList<>();
    private int limit = 10;

    public BlockingQueue(int limit){
        this.limit = limit;
    }

    /**
     * 当队列满了的时候阻塞线程
     *
     * @param item
     * @throws InterruptedException
     */
    public synchronized void enqueue(Object item) throws InterruptedException {
        while (this.queue.size() == this.limit) {
            wait();
        }
        if (this.queue.size() < this.limit) {
            notifyAll();
        }
        this.queue.add(item);
    }

    /**
     * 当队列为空的时候阻塞线程
     * @return
     * @throws InterruptedException
     */
    public synchronized Object dequeue() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() > 1) {
            notifyAll();
        }
        return this.queue.remove(0);
    }
}

```

- 使用阻塞队列实现生产者消费者

```java
public class ProducerConsumerPattern {

    public static void main(String[] args) {
        BlockingQueue shareQueue = new LinkedBlockingQueue<>();

        Thread prodThread = new Thread(new Producer(shareQueue));
        Thread consThread = new Thread(new Consumer(shareQueue));

        prodThread.start();
        consThread.start();
    }

    /**
     * 生产者
     */
    static class Producer implements Runnable {
        private final BlockingQueue shareQueue;
        public Producer(BlockingQueue shareQueue) {
            this.shareQueue = shareQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++){
                System.out.println("Produced: " + i);
                try {
                    shareQueue.put(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 消费者
     */
    static class Consumer implements Runnable {
        private final java.util.concurrent.BlockingQueue shareQueue;
        public Consumer(BlockingQueue shareQueue) {
            this.shareQueue = shareQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Consumer: " + shareQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
```

- 使用Lock和Condition实现阻塞队列

```java
public class BoundedBuffer {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[100];

    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length)
                notFull.await();
            items[putptr] = x;
            if (++putptr == items.length)
                putptr = 0;
            ++count;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}

```

[源码传送门](https://github.com/SanfenR/JavaNoob/tree/master/BlockingQueue)
