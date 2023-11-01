package concurrency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundedBlockingQueueTest {

    /**
     * Number of producer threads = 1
     * Number of consumer threads = 1
     * <p>
     * Methods ["BoundedBlockingQueue","enqueue","dequeue","dequeue","enqueue","enqueue","enqueue","enqueue","dequeue"]
     * Parameters [[2],[1],[],[],[0],[2],[3],[4],[]]
     * <p>
     * queue.enqueue(1);   // The producer thread enqueues 1 to the queue.
     * queue.dequeue();    // The consumer thread calls dequeue and returns 1 from the queue.
     * queue.dequeue();    // Since the queue is empty, the consumer thread is blocked.
     * queue.enqueue(0);   // The producer thread enqueues 0 to the queue. The consumer thread is unblocked and returns 0 from the queue.
     * queue.enqueue(2);   // The producer thread enqueues 2 to the queue.
     * queue.enqueue(3);   // The producer thread enqueues 3 to the queue.
     * queue.enqueue(4);   // The producer thread is blocked because the queue's capacity (2) is reached.
     * queue.dequeue();    // The consumer thread returns 2 from the queue. The producer thread is unblocked and enqueues 4 to the queue.
     * queue.size();       // 2 elements remaining in the queue. size() is always called at the end of each test case.
     * <p>
     * Output:
     * [1,0,2,2]
     */
    @Test
    public void shouldReturn1022() {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(2);

        Thread producer = new Thread(() -> {
            try {
                int[] elements = new int[]{1, 0, 2, 3, 4};
                for (int element : elements) {
                    boundedBlockingQueue.enqueue(element);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                boundedBlockingQueue.dequeue();
                boundedBlockingQueue.dequeue();
                boundedBlockingQueue.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();

        //wait until both producer and consumer threads have finished
        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(2, boundedBlockingQueue.size());
    }

}