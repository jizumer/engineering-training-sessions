package concurrency;

public class PrintInOrder {


    private volatile int counter = 0;
    private final Object lock = new Object();

    public PrintInOrder() {

    }

    private void runIfCounterValue(Runnable runnable, int value) {
        boolean done = false;
        while (!done) {
            synchronized (lock) {
                if (this.counter == value - 1) {
                    runnable.run();
                    counter++;
                    done = true;
                }
            }
        }
    }

    public void first(Runnable printFirst) throws InterruptedException {

        runIfCounterValue(printFirst, 1);

        // printFirst.run() outputs "first". Do not change or remove this line.
        //printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        runIfCounterValue(printSecond, 2);
        // printSecond.run() outputs "second". Do not change or remove this line.
        //printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {
        runIfCounterValue(printThird, 3);
        // printThird.run() outputs "third". Do not change or remove this line.
        //printThird.run();
    }
}
