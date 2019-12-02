package first_exercise;

import redis.clients.jedis.Jedis;

public class Loader {
    public static void main(String[] args) {
        Jedis client = new Jedis("localhost", 6379);
        City cites = new City(client);
        cites.start();
    }
}
