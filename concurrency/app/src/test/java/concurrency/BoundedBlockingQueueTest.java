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


    /**
     * Methods ["BoundedBlockingQueue","enqueue","enqueue","enqueue","dequeue","dequeue","dequeue","enqueue"]
     * Parameters [[3],[1],[0],[2],[],[],[],[3]]
     * Output:
     * [1,0,2,1]
     *
     * Number of producer threads = 3
     * Number of consumer threads = 4
     *
     * BoundedBlockingQueue queue = new BoundedBlockingQueue(3);   // initialize the queue with capacity = 3.
     *
     * queue.enqueue(1);   // Producer thread P1 enqueues 1 to the queue.
     * queue.enqueue(0);   // Producer thread P2 enqueues 0 to the queue.
     * queue.enqueue(2);   // Producer thread P3 enqueues 2 to the queue.
     * queue.dequeue();    // Consumer thread C1 calls dequeue.
     * queue.dequeue();    // Consumer thread C2 calls dequeue.
     * queue.dequeue();    // Consumer thread C3 calls dequeue.
     * queue.enqueue(3);   // One of the producer threads enqueues 3 to the queue.
     * queue.size();       // 1 element remaining in the queue.
     *
     * Since the number of threads for producer/consumer is greater than 1, we do not know how the threads will be
     * scheduled in the operating system, even though the input seems to imply the ordering.
     * Therefore, any of the output [1,0,2] or [1,2,0] or [0,1,2] or [0,2,1] or [2,0,1] or [2,1,0] will be accepted.
     */
    @Test
    public void shouldWorkAsExpectedWithCapacityOf3() {
        BoundedBlockingQueue boundedBlockingQueue = new BoundedBlockingQueue(3);

        Thread producer1 = new Thread(() -> {
            try {
                boundedBlockingQueue.enqueue(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread producer2 = new Thread(() -> {
            try {
                boundedBlockingQueue.enqueue(0);
                boundedBlockingQueue.enqueue(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread producer3 = new Thread(() -> {
            try {
                boundedBlockingQueue.enqueue(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer1 = new Thread(() -> {
            try {
                boundedBlockingQueue.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer2 = new Thread(() -> {
            try {
                boundedBlockingQueue.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumer3 = new Thread(() -> {
            try {
                boundedBlockingQueue.dequeue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });



        producer1.start();
        producer2.start();
        producer3.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

        try {
            producer1.join();
            producer2.join();
            producer3.join();
            consumer1.join();
            consumer2.join();
            consumer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(1, boundedBlockingQueue.size());
    }
}