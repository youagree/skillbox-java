import redis.clients.jedis.Jedis;

public class Loader {
    public static void main(String[] args) {
        Jedis client = new Jedis("localhost", 6379);
        City cites = new City(client);
        cites.start();
        SiteLog log = new SiteLog(client);
        log.start();
    }
}
