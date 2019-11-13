import java.util.Date;
import java.util.List;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

public class SiteLog {

    private Jedis client;
    List<Tuple> result;

    public SiteLog(Jedis client) {
        this.client = client;
    }

    public void start() {
        result = init();
        print(result);
    }

    private List<Tuple> init() {
        removeKey();
        for (int i = 1; i <= 20; i++) {
            client.zadd("Users", new Date().getTime(), String.valueOf(i));
        }
        ScanResult<Tuple> users = client.zscan("Users", "0");
        return users.getResult();
    }

    private void print(List<Tuple> list) {
        for (Tuple user : list) {
            System.out.println("User " + user.getElement());
            try {
                Thread.sleep(100);
                if (Math.random() < 0.10) {
                    bill();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        print(list);
    }

    private void bill() throws InterruptedException {
        int i = (int) (0 + result.size() * Math.random());
        String element = result.get(i).getElement();
        System.out.println("User " + element + " buy paid option");
        Thread.sleep(1000);

    }

    private void removeKey() {
        client.del("Users");
    }
}