package concurrency;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class PrintInOrderTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }



    /**
     * Input: [1,2,3] means that this class will be passed to three threads.
     * The first one will call the first() method,
     * the second one will call the second() method,
     * and the third one will call the third() method.
     */
    @Test
    public void shouldPrintInOrderForInput123() throws InterruptedException {
        PrintInOrder printInOrder = new PrintInOrder();
        Runnable first = () -> System.out.println("first");
        Runnable second = () -> System.out.println("second");
        Runnable third = () -> System.out.println("third");

        Thread thread1 = new Thread(() -> {
            try {
                printInOrder.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                printInOrder.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                printInOrder.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(1000);

        assertEquals("first\nsecond\nthird\n", outputStreamCaptor.toString());
    }

    /**
     * Input: [2,1,3] means that this class will be passed to three threads.
     * The first one will call the second() method,
     * the second one will call the first() method,
     * and the third one will call the third() method.
     *
     */
    @Test
    public void shouldPrintInOrderForInput213() throws InterruptedException {
        PrintInOrder printInOrder = new PrintInOrder();
        Runnable first = () -> System.out.println("first");
        Runnable second = () -> System.out.println("second");
        Runnable third = () -> System.out.println("third");

        Thread thread1 = new Thread(() -> {
            try {
                printInOrder.second(second);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                printInOrder.first(first);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                printInOrder.third(third);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(1000);

        assertEquals("first\nsecond\nthird\n", outputStreamCaptor.toString());
    }

}