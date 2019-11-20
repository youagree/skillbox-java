import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

import java.util.List;

public class SiteLog {

    private Jedis client;
    List<String> listOfUsers;
    String key = "Users";

    public SiteLog(Jedis client) {
        this.client = client;
    }

    public void start() {
        listOfUsers = generateListOfUsers();
        emulateSiteWork(listOfUsers);
    }

    private List<String> generateListOfUsers() {
        removeKey();
        for (long i = 0; i < 20; i++) {
            client.rpush(key, String.valueOf(i));
        }
        List<String> users = client.lrange(key, 0, 19);
        users.stream().forEach(System.out::println);
        return users;
    }

    private void emulateSiteWork(List<String> list) {
        for (;;){
            for (int i = 0; i < list.size(); i++) {
                System.out.println("User " + client.lindex(key, i));
                try {
                    Thread.sleep(200);
                    if (Math.random() < 0.10) {
                        donateForPaidOption(i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void donateForPaidOption(int userNumber) throws InterruptedException {
        long randomPosition = (long) (0 + listOfUsers.size() * Math.random());
        String currentUser = client.lindex(key, userNumber);
        client.linsert(key, ListPosition.AFTER,  currentUser,client.lindex(key,randomPosition));
        System.out.println("User " + randomPosition + " buy paid option");
        Thread.sleep(1000);
    }

    private void removeKey() {
        client.del("Users");
    }
}