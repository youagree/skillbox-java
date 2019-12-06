package second_exercise;

import static second_exercise.utils.Constants.HOST;
import static second_exercise.utils.Constants.KEY;
import static second_exercise.utils.Constants.PORT;

import redis.clients.jedis.Jedis;

public class ProducerWithPromote
{
    public static void main(String[] args) {
        try (Jedis client = new Jedis(HOST, PORT)) {
            while (true) {
                String deletedUser = client.lpop(KEY);
                client.lpush(KEY, deletedUser);
            }
        }
    }
}
