package second_exercise;

import static second_exercise.utils.Constants.HOST;
import static second_exercise.utils.Constants.KEY;
import static second_exercise.utils.Constants.PORT;
import static second_exercise.utils.Utils.waitSomeTime;

import redis.clients.jedis.Jedis;

public class Displayer {
    public static void main(String[] args) {
        try (Jedis client = new Jedis(HOST, PORT)) {
            for (long i = 0; i < 20; i++) {
                client.lpush(KEY, "User  " + i);
            }
            while (true) {
                waitSomeTime(1000);
                String currentActiveUser = client.lindex(KEY, 0);
                System.out.println(currentActiveUser);
            }
        }
    }
}
