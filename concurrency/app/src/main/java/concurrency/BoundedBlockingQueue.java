package concurrency;

import java.util.LinkedList;
import java.util.Queue;

public class BoundedBlockingQueue {
    private final Queue<Integer> queue;
    private final int capacity;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public void enqueue(int element) throws InterruptedException {
        synchronized(queue) {
            while(queue.size() == capacity){
                queue.wait();
            }
            queue.notifyAll();
            queue.offer(element);
            System.out.println("Enqueued "
                    + element
                    + ". Queue status: "
                    + queue
                    + ". Queue size: "
                    + queue.size());
        }
    }

    public int dequeue() throws InterruptedException {
        int val;
        synchronized(queue){
            while(queue.isEmpty())  {
                queue.wait();
            }
            queue.notifyAll();
            val = queue.poll();
            System.out.println("Dequeued "
                    + val
                    + ". Queue status: "
                    + queue
                    + ". Queue size: "
                    + queue.size());
        }
        return val;
    }

    public int size() {
        return queue.size();
    }
}
