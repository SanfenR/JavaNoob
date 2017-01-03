package block;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sanfen on 2017/1/3.
 *
 * 阻塞队列
 *
 */
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
