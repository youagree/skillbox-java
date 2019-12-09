package second_exercise;

import static second_exercise.utils.Constants.HOST;
import static second_exercise.utils.Constants.KEY;
import static second_exercise.utils.Constants.PORT;
import static second_exercise.utils.Utils.waitSomeTime;

import redis.clients.jedis.Jedis;

public class ProducerWithPromote
{
    public static void main(String[] args) {
        int count = 0;
        try (Jedis client = new Jedis(HOST, PORT)) {
            while (true) {
                waitSomeTime(1000);
                count++;
                if (count == 5) {
                    long randomPosition = (long) (0 + 20 * Math.random());
                    String user = client.lindex(KEY, randomPosition);
                    client.lrem(KEY,0, user);
                    client.lpush(KEY, user);
                    count = 0;
                }
                client.rpoplpush(KEY, KEY);;
            }
        }
    }
}
