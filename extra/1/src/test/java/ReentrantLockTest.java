import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class ReentrantLockTest {

    @Test
    public void testLockAndUnlock() throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread.sleep(100);
            new Thread(() -> {
                try {
                    reentrantLock.lock();
                    list.add(Thread.currentThread().getName());
                    reentrantLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        assertEquals(list.size(), 9);
    }
}
