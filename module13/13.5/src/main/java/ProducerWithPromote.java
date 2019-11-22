import redis.clients.jedis.Jedis;

public class ProducerWithPromote
{
    public static void main(String[] args) throws InterruptedException {
        Jedis client = new Jedis("localhost", 6379);
        SiteLog siteLog = new SiteLog(client);
        siteLog.donateForPaidOption();
    }
}
