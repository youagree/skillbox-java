package second_exercise.utils;

import redis.clients.jedis.exceptions.JedisException;

public class Utils {
    public static void waitSomeTime(long second) {
        try {
            Thread.sleep(second);
        } catch (InterruptedException | JedisException exx) {
            exx.printStackTrace();
        }
    }
}
