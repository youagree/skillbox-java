package second_exercise;

import redis.clients.jedis.Jedis;

public class ProducerWithPromote
{
    public static void main(String[] args) throws InterruptedException {
        Jedis client = new Jedis("localhost", 8080);
        SiteLog siteLog = new SiteLog(client);
        siteLog.donateForPaidOption();
    }
}
