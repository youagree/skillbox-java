public class ReentrantLock {

    private final Object sync = new Object();
    private Thread lockedBy = null;
    private int lockCount = 0;

    public void lock() throws InterruptedException {
        synchronized (sync) {
            Thread callingThread = Thread.currentThread();
            while (lockedBy != null && lockedBy != callingThread) {
                sync.wait();
            }
            lockedBy = callingThread;
            lockCount++;
        }
    }

    public void unlock() {
        synchronized (sync) {
            if (Thread.currentThread() == lockedBy)
                if (--lockCount == 0) {
                    lockedBy = null;
                    sync.notify();
                }
        }
    }
}
