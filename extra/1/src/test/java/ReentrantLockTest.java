
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ReentrantLockTest {

    @Test
    public void testLockAndUnlock() throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(9);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            new Thread(() -> {
                try {
                    reentrantLock.lock();
                    list.add(Thread.currentThread().getName());
                    reentrantLock.unlock();
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        countDownLatch.await();
        assertEquals(list.size(), 9);
    }
}
