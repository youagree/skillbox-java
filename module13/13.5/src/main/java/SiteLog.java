import redis.clients.jedis.Jedis;

import java.util.Set;

public class SiteLog {

    private Jedis client;
    String key = "Users";
    Set<String> setOfUsers;

    public SiteLog(Jedis client) {
        this.client = client;
    }

    public void startViewUsers() {
        setOfUsers = generateListOfUsers();
        emulateSiteWork(setOfUsers);
    }

    private Set<String> generateListOfUsers() {
        removeKey();
        for (long i = 0; i < 20; i++) {
            client.zadd(key, i, "User " + i);
        }
        Set<String> users = client.zrange(key, 0, 19);
        return users;
    }

    private void emulateSiteWork(Set<String> list) {
        for (;;){
            for(String set : list){
                System.out.println(set);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    protected void donateForPaidOption() throws InterruptedException {
        setOfUsers = generateListOfUsers();
        for (;;){
            long randomPosition = (long) (0 + setOfUsers.size() * Math.random());
            System.out.println("User " + randomPosition + " buy paid option");
            Thread.sleep(1000);
        }
    }

    private void removeKey() {
        client.del("Users");
    }
}