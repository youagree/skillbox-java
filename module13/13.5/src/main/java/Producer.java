import redis.clients.jedis.Jedis;

public class Producer {
    public static void main(String[] args) {
        Jedis client = new Jedis("localhost", 6379);
        SiteLog siteLog = new SiteLog(client);
        siteLog.startViewUsers();
    }
}
