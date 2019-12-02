package second_exercise;

import redis.clients.jedis.Jedis;

public class Displayer {
    public static void main(String[] args) {
        Jedis client = new Jedis("localhost", 8080);
        SiteLog siteLog = new SiteLog(client);
        siteLog.startViewUsers();
    }
}
