package block;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by sanfen on 2017/1/3.
 * 阻塞队列实现消费者生产者
 * 提供阻塞队列中put()和take()
 * ArrayBlockingQueue自然有界
 * LinkedBlockingQueue可选边界
 *
 */
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
